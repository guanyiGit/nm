package com.goodsmanagement.service.impl;

import com.entities.GoodsInfoDO;
import com.entities.OrgInfo;
import com.entities.Tree;
import com.goodsmanagement.dao.GoodsInfoDao;
import com.goodsmanagement.service.GoodsInfoService;
import com.goodsmanagement.vo.GoodsInfoVO;
import com.orgmanagement.dao.OrgInfoDao;
import com.utils.BuildTree;
import com.utils.ShiroUtils;
import com.utils.orgtills;
import org.apache.velocity.runtime.directive.Break;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {
	@Autowired
	private GoodsInfoDao goodsInfoDao;
	@Autowired
	private OrgInfoDao sysDeptMapper;
	
	@Override
	public GoodsInfoVO get(Integer id){
		Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
		return goodsInfoDao.get(id,type);
	}
	
	@Override
	public List<GoodsInfoVO> list(Map<String, Object> map){
		Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
		map.put("type",type);
        return goodsInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return goodsInfoDao.count(map);
	}
	
	@Override
	public int save(GoodsInfoDO goodsInfo){
		return goodsInfoDao.save(goodsInfo);
	}
	
	@Override
	public int update(GoodsInfoDO goodsInfo){
		return goodsInfoDao.update(goodsInfo);
	}
	
	@Override
	public int remove(Integer id){
		return goodsInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return goodsInfoDao.batchRemove(ids);
	}

	/**
	 * 初始化组织下拉框
	 *
	 * @param map
	 * @return
	 */
	@Override
	public List<Tree<OrgInfo>> initOrgSelect(Map<String, Object> map) {
		List<OrgInfo> orgInfos = sysDeptMapper.initOrgSelect(map);
		Long  deptId = (Long)map.get("orgId");
		Long  pid = (Long)map.get("deptPid");

		List<OrgInfo> depts = orgtills.getDeptsById(orgInfos, deptId.intValue(), new ArrayList<>());

		OrgInfo allOrg = new OrgInfo();
		allOrg.setId(9999);	//构建一个虚拟组织，设置id为-1
		allOrg.setDepartName("全部");
		allOrg.setPid(-1);	//最顶层，没有父亲
		depts.stream().forEach(org ->{
			if(deptId.intValue()==org.getId()){
				org.setPid(9999);
			}
		});
		depts.add(allOrg);
		List<Tree<OrgInfo>> trees = listToTreeList(depts);
		List<Tree<OrgInfo>> list = BuildTree.buildList(trees, "-1");
		return list;
	}

	/**
	 * 初始化组织下拉框（wx）
	 * @param map
	 * @return
	 */
	@Override
	public List<OrgInfo> initOrgSelect2(Map<String, Object> map) {
		List<OrgInfo> orgInfos = sysDeptMapper.initOrgSelect(map);
		  Integer deptId = Integer.parseInt(map.get("orgId").toString());
		List<OrgInfo> depts = orgtills.getDeptsById(orgInfos, deptId, new ArrayList<>());
		return depts;
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
			Map<String, Object> attributes = new HashMap<>(16);
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		return trees;
	}
}
