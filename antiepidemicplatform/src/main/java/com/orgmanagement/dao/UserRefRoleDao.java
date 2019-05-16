package com.orgmanagement.dao;

import com.orgmanagement.domain.RoleDO;
import com.orgmanagement.domain.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-29 16:27:20
 */
@Mapper
public interface UserRefRoleDao {
//
//	UserRefRole get(Integer id);
//
//	List<UserRefRole> list(Map<String, Object> map);
//
//	int count(Map<String, Object> map);
//
//	int save(UserRefRole userRefRole);
//
//	int update(UserRefRole userRefRole);
//
//	int remove(Integer id);
//
//	int batchRemove(Integer[] ids);


	UserRoleDO get(Long id);

	List<UserRoleDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UserRoleDO userRole);

	int update(UserRoleDO userRole);

	int remove(Long id);

	int batchRemove(Long[] ids);

	List<Long> listRoleId(Long userId);

	int removeByUserId(Long userId);

	int removeByRoleId(Long roleId);

	int batchSave(List<UserRoleDO> list);

	int batchRemoveByUserId(Long[] ids);

	List<RoleDO> listRolesByUserId(Long userId);
}
