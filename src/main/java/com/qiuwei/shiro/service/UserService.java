package com.qiuwei.shiro.service;

import com.qiuwei.shiro.entity.User;

/**
 * @Author: qiuwei@19pay.com.cn
 * @Version 1.0.0
 */
public interface UserService {

    User findByUsername(String username);
}
