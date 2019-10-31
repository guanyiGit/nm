package com.orgmanagement.service.impl;

import com.orgmanagement.dao.RoleInfoDao;
import com.orgmanagement.dao.RoleRefResourceDao;
import com.orgmanagement.dao.UserInfoDao;
import com.orgmanagement.dao.UserRefRoleDao;
import com.orgmanagement.domain.RoleDO;
import com.orgmanagement.domain.RoleMenuDO;
import com.orgmanagement.domain.UserDO;
import com.orgmanagement.service.RoleInfoService;
import com.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class RoleInfoServiceImpl implements RoleInfoService {
//	@Autowired
//	private RoleInfoDao roleInfoDao;
//
//	@Override
//	public RoleInfo get(Integer id){
//		return roleInfoDao.get(id);
//	}
//
//	@Override
//	public List<RoleInfo> list(Map<String, Object> map){
//		return roleInfoDao.list(map);
//	}
//
//	@Override
//	public int count(Map<String, Object> map){
//		return roleInfoDao.count(map);
//	}
//
//	@Override
//	public int save(RoleInfo roleInfo){
//		return roleInfoDao.save(roleInfo);
//	}
//
//	@Override
//	public int update(RoleInfo roleInfo){
//		return roleInfoDao.update(roleInfo);
//	}
//
//	@Override
//	public int remove(Integer id){
//		return roleInfoDao.remove(id);
//	}
//
//	@Override
//	public int batchRemove(Integer[] ids){
//		return roleInfoDao.batchRemove(ids);
//	}
//
//	@Override
//	public RoleInfo selectByRoleName(Map<String, Object> map) {
//		return roleInfoDao.selectByRoleName(map);
//	}


	public static final String ROLE_ALL_KEY = "\"role_all\"";

	public static final String DEMO_CACHE_NAME = "role";

	@Autowired
	RoleInfoDao roleMapper;
	@Autowired
	RoleRefResourceDao roleMenuMapper;
	@Autowired
	UserInfoDao userMapper;
	@Autowired
	UserRefRoleDao userRoleMapper;

	@Override
	public List<RoleDO> list() {
		List<RoleDO> roles = roleMapper.list(new HashMap<>(16));
		//判断登陆者的身份进行  角色分配

        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type==1){//超级管理员
            return roles.stream().filter(role ->{return role.getType()==6||role.getType()==10
                    ||role.getType()==2||role.getType()==5||role.getType()==9||role.getType()==3
                    ||role.getType()==8||role.getType()==7;}).collect(Collectors.toList());
        }else if(type ==6){//州级管理员
                return  roles.stream().filter(role->{return (role.getType()==6 ||role.getType()==10||role.getType()==5);}).collect(Collectors.toList());
        }else if(type ==5){//县级管理员
                return  roles.stream().filter(role->{return (role.getType()==5 ||role.getType()==9||role.getType()==3);}).collect(Collectors.toList());
        }else if(type == 3){//乡级管理员
                return  roles.stream().filter(role->{return (role.getType()==3 ||role.getType()==8);}).collect(Collectors.toList());
        }else {//非管理员
            return roles;
        }
	}


	@Override
	public List<RoleDO> list(Long userId) {
		List<Long> rolesIds = userRoleMapper.listRoleId(userId);
		List<RoleDO> roles = roleMapper.list(new HashMap<>(16));


        UserDO user = ShiroUtils.getUser();
        int type = user.getRoles().get(0).getType();
        if(type==1){//超级管理员
            roles = roles;
        }else if(type ==6){//州级管理员
            roles =   roles.stream().filter(role->{return (role.getType()==6 ||role.getType()==10);}).collect(Collectors.toList());
        }else if(type ==5){//县级管理员
            roles =   roles.stream().filter(role->{return (role.getType()==5 ||role.getType()==9);}).collect(Collectors.toList());
        }else if(type == 3){//乡级管理员
            roles =   roles.stream().filter(role->{return (role.getType()==3 ||role.getType()==8);}).collect(Collectors.toList());
        }else {//非管理员
            roles =  roles;
        }
        if(roles !=null){
            for (RoleDO roleDO : roles) {
                roleDO.setRoleSign("false");
                for (Long roleId : rolesIds) {
                    if (Objects.equals(roleDO.getRoleId(), roleId)) {
                        roleDO.setRoleSign("true");
                        break;
                    }
                }
            }
        }
		return roles;
	}
	@Transactional
	@Override
	public int save(RoleDO role) {
		int count = roleMapper.save(role);
		List<Long> menuIds = role.getMenuIds();
		Long roleId = role.getRoleId();
		List<RoleMenuDO> rms = new ArrayList<>();
		for (Long menuId : menuIds) {
			RoleMenuDO rmDo = new RoleMenuDO();
			rmDo.setRoleId(roleId);
			rmDo.setMenuId(menuId);
			rms.add(rmDo);
		}
		roleMenuMapper.removeByRoleId(roleId);
		if (rms.size() > 0) {
			roleMenuMapper.batchSave(rms);
		}
		return count;
	}

	@Transactional
	@Override
	public int remove(Long id) {
		int count = roleMapper.remove(id);
		userRoleMapper.removeByRoleId(id);
		roleMenuMapper.removeByRoleId(id);
		return count;
	}

	@Override
	public RoleDO get(Long id) {
		RoleDO roleDO = roleMapper.get(id);
		return roleDO;
	}

	@Override
	public int update(RoleDO role) {
		int r = roleMapper.update(role);
		List<Long> menuIds = role.getMenuIds();
		Long roleId = role.getRoleId();
		roleMenuMapper.removeByRoleId(roleId);
		List<RoleMenuDO> rms = new ArrayList<>();
		for (Long menuId : menuIds) {
			RoleMenuDO rmDo = new RoleMenuDO();
			rmDo.setRoleId(roleId);
			rmDo.setMenuId(menuId);
			rms.add(rmDo);
		}
		if (rms.size() > 0) {
			roleMenuMapper.batchSave(rms);
		}
		return r;
	}

	@Override
	public int batchremove(Long[] ids) {
		int r = roleMapper.batchRemove(ids);
		return r;
	}
}
