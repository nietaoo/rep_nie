package com.emp.service;

import java.util.Set;



import com.emp.entity.User;

public interface UserService {

	      User queryUser(String username);//�����û�����ѯ�û�
		
		void addUser(User user);//����û�
		
		 Set<String> queryRoles(String username);//�����û�����ѯ�û������еĽ�ɫ   
		
	
	     Set<String> queryPermissions(String username);//�����û�����ѯ���е�Ȩ��
}
