package com.qiuwei.shiro.entity;

import lombok.Data;

import java.util.Date;

/**
 * 角色
 * 
 * @author qiuwei
 * 
 * @date 2019-08-30
 */
@Data
public class Role {
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者ID
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}