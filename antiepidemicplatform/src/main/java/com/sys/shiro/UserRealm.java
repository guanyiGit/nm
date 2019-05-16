package com.sys.shiro;

import com.orgmanagement.domain.RoleDO;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.ResourceInfoService;
import com.orgmanagement.service.UserInfoService;
import com.sys.config.ApplicationContextRegister;
import com.utils.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class UserRealm extends AuthorizingRealm {
/*	@Autowired
	UserDao userMapper;
	@Autowired
	MenuService menuService;*/

	@Autowired
	UserInfoService userInfoService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		Long userId = ShiroUtils.getUserId();
		ResourceInfoService menuService = ApplicationContextRegister.getBean(ResourceInfoService.class);
		Set<String> perms = menuService.listPerms(Integer.parseInt(userId.toString()));

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();



		Map<String, Object> map = new HashMap<>(16);
        UserDO user = ShiroUtils.getUser();
        List<RoleDO> roles = user.getRoles();
        List<String> listrole = new ArrayList<>();
		if(roles.size()>0){
		    roles.stream().forEach(role ->{
		        listrole.add(role.getRoleName());
            });
        }


		info.setStringPermissions(perms);
		info.addRoles(listrole);

		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		Map<String, Object> map = new HashMap<>(16);
		map.put("username", username.trim());
		String password = new String((char[]) token.getCredentials());

//		UserInfoDao userMapper = ApplicationContextRegister.getBean(UserInfoDao.class);
		// 查询用户信息
		UserDO user = userInfoService.list(map).get(0);

		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}

		// 密码错误
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}

		// 账号锁定
		if (user.getStatus() == 0) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

}
