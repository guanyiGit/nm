package com.orgmanagement.dao;

import com.entities.OrgRefRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-29 17:09:34
 */
@Mapper
public interface OrgRefRoleDao {

	OrgRefRole get(Integer id);
	
	List<OrgRefRole> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrgRefRole orgRefRole);
	
	int update(OrgRefRole orgRefRole);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
