package com.tyxj.security.realm;

import java.util.Arrays;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import com.tyxj.common.MD5Util;

public class CustomCredentialsMatcher extends HashedCredentialsMatcher {

	public boolean doCredentialsMatch(AuthenticationToken authcToken,
			AuthenticationInfo info) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		Object tokenCredentials = encrypt(String.valueOf(token.getPassword()));
		Object accountCredentials = getCredentials(info);
		// 将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
		return isEqual(tokenCredentials, accountCredentials);
	}

	// 将传进来密码加密方法
	private String encrypt(String data) {
		return MD5Util.md5s(data);
	}
	
	protected boolean isEqual(Object tokenCredentials, Object accountCredentials) {
		if ((isByteSource(tokenCredentials))
				&& (isByteSource(accountCredentials))) {
			byte[] tokenBytes = toBytes(tokenCredentials.toString());
			byte[] accountBytes = toBytes(accountCredentials.toString());
			return Arrays.equals(tokenBytes, accountBytes);
		}
		return accountCredentials.equals(tokenCredentials);
	}
}
