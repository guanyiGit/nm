package com.orgmanagement.service.impl;

import com.entities.OrgInfo;
import com.entities.Tree;
import com.exception.NormalException;
import com.orgmanagement.dao.OrgInfoDao;
import com.orgmanagement.domain.DeptDO;
import com.orgmanagement.service.OrgInfoService;
import com.utils.BuildTree;
import com.utils.ShiroUtils;
import com.utils.orgtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OrgInfoServiceImpl implements OrgInfoService {
//	@Autowired
//	private OrgInfoDao orgInfoDao;
//
//	@Override
//	public Map<String ,String> get(Integer id){
//		return orgInfoDao.get(id);
//	}
//
//	@Override
//	public List<Map<String ,Object>> list(Map<String, Object> map){
//	    //所有组织
//        List<OrgInfo> orgInfos = orgInfoDao.orgList();
//        ArrayList<Map<String, Object>> maps = new ArrayList<>();
//        List<Map<String, Object>> list = orgInfoDao.list(map);
//        list.stream().forEach(org ->{
//            int orgId = Integer.parseInt(org.get("id").toString());
//            List<OrgInfo> orgParents = orgtills.getFatherOrgsByOrgId(orgInfos, orgId, new ArrayList<OrgInfo>());
//            String orgPath = orgParents.stream().map(og -> {
//                return og.getDepartName();
//            }).reduce((a, b) -> {
//                return a + "/" + b;
//            }).get();
//            org.put("orgPath",orgPath);
//            maps.add(org);
//        });
//
//
//        return maps;
//	}
//
//	@Override
//	public int count(Map<String, Object> map){
//		return orgInfoDao.count(map);
//	}
//
//	@Override
//	public int save(OrgInfo orgInfo){
//		return orgInfoDao.save(orgInfo);
//	}
//
//	@Override
//	public int update(OrgInfo orgInfo){
//		return orgInfoDao.update(orgInfo);
//	}
//
//	@Override
//	public int remove(Integer id){
//		return orgInfoDao.remove(id);
//	}
//
//	@Override
//	public int batchRemove(Integer[] ids){
//		return orgInfoDao.batchRemove(ids);
//	}
//
//	@Override
//	public Map<String, String> selectByOrgName(String departName) {
//		return orgInfoDao.selectByOrgName(departName);
//	}
//

	@Autowired
	private OrgInfoDao sysDeptMapper;

	@Override
	public List<OrgInfo> orgList() {
		return sysDeptMapper.orgList();
	}

	@Override
	public DeptDO get(Long deptId){
		return sysDeptMapper.get(deptId);
	}

	@Override
	public List<DeptDO> list(Map<String, Object> map){
        Long  deptId = (Long)map.get("orgId");
        List<DeptDO> list = sysDeptMapper.list(map);
        int type = ShiroUtils.getUser().getRoles().get(0).getType();
        if(deptId == null && type == 1) {
            deptId = 0L;
        }
        List<DeptDO> depts = orgtills.getDeptIdsById(list, deptId, new ArrayList<>());
        return depts;
	}

	@Override
	public int count(Map<String, Object> map){
		return sysDeptMapper.count(map);
	}

	@Override
	public int save(DeptDO sysDept){


		return sysDeptMapper.save(sysDept);
	}

	@Override
	public int update(DeptDO sysDept){
		return sysDeptMapper.update(sysDept);
	}

	@Override
	public int remove(Long deptId){
		return sysDeptMapper.remove(deptId);
	}

	@Override
	public int batchRemove(Long[] deptIds){
		return sysDeptMapper.batchRemove(deptIds);
	}

	@Override
	public Tree<DeptDO> getTree() {
		List<Tree<DeptDO>> trees = new ArrayList<Tree<DeptDO>>();
		List<DeptDO> sysDepts = sysDeptMapper.list(new HashMap<String,Object>(16));
		for (DeptDO sysDept : sysDepts) {
			Tree<DeptDO> tree = new Tree<DeptDO>();
			tree.setId(sysDept.getDeptId().toString());
			tree.setParentId(sysDept.getParentId().toString());
			tree.setText(sysDept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<DeptDO> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public boolean checkDeptHasUser(Long deptId) {
		// TODO Auto-generated method stub
		//查询部门以及此部门的下级部门
		int result = sysDeptMapper.getDeptUserNumber(deptId);
		return result==0?true:false;
	}



	@Override
	public List<Tree<OrgInfo>> initOrgSelect(Map<String, Object> map) {
		List<OrgInfo> orgInfos = sysDeptMapper.initOrgSelect(map);
		Long  deptId = (Long)map.get("orgId");
		Long  pid = (Long)map.get("deptPid");
		int  roleType = (int)map.get("roleType");
		List<OrgInfo> depts = null;
		List<Tree<OrgInfo>> list = null;
		try {
			depts = orgtills.getDeptsById(orgInfos, deptId.intValue(), new ArrayList<>());
			List<Tree<OrgInfo>> trees = listToTreeList(depts);
			list = BuildTree.buildList(trees, pid.toString());
			return list;
		}catch (NullPointerException e){
			if(roleType==1){//判断如果当前用户为超级管理员则 返回左右组织
				depts = orgInfos;
			}
			List<Tree<OrgInfo>> trees = listToTreeList(depts);
			list = BuildTree.buildList(trees, "0");
			throw  new NormalException("用户为admin");
		}finally {
			return  list;
		}
	}

	/**
	 * 查询组织
	 */
	public List<Tree<OrgInfo>> getAllMenusByUserId(Map<String,Object> map) {
		List<Tree<OrgInfo>> trees = new ArrayList<>();
		List<OrgInfo> orgInfos = sysDeptMapper.initOrgSelect(map);
		for (OrgInfo org : orgInfos) {
			Tree<OrgInfo> tree = new Tree<>();
			tree.setId(org.getId().toString());
			tree.setParentId(org.getPid().toString());
			tree.setText(org.getDepartName());
			Map<String, Object> attributes = new HashMap<>(16);
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		return trees;
	}

    public List<Tree<OrgInfo>> listToTreeList(List<OrgInfo> orgInfos) {
        List<Tree<OrgInfo>> trees = new ArrayList<>();
//		List<OrgInfo> orgInfos = sysDeptMapper.initOrgSelect(map);
        for (OrgInfo org : orgInfos) {
            Tree<OrgInfo> tree = new Tree<>();
            tree.setId(org.getId().toString());
            tree.setParentId(org.getPid().toString());
            tree.setText(org.getDepartName());
            tree.setAreaName(org.getAreaName());
            tree.setAreaId(org.getAreaId());
            Map<String, Object> attributes = new HashMap<>(16);
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        return trees;
    }


	@Override
	public List<Tree<OrgInfo>> initOrgSelect2() {
        Long orgId = ShiroUtils.getUser().getDeptId();
        Long pid = ShiroUtils.getUser().getPid();
		List<OrgInfo> orgInfos = sysDeptMapper.initOrgSelect2();
		List<OrgInfo> depts = orgtills.getDeptsById(orgInfos, orgId.intValue(), new ArrayList<>());
		List<Tree<OrgInfo>> trees = listToTreeList(depts);
		List<Tree<OrgInfo>> list = BuildTree.buildList(trees, pid.toString());
		return list;
	}

	@Override
	public List<Tree<OrgInfo>> initOrgSelect3(Map<String, Object> map) {
		List<OrgInfo> orgInfos = sysDeptMapper.initOrgSelect(map);
		Long  deptId = (Long)map.get("orgId");
		Long  pid = (Long)map.get("deptPid");
		int  roleType = (int)map.get("roleType");

		List<OrgInfo> depts = orgtills.getChildren(orgInfos, deptId.intValue(), new ArrayList<>());
//		for (OrgInfo orgInfo:
//				depts) {
//			if(orgInfo.getId().intValue() == deptId){ 	//排除本级
//				depts.remove(orgInfo);
//				break;
//			}
//		}
		List<Tree<OrgInfo>> trees = listToTreeList(depts);
		List<Tree<OrgInfo>> list = BuildTree.buildList(trees, deptId.toString());
		return list;
	}

	@Override
	public List<OrgInfo> initOrgSelect4(Long deptId) {
		return sysDeptMapper.initOrgSelect4(deptId);
	}

	@Override
    public List<DeptDO> lists() {
       return  sysDeptMapper.list(new HashMap<>());
    }
}

