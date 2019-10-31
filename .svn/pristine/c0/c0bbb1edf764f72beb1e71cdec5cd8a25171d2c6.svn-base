package com.orgmanagement.service.impl;

import com.entities.ResourceInfo;
import com.entities.Tree;
import com.orgmanagement.dao.ResourceInfoDao;
import com.orgmanagement.service.ResourceInfoService;
import com.utils.BuildTree;
import com.utils.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ResourceInfoServiceImpl implements ResourceInfoService {
	@Autowired
	private ResourceInfoDao resourceInfoDao;
	
	@Override
	public ResourceInfo get(Integer id){
		return resourceInfoDao.get(id);
	}
	
	@Override
	public List<ResourceInfo> list(Map<String, Object> map){
		return resourceInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return resourceInfoDao.count(map);
	}
	
	@Override
	public int save(ResourceInfo resourceInfo){
		return resourceInfoDao.save(resourceInfo);
	}
	
	@Override
	public int update(ResourceInfo resourceInfo){
		return resourceInfoDao.update(resourceInfo);
	}
	
	@Override
	public int remove(Integer id){
		return resourceInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return resourceInfoDao.batchRemove(ids);
	}

	@Override
	public List<Tree<ResourceInfo>> listMenuTree(int id) {
        List<Tree<ResourceInfo>> trees = getAllMenusByUserId(id);
        List<Tree<ResourceInfo>> collect = trees.stream().distinct().collect(Collectors.toList());
        // 默认顶级菜单为０，根据数据库实际情况调整
		List<Tree<ResourceInfo>> list = BuildTree.buildList(collect, "0");
		return list;
	}



	/**
	 * 查询用户的所有资源菜单
	 */
	public List<Tree<ResourceInfo>> getAllMenusByUserId(int id) {
		List<Tree<ResourceInfo>> trees = new ArrayList<Tree<ResourceInfo>>();
		//多语言
        Map<String,Object> map = new HashMap<>();
        Object type = ShiroUtils.getSubjct().getSession().getAttribute("type");
        map.put("id",id);
        map.put("type",type);
//		List<ResourceInfo> menuDOs = resourceInfoDao.listMenuByUserId(id);
		List<ResourceInfo> menuDOs = resourceInfoDao.listMenuByUserIdI18n(map);
		for (ResourceInfo sysMenuDO : menuDOs) {
			Tree<ResourceInfo> tree = new Tree<ResourceInfo>();
			tree.setId(sysMenuDO.getId().toString());
			tree.setParentId(sysMenuDO.getPid().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getUrl());
			attributes.put("icon", sysMenuDO.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		return trees;
	}

    /**
     *
     *
     */

    public  List<Tree<ResourceInfo>> getMenusById(int userId,String menuId){
        List<Tree<ResourceInfo>> trees = getAllMenusByUserId(userId);
        List<Tree<ResourceInfo>> allChild = BuildTree.getAllChild(trees, menuId).getChildren();
        return  allChild;
    }

	@Override
	public Set<String> listPerms(int userId) {
		List<String> perms = resourceInfoDao.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for (String perm : perms) {
			if (StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}

}
