package com.atguigu.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.util.ByteSource;

public class SecondRealm extends AuthenticatingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
			throws AuthenticationException {
		//System.out.println("doGetAuthenticationInfo:"+token);
		
		System.out.println("[SecondRealm] doGetAuthenticationInfo");
		
		//1:把AuthenticationToken转化为UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		
		//2:从UsernamePasswordToken中获取username
		String username = upToken.getUsername();
		
		//3：调整逐句库中的方法，从数据库中查询username对应的用户记录
		System.out.println("从数据库中获取username:"+username+"所对应的用户信息.");
		
		//4,5是自定义异常
		//4：若用户不存在，则可以抛出UnknownAccountException
		if("unknown".equals(username)) {
			throw new UnknownAccountException("用户不存在!");
		}
		
		//5:根据用户信息的情况，决定是否需要抛出其他的AuthenticationException异常
		if("monster".equals(username)) {
			throw new LockedAccountException("用户被锁定");
		}
		
		//6：根据用户的情况，来构建AuthenticationInfo对象并返回，通常使用的实现类为：SimpleAuthenticationInfo
		//以下信息是从数据库中获取的
		//1）：priciple:认证得实体信息，可以是username，也可以是数据库对应的用户得实体类对象
		Object principal = username;
		//2):credentials:密码
		Object credentials = null;//"fc1709d0a95a6be30bc5926fdb7f22f4";     //123456经过md5加密且次数为1024后的结果
		if("admin".equals(username)) {
			credentials = "ce2f6417c7e1d32c1d81a797ee0b499f87c5de06----";    //admin用户的密码经过SHA1盐值加密后的结果
		}else if("user".equals(username)) {      //密码故意错误,验证AllSuccessfulStrategy
			credentials = "073d4c3ae812935f23cb3f2a71943f49e082a718----";    //user用户的密码经过SHA1盐值加密后的结果
		}
		//3):realmNmae:当前的realm对象得name,调用父类的getName()方法即可
		String realmName = getName();
		//4）：盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		 
     	SimpleAuthenticationInfo info = null;//new SimpleAuthenticationInfo(principal, credentials, realmName);
     	info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		return info; 
	}

	
	public static void main(String[] args) {
		String hashAlgorithmName = "SHA1";         //SHA1加密方式
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("admin"); 
		int hashIterations = 1024; 
		
		SimpleHash result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);      //加密结果为：fc1709d0a95a6be30bc5926fdb7f22f4
	}
	

}
