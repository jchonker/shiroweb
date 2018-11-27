package com.atguigu.shiro.hanlders;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.shiro.services.ShiroService;

@Controller
@RequestMapping("/shiro")
public class ShiroHandler {
	
	@Autowired
	private ShiroService shiroService;
	
	@RequestMapping("/testShiroAnnotation")
	public String testShiroAnnotation(HttpSession session) {
		session.setAttribute("key", "value12345");     //给httpSession对象中设置一个键值对
		shiroService.testMethod();
		return "redirect:/list.jsp";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam("username") String username,@RequestParam("password") String password) {
	
		//重要:获取当前的subject,调用SecurityUtils.getSubject();
        Subject currentUser = SecurityUtils.getSubject();
        
        //测试当前的用户是否已经被认证,即是否已经登录
        //调用Subject的isAuthenticated()方法
        if (!currentUser.isAuthenticated()) {
        	//把用户名和密码封装成UsernamePasswordToken对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //remenberme
            token.setRememberMe(true);
            try {
            	//执行登录,传递token给Realm中的方法
                currentUser.login(token);
            } 
            // ... catch more exceptions here (maybe custom ones specific to your application?
            //所有认证时异常的父类
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            	System.out.println("登陆失败:"+ae.getMessage()); 
            }
        }
		
		return "redirect:/list.jsp";
	}
}
