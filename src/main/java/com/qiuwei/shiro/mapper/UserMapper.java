package com.qiuwei.shiro.mapper;

import com.qiuwei.shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: qiuwei@19pay.com.cn
 * @Version 1.0.0
 */
@Mapper
public interface UserMapper {

    int delete(Long userId);

    int insert(User user);


    User findById(Long userId);


    User findByUsername(String username);



    int updateByParams(User record);
}