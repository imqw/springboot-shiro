package com.qiuwei.shiro.mapper;

import com.qiuwei.shiro.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    int delete(Long roleId);

    int insert(Role role);

    Role findById(Long roleId);

    int updateByParams(Role role);


    List<Role> listRoleByUserId(Long userId);

}