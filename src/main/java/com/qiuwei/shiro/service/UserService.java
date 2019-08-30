package com.qiuwei.shiro.service;

import com.qiuwei.shiro.util.CacheUser;

/**
 * @Author: qiuwei@19pay.com.cn
 * @Version 1.0.0
 */
public interface UserService {

    /**
     * 登入
     * @param username
     * @param password
     * @return
     */
    CacheUser login(String username, String password);


    /**
     * 登出
     */
    void logout();



}
