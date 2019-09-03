package com.emp.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import com.emp.dao.UserDao;
import com.emp.entity.User;
import com.emp.service.UserService;
@Service
public class UserServicelmpl implements UserService {
	@Resource
     private   UserDao userDao;

	@Override
	public User queryUser(String username) {
		User user = userDao.queryByUserName(username);
		return user;
	}

	@Override
	public Set<String> queryRoles(String username) {
		  Set<String> roles = userDao.queryRoles(username);
		return roles;
	}

	@Override
	public Set<String> queryPermissions(String username) {
		Set<String> pers = userDao.queryPermissions(username);
		return pers;
	}

	@Override
	public void addUser(User user) { 
		//加盐,加密的效果                                                       需要加密的密码                                                        加盐的次数
		 String password = new SimpleHash("MD5", user.getPassword() ,user.getUsername(), 1024).toString();
		 //password就是加密后的密码
		 //用户加密后的密码重置用来重页面传过来的密码
		 user.setPassword(password);
		 userDao.save(user);

		
		
	}


}
