package com.orgmanagement.dao;

import com.entities.OrgInfo;
import com.orgmanagement.domain.DeptDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-29 11:36:09
 */
@Mapper
public interface OrgInfoDao {

//	Map<String,String> get(Integer id);
//
//	List<Map<String,Object>> list(Map<String, Object> map);
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
//	/**
//	 * 查询所有组织信息
//	 * @return
//	 */
	List<OrgInfo> orgList();

	DeptDO get(Long deptId);

	List<DeptDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(DeptDO dept);

	int update(DeptDO dept);

	int remove(Long deptId);

	int batchRemove(Long[] deptIds);

	Long[] listParentDept();

	int getDeptUserNumber(Long deptId);

	List<OrgInfo>  initOrgSelect (Map<String,Object> map);

	List<OrgInfo> initOrgSelect2();

	List<OrgInfo> initOrgSelect4(Long deptId);

	List<OrgInfo> getChildrenOrg(Long deptId);

}
