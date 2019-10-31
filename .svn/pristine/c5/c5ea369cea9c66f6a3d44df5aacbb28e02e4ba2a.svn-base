package com.epmanagement.service;

import java.util.Date;

import com.entities.TInfectionInfo;
import com.utils.PageUtils;

public interface InfectionInfoService {
	/**
	 * 牛羊感染病变列表
	 * @param index 索引
	 * @param pageSize 页面大小
	 * @param orgId1 州级管理员从下拉框中选择的组织id 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	PageUtils findInfectionInfoList(Integer index, Integer pageSize, Integer orgId1, Date startDate, Date endDate);
	
	/**
	 * 修改
	 */
	int update(TInfectionInfo tInfectionInfo);
	/**
	 * 新增
	 */
	int save(TInfectionInfo tInfectionInfo);
	/**
	 * 删除
	 */
	int delete(Integer id);

}
