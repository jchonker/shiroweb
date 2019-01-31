package com.atguigu.shiro.services;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;

public class ShiroService {

	@RequiresRoles({"admin"})
	public void testMethod() {
		System.out.println("testMethod,time:"+new Date());
		
		/**
			 原本在Service层中是获取不到Controller层中session中的值的，因为会造成侵入
			但是可以 通过shiro提供的session在service层中获取controller层中HttpSession对象的值
		 	这样会给开发带来便利
		 */
		Session session = SecurityUtils.getSubject().getSession();
		Object val = session.getAttribute("key");
		System.out.println(val);     //运行后可以发现:可以获取到key的值value12345
	}
	
}
