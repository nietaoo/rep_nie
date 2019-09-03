package com.emp.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emp.entity.User;
import com.emp.service.UserService;

       @Controller
  public class UserController {
	  @Resource
	  private UserService userService;
	  
	  @RequestMapping("/user/register")
		public String register(User user){
			userService.addUser(user);
			//重定向到登录页面
			return "redirect:/user/toLogin";
		}
		
		//跳转到注册页面
		@RequestMapping("/user/toRegister")
		public String toRegister(){
			 return "Register";
		}
		
		
		//跳转到登录页面
		@RequestMapping("/user/toLogin")
		public String toLogin(){
			return "Login";
		}
		
		//跳转到位授权页面
		@RequestMapping("/user/unauthorized")
		public String toUnauthorized(){
			return "Unauthorized";
		}
	
	@RequestMapping("/user/login")
	public String login(User user,@RequestParam(value="rememberMe",required=false, defaultValue="0")Integer rememberMe ,Model model ){
		Subject subject=SecurityUtils.getSubject();//getSubject主题  使用这个东西的用户  调用请求/user/login
		//创建一个令牌对象
		
		UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try{
			//判断是否记住我
			if(rememberMe==1){
				token.setRememberMe(true);
			}
			
			//
 			subject.login(token);
		/*	request.setAttribute("user", user);*/
			//重定向到员工列表
			/*subject.logout();//登出
*/			return "redirect:/emp/conditionList";
		}catch(Exception e){
			e.printStackTrace();
			/*request.setAttribute("user", user);*/
		/*	request.setAttribute("errorMsg", "用户名或密码错误！");*/
			model.addAttribute("msg", "用户名或密码错误！");
			return "Login";
		}
	}
	

	
			
}
