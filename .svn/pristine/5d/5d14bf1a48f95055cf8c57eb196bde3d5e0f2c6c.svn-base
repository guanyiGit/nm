package com.epmanagement.service;

import java.util.Date;

import com.entities.TDogAnatomy;
import com.utils.PageUtils;

public interface DogAnatomyService {
	
	/**
	 * 查找犬粪抗原列表
	 * @param index 索引
	 * @param pageSize 页面大小
	 * @param orgId1 州级管理员从下拉框中选择的组织id 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	PageUtils findDogAnatomyList(Integer index, Integer pageSize, Integer orgId1, Date startDate, Date endDate);

	/**
	 * 保存
	 */
	Integer save(TDogAnatomy dogAnatomy);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int remove(Integer id);

}
