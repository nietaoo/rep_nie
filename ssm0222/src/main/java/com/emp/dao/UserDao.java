package com.emp.dao;

import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.emp.entity.User;

public interface UserDao {
  
	
	@Select("select * from sh_user where username=#{username} ")
     User queryByUserName(@Param("username")String username);//�����û���
	//ע��
	@Insert("insert into sh_user(username ,password) values(#{username},#{password})")
	void save(User user);
	
	
	@Select("select  r.rolename  from sh_user u INNER JOIN sh_user_role ur "
			+ " on u.id = ur.user_id "
			+ " INNER JOIN sh_role r "
			+ " on ur.role_id=r.id "
			+ " where  u.username=#{username}")
	 Set<String> queryRoles(@Param("username")String username);//�����û�����ѯ�û������еĽ�ɫ   ����ֵ������shiro��ܶ����
	
	
	@Select(" select DISTINCT p.permission_name "
			+ " from sh_user u inner join sh_user_role ur "
			+ " on u.id = ur.user_id "
			+ " inner join sh_role r "
			+ " on ur.role_id = r.id"
			+ " inner join sh_role_permission rp"
			+ " on r.id = rp.role_id"
			+ " inner join sh_permission p"
			+ " on rp.permission_id = p.id"
			+ " where u.username=#{username}")
     Set<String> queryPermissions(@Param("username")String username);//�����û�����ѯ���е�Ȩ��
}