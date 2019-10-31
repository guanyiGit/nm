package com.orgmanagement.dao;

import com.entities.ResourceInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-29 16:39:06
 */
@Mapper
public interface ResourceInfoDao {

	ResourceInfo get(Integer id);
	
	List<ResourceInfo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ResourceInfo resourceInfo);
	
	int update(ResourceInfo resourceInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<ResourceInfo> listMenuByUserId(int id);
	//多语言
	List<ResourceInfo> listMenuByUserIdI18n(Map<String,Object> map);

	List<String> listUserPerms(int id);
}
