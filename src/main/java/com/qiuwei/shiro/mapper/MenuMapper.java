package com.qiuwei.shiro.mapper;


import com.qiuwei.shiro.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    int delete(Long menuId);

    int insert(Menu menu);

    Menu findById(Long menuId);

    int updateByParams(Menu menu);

    List<Menu> listMenuByRoleId(Long roleId);

}