package com.qiuwei.shiro.mapper;

import com.qiuwei.shiro.entity.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {

    int delete(Long roleId);

    int insert(Role role);

    Role findById(Long roleId);

    int updateByParams(Role role);
}