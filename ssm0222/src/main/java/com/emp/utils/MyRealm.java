package com.emp.utils;


import java.util.Set;

/**
 * ��֤(����)
 * ��Ȩ
 * �ĺ���ҵ���߼�
 * **/


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.emp.entity.User;
import com.emp.service.UserService;

public class MyRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;
      
	@Override	
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/**		 * ע��principals.getPrimaryPrincipal()��Ӧ		 * new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName())�ĵ�һ������		 */		
		//��ȡ��ǰ���	��ȡ�����û���	
		String username = (String) principals.getPrimaryPrincipal();
		//��Ȩ��֤�Ķ���,��Ҫ���ڵ����û����еĽ�ɫ��Ȩ����Ϣ
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//�����ݿ��в��Ҹ��û��кν�ɫ��Ȩ��		
		Set<String> roles = userService.queryRoles(username);
		Set<String> permissions = userService.queryPermissions(username);
		//Ϊ��ǰ�û������Ӧ��ɫ��Ȩ��		
		info.setRoles(roles);		
		info.setStringPermissions(permissions);		
		//��info�û�ȥ��֤�Ƿ���ĳ��
		return info;	
		
		} 	
	/**	 * ��֤����	 */	
//	@Override	
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//		//��ȡ�û���	
//		String username = (String) token.getPrincipal();
//		//�����ݿ��в����û���Ϣ
//		User user = userService.queryUser(username);
//		if (user == null) {	
//			return null;
//			}		
//		AuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
//		return info;	
//		}
//	 @Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

	        String username = (String) token.getPrincipal();// ��ȡ�û���

	        // �����û�������û��������ݿ����ƥ��  ͨ��username��ѯ�û�����
	       User user = userService.queryUser(username);
	        //�û���������ƥ������
	        if (user != null) {
	             //1)principal����֤��ʵ����Ϣ��������userName��Ҳ���������ݿ���Ӧ���û���ʵ�����  
	            Object principal = user.getUsername();

	            //2)credentials�����ݿ��е�����  
	            Object credentials = user.getPassword(); 

	            //3)realmName����ǰrealm�����name�����ø����getName()��������  
	            String realmName = getName();  

	            //4)credentialsSalt��ֵ  
	            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());//ʹ���û�����Ϊ��ֵ  

	            //�����û��������������AuthenticationInfo����,ͨ��ʹ�õ�ʵ����ΪSimpleAuthenticationInfo
	            //5)�����ݿ����û�����������бȶԣ�������ֵ���ܣ���4����������realName��
	            SimpleAuthenticationInfo authcInfo = new SimpleAuthenticationInfo(principal, credentials,credentialsSalt,realmName);
	            return authcInfo;
	        } else {
	        	//û���û����ؿ� ��֤ʧ��
	            return null;
	        }
	    }
	
	}
		


