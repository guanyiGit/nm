package com.orgmanagement.service.impl;

import com.entities.AreaInfo;
import com.entities.Tree;
import com.orgmanagement.dao.AreaInfoDao;
import com.orgmanagement.service.AreaInfoService;
import com.utils.BuildTree;
import com.utils.areaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class AreaInfoServiceImpl implements AreaInfoService {
	@Autowired
	private AreaInfoDao areaInfoDao;
	
	@Override
	public AreaInfo get(Integer id){
		return areaInfoDao.get(id);
	}
	
	@Override
	public List<AreaInfo> list(Map<String, Object> map){
		return areaInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return areaInfoDao.count(map);
	}
	
	@Override
	public int save(AreaInfo areaInfo){
		return areaInfoDao.save(areaInfo);
	}
	
	@Override
	public int update(AreaInfo areaInfo){
		return areaInfoDao.update(areaInfo);
	}
	
	@Override
	public int remove(Integer id){
		return areaInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return areaInfoDao.batchRemove(ids);
	}

	@Override
	public List<AreaInfo> getList() {
		return areaInfoDao.getList();
	}

	@Override
	public  List<Tree<AreaInfo>> initAreaSelect(Map<String, Object> map) {
        List<Tree<AreaInfo>> trees = getAllMenusByUserId(map);
        List<Tree<AreaInfo>> list = BuildTree.buildList(trees, "0");
        return list;
	}

    public List<Tree<AreaInfo>> getAllMenusByUserId(Map<String, Object> map) {
        List<Tree<AreaInfo>> trees = new ArrayList<>();
        List<AreaInfo> areaInfos = areaInfoDao.initAreaSelect(map);
        for (AreaInfo area : areaInfos) {
            Tree<AreaInfo> tree = new Tree<>();
            tree.setId(area.getId().toString());
            tree.setParentId(area.getPid().toString());
            tree.setText(area.getName());
            Map<String, Object> attributes = new HashMap<>(16);
            tree.setAttributes(attributes);
            trees.add(tree);
        }
        return trees;
    }
    
    /**
	 * @param level 区域等级
	 * 查询城市列表
	 */
	@Override
	public List<AreaInfo> findCityList(Integer level) {
		return areaInfoDao.findCityList(level);
	}

	/**
	 * 根据当前区域id查找直接子级区域
	 */
	@Override
	public List<AreaInfo> getChildrenList(Integer pId) {
		return areaInfoDao.getChildrenList(pId);
	}

	//获取所属乡镇
	@Override
	public String findAreas(Integer town,List<AreaInfo> list) {
			List<AreaInfo> areaInfos = areaUtils.areaReverse(list, town, new ArrayList<AreaInfo>());
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i < areaInfos.size(); i++) {
				if(i != areaInfos.size()) {
					sb.append(areaInfos.get(i).getName());
				}
			}
		return sb.toString();
	}

}
