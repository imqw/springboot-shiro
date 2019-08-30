package com.qiuwei.shiro.mapper;

import com.qiuwei.shiro.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: qiuwei@19pay.com.cn
 * @Version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {


    @Autowired
    UserMapper userMapper;


    @Test
    public void testFindByUsername() {

        User admin = this.userMapper.findByUsername("admin");

        System.out.println(admin);


    }
}