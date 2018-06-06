package com.gongsi.mini.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * Created by 吴宇 on 2018-06-05.
 */
public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {
    /** 验证密码*/
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        /** 不用密码验证*/
        return true;
    }
}
