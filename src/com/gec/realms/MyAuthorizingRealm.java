package com.gec.realms;

import java.util.Set;

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

import com.gec.domain.User;
import com.gec.mapper.RoleMapper;
import com.gec.mapper.UserMapper;

public class MyAuthorizingRealm extends AuthorizingRealm {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;

	// Cation: 是做登录认证的。
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// {1}获取主身份信息(用户名)
		String username = (String) token.getPrincipal();
		System.out.println(username);
		// {2}做一个 user 判空(数据库中 User)
		User dbUser = userMapper.queryUserByName(username);
		System.out.println(dbUser);
		if (dbUser == null) {
			return null; // 找不到用户
		}

		// new SimpleAuthenticationInfo(
		// 数据库中User, 加密后的口令(数据库),
		// 盐值, realm名称 );
		// {ps}生成盐值的字节对象
		ByteSource salt = ByteSource.Util.bytes(username);
		SimpleAuthenticationInfo info = 
				new SimpleAuthenticationInfo(dbUser, dbUser.getPassword(), salt, getName());
		return info;
	}

	// Zation: 是做用户授权 与 权限认证的。
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
		//获取主身份信息。(做认证时放入的是 user, 所以这里拿到的是 user)
		User priPrincipal = (User) collection.getPrimaryPrincipal();
		//获取角色 ID
		String roleId = priPrincipal.getPrimaryRole().getId(); 
		//通过角色id获取角色权限。
		Set<String> permissions = roleMapper.getRolePermission(roleId); 
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//设置权限信息。
		info.setStringPermissions(permissions);
		//最后将信息对象返回给授权认证器处理。
		return info;
	}

}
