package com.qiuwei.shiro.config;

import com.qiuwei.shiro.entity.Menu;
import com.qiuwei.shiro.entity.Role;
import com.qiuwei.shiro.entity.User;
import com.qiuwei.shiro.mapper.MenuMapper;
import com.qiuwei.shiro.mapper.RoleMapper;
import com.qiuwei.shiro.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

/**
 * @Author: qiuwei@19pay.com.cn
 * @Version 1.0.0
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    private UserMapper userMapper;

    private RoleMapper roleMapper;

    private MenuMapper menuMapper;


    @Autowired
    @SuppressWarnings("all")
    public ShiroRealm(UserMapper userMapper, RoleMapper roleMapper, MenuMapper menuMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.menuMapper = menuMapper;
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        log.info("开始执行授权操作.......");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        /**
         * 查询用户角色
         * 如果身份认证的时候没有传入User对象，这里只能取到userName
         * 也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
         */
        User user = (User) principalCollection.getPrimaryPrincipal();

        if (user == null) {
            log.error("用户不存在");
            throw new UnknownAccountException("用户不存在");
        }

        /**
         * 查询用户角色
         */

        List<Role> roles = this.roleMapper.listRoleByUserId(user.getUserId());

        for (Role role : roles) {
            authorizationInfo.addRole(role.getRoleName());
            // 根据角色查询权限
            List<Menu> menus = this.menuMapper.listMenuByRoleId(role.getRoleId());
            for (Menu m : menus) {
                authorizationInfo.addStringPermission(m.getPerms());
            }
        }
        return authorizationInfo;
    }


    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        log.info("开始进行身份认证......");

        //获取用户的输入的账号.
        String userName = (String) authenticationToken.getPrincipal();

        //通过username从数据库中查找 User对象.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userMapper.findByUsername(userName);
        if (Objects.isNull(user)) {
            return null;
        }

        return new SimpleAuthenticationInfo(
                // 这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                user,
                // 密码
                user.getPassword(),
                // salt = username + salt
                ByteSource.Util.bytes(user.getSalt()),
                // realm name
                getName()
        );
    }
}
