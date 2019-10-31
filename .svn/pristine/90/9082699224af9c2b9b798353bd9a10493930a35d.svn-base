package com.orgmanagement.service;

import com.entities.OrgInfo;
import com.entities.Tree;
import com.orgmanagement.domain.DeptDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-29 11:36:09
 */
public interface OrgInfoService {

//	Map<String ,String> get(Integer id);
//
//	List<Map<String ,Object>> list(Map<String, Object> map);
//
//	int count(Map<String, Object> map);
//
//	int save(OrgInfo orgInfo);
//
//	int update(OrgInfo orgInfo);
//
//	int remove(Integer id);
//
//	int batchRemove(Integer[] ids);
//
//	Map<String ,String> selectByOrgName(String departName);
//
	List<OrgInfo> orgList();

	DeptDO get(Long deptId);

	List<DeptDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(DeptDO sysDept);

	int update(DeptDO sysDept);

	int remove(Long deptId);

	int batchRemove(Long[] deptIds);

	Tree<DeptDO> getTree();

	boolean checkDeptHasUser(Long deptId);

	List<Tree<OrgInfo>> initOrgSelect (Map<String,Object> map);

	List<Tree<OrgInfo>> initOrgSelect2 ();

	List<Tree<OrgInfo>> initOrgSelect3 (Map<String,Object> map);

	List<OrgInfo> initOrgSelect4(Long deptId);

	List<DeptDO> lists ();





}
