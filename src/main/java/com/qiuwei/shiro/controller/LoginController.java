package com.qiuwei.shiro.controller;

import com.qiuwei.shiro.entity.User;
import com.qiuwei.shiro.service.UserService;
import com.qiuwei.shiro.util.CacheUser;
import com.qiuwei.shiro.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: qiuwei@19pay.com.cn
 * @Version 1.0.0
 */
@Slf4j
@RestController
public class LoginController {


    private Response response;

    private UserService userService;

    @Autowired
    @SuppressWarnings("all")
    public LoginController(Response response, UserService userService) {
        this.response = response;
        this.userService = userService;
    }

    /**
     * description: 登录
     *
     * @return 登录结果
     */
    @PostMapping("/login")
    public Response login(User user) {
        log.warn("进入登录.....");

        String username = user.getUsername();
        String password = user.getPassword();

        if (StringUtils.isBlank(username)) {
            return response.failure("用户名为空！");
        }

        if (StringUtils.isBlank(password)) {
            return response.failure("密码为空！");
        }

        CacheUser loginUser = userService.login(username, password);
        // 登录成功返回用户信息
        return response.success("登录成功！", loginUser);
    }

    /**
     * description: 登出
     */
    @RequestMapping("/logout")
    public Response logOut() {
        userService.logout();
        return response.success("登出成功！");
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping("/un_auth")
    public Response unAuth() {
        return response.failure(HttpStatus.UNAUTHORIZED, "用户未登录！", null);
    }

    /**
     * 未授权，无权限，此处返回未授权状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping("/unauthorized")
    public Response unauthorized() {
        return response.failure(HttpStatus.FORBIDDEN, "用户无权限！", null);
    }

}
