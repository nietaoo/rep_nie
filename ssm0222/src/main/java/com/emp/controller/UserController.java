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
			//�ض��򵽵�¼ҳ��
			return "redirect:/user/toLogin";
		}
		
		//��ת��ע��ҳ��
		@RequestMapping("/user/toRegister")
		public String toRegister(){
			 return "Register";
		}
		
		
		//��ת����¼ҳ��
		@RequestMapping("/user/toLogin")
		public String toLogin(){
			return "Login";
		}
		
		//��ת��λ��Ȩҳ��
		@RequestMapping("/user/unauthorized")
		public String toUnauthorized(){
			return "Unauthorized";
		}
	
	@RequestMapping("/user/login")
	public String login(User user,@RequestParam(value="rememberMe",required=false, defaultValue="0")Integer rememberMe ,Model model ){
		Subject subject=SecurityUtils.getSubject();//getSubject����  ʹ������������û�  ��������/user/login
		//����һ�����ƶ���
		
		UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(), user.getPassword());
		try{
			//�ж��Ƿ��ס��
			if(rememberMe==1){
				token.setRememberMe(true);
			}
			
			//
 			subject.login(token);
		/*	request.setAttribute("user", user);*/
			//�ض���Ա���б�
			/*subject.logout();//�ǳ�
*/			return "redirect:/emp/conditionList";
		}catch(Exception e){
			e.printStackTrace();
			/*request.setAttribute("user", user);*/
		/*	request.setAttribute("errorMsg", "�û������������");*/
			model.addAttribute("msg", "�û������������");
			return "Login";
		}
	}
	

	
			
}
