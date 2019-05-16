package com.orgmanagement.service;

import com.entities.AreaInfo;
import com.entities.Tree;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-29 13:41:07
 */
public interface AreaInfoService {
	
	AreaInfo get(Integer id);
	
	List<AreaInfo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AreaInfo areaInfo);
	
	int update(AreaInfo areaInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<AreaInfo> getList();

	List<Tree<AreaInfo>> initAreaSelect(Map<String, Object> map);

	/**
	 * @param level 区域等级
	 * 查询城市列表
	 */
	List<AreaInfo> findCityList(Integer level);

	/**
	 * 根据当前区域id查找直接子级区域
	 */
	List<AreaInfo> getChildrenList(Integer pId);

	String findAreas(Integer town,List<AreaInfo> list);



}
