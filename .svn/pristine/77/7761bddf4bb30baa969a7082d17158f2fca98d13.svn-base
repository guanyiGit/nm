package com.epmanagement.service;

import com.entities.CorpseDisposal;
import com.epmanagement.vo.CorpseDisposalVO;
import com.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-28 09:52:35
 */
public interface CorpseDisposalService {
	
	Map<String,String> get(Integer id);
	
	List<CorpseDisposalVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CorpseDisposal corpseDisposal);
	
	int update(CorpseDisposal corpseDisposal);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	Map<String,String> selectByTitle(Map<String,Object> map);
	
	/**
	 * 
	 * @param index 索引
	 * @param pageSize 页面大小
	 * @param orgId 当前登录人部门id
	 * @param searchKey 搜索条件
	 * @return
	 */
	PageUtils findCorpseDisposalList(Integer index, Integer pageSize, String searchKey,Integer orgId,Integer pro);
	
	
	/**
	 * 查找尸体详情
	 * @param id 主键id
	 * @return
	 */
	CorpseDisposalVO getCorpseDetail(Integer id);
	CorpseDisposalVO getCorpseDetaili18n(Map<String, Object> map);
}
