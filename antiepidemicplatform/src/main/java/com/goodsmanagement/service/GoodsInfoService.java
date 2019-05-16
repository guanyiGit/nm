package com.goodsmanagement.service;

import com.entities.GoodsInfoDO;
import com.entities.OrgInfo;
import com.entities.Tree;
import com.goodsmanagement.vo.GoodsInfoVO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-05 15:17:41
 */
public interface GoodsInfoService {
	
	GoodsInfoVO get(Integer id);
	
	List<GoodsInfoVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GoodsInfoDO goodsInfo);
	
	int update(GoodsInfoDO goodsInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	/**
	 * 初始化组织下拉框
	 * @param map
	 * @return
	 */
	List<Tree<OrgInfo>> initOrgSelect (Map<String,Object> map);

	//初始化组织下拉框（wx）
	List<OrgInfo> initOrgSelect2(Map<String, Object> map);
}
