package com.emp.service;

import java.util.Set;



import com.emp.entity.User;

public interface UserService {

	      User queryUser(String username);//依据用户名查询用户
		
		void addUser(User user);//添加用户
		
		 Set<String> queryRoles(String username);//依据用户名查询用户的所有的角色   
		
	
	     Set<String> queryPermissions(String username);//依据用户名查询所有的权限
}
