package com.epmanagement.service;

import java.util.Date;

import com.entities.TAntibodyDetection;
import com.utils.PageUtils;

public interface AntibodyDetectionService {
	
	/**
	 * 牛羊免疫抗体列表
	 * @param index 索引
	 * @param pageSize 页面大小
	 * @param orgId1 州级管理员从下拉框中选择的组织id 
	 * @param startDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	PageUtils findAntibodyDetectionList(Integer index, Integer pageSize, Integer orgId1, Date startDate, Date endDate);
	
	/**
	 * 修改
	 */
	int update(TAntibodyDetection antibodyDetection);
	
	/**
	 * 保存
	 */
	int save(TAntibodyDetection antibodyDetection);
	
	/**
	 * 删除
	 * @param id
	 */
	int delete(Integer id);

}
