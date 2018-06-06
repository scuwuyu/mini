package com.gongsi.mini.entities;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private Long id;

    private String userId;

    private String nickName;

    private String openId;

    private String avatarUrl;

    private Boolean isSeller;

    private Date createTime;
}