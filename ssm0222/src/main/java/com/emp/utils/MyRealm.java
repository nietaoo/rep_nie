package com.emp.utils;


import java.util.Set;

/**
 * 认证(登入)
 * 授权
 * 的核心业务逻辑
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
		/**		 * 注意principals.getPrimaryPrincipal()对应		 * new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName())的第一个参数		 */		
		//获取当前身份	获取就是用户名	
		String username = (String) principals.getPrimaryPrincipal();
		//授权验证的对象,需要定期登入用户所有的角色和权限信息
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//从数据库中查找该用户有何角色和权限		
		Set<String> roles = userService.queryRoles(username);
		Set<String> permissions = userService.queryPermissions(username);
		//为当前用户赋予对应角色和权限		
		info.setRoles(roles);		
		info.setStringPermissions(permissions);		
		//由info用户去验证是否有某个
		return info;	
		
		} 	
	/**	 * 认证方法	 */	
//	@Override	
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//		//获取用户名	
//		String username = (String) token.getPrincipal();
//		//从数据库中查找用户信息
//		User user = userService.queryUser(username);
//		if (user == null) {	
//			return null;
//			}		
//		AuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
//		return info;	
//		}
//	 @Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

	        String username = (String) token.getPrincipal();// 获取用户名

	        // 根据用户输入的用户名在数据库进行匹配  通过username查询用户对象
	       User user = userService.queryUser(username);
	        //用户名存在则匹配密码
	        if (user != null) {
	             //1)principal：认证的实体信息，可以是userName，也可以是数据库表对应的用户的实体对象  
	            Object principal = user.getUsername();

	            //2)credentials：数据库中的密码  
	            Object credentials = user.getPassword(); 

	            //3)realmName：当前realm对象的name，调用父类的getName()方法即可  
	            String realmName = getName();  

	            //4)credentialsSalt盐值  
	            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());//使用用户名作为盐值  

	            //根据用户的情况，来构建AuthenticationInfo对象,通常使用的实现类为SimpleAuthenticationInfo
	            //5)与数据库中用户名和密码进行比对，密码盐值加密，第4个参数传入realName。
	            SimpleAuthenticationInfo authcInfo = new SimpleAuthenticationInfo(principal, credentials,credentialsSalt,realmName);
	            return authcInfo;
	        } else {
	        	//没有用户返回空 认证失败
	            return null;
	        }
	    }
	
	}
		


