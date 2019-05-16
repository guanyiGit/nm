package com.epmanagement.service;

import java.util.Date;
import java.util.List;

import com.entities.CorpseDisposal;
import com.entities.OrgInfo;
import com.entities.TManureAntigen;
import com.utils.PageUtils;

public interface ManureAntigenService {
	
	/**
	 * 查找犬粪抗原列表
	 * @param index 索引
	 * @param pageSize 页面大小
	 * @return
	 */
	PageUtils findManureAntigenList(Integer index, Integer pageSize,Integer orgId1,Date startDate,Date endDate);
	
	/**
	 * 查看本州和下属县级组织
	 */
	List<OrgInfo> findOrgList();
	
	/**
	 * 新增犬粪抗原
	 * @param manureAntigen
	 * @return
	 */
	Integer save(TManureAntigen manureAntigen);
	
	/**
	 * 修改
	 */
	Integer update(TManureAntigen manureAntigen);
	
	/**
	 * 删除
	 */
	Integer remove(Integer id);

}
