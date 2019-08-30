package com.qiuwei.shiro.util;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 缓存用户信息
 * <br/>
 * @author     ：leigq
 * @date       ：2019/7/3 14:40
 */
@Data
@Builder
public class CacheUser implements Serializable {


    private static final long serialVersionUID = -3977272347775243323L;
    private String email;

    private String name;

    private Integer state;

    private String username;

    private String token;
}
