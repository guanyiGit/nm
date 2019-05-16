package com.orgmanagement.dao;

import com.orgmanagement.domain.RoleMenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-29 16:33:39
 */
@Mapper
public interface RoleRefResourceDao {

//	RoleRefResource get(Integer id);
//
//	List<RoleRefResource> list(Map<String, Object> map);
//
//	int count(Map<String, Object> map);
//
//	int save(RoleRefResource roleRefResource);
//
//	int update(RoleRefResource roleRefResource);
//
//	int remove(Integer id);
//
//	int batchRemove(Integer[] ids);

	RoleMenuDO get(Long id);

	List<RoleMenuDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(RoleMenuDO roleMenu);

	int update(RoleMenuDO roleMenu);

	int remove(Long id);

	int batchRemove(Long[] ids);

	List<Long> listMenuIdByRoleId(Long roleId);

	int removeByRoleId(Long roleId);

	int removeByMenuId(Long menuId);

	int batchSave(List<RoleMenuDO> list);
}
