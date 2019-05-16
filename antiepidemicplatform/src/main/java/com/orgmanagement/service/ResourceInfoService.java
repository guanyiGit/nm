package com.orgmanagement.service;


import com.entities.ResourceInfo;
import com.entities.Tree;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-29 16:39:06
 */
public interface ResourceInfoService {
	
	ResourceInfo get(Integer id);
	
	List<ResourceInfo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ResourceInfo resourceInfo);
	
	int update(ResourceInfo resourceInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	//	根据用户id查询菜单项
	List<Tree<ResourceInfo>> listMenuTree(int id);
	//根据用户id查询资源权限
	Set<String> listPerms(int userId);

	List<Tree<ResourceInfo>> getMenusById(int userId,String menuId);
}
