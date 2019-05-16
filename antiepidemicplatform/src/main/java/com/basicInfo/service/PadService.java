package com.basicInfo.service;

import com.entities.TPadInfo;
import com.utils.PageUtils;


public interface PadService {

	/**
	 * 新增Pad
	 */
	int addPad(TPadInfo pad);

	/**
	 * Pad列表
	 * @param  pageNumber:页码
	 * 		   pageSize:页面大小
	 * 		   orgId:部门id
	 */
	PageUtils findPadList(Integer index, Integer pageSize, Integer orgId,String searchKey);

	/**
	 * 药品详情
	 * @param  id 药品id
	 */
	TPadInfo findPadDetail(Integer id);

	
	/**
	 * 删除Pad
	 */
	int deletePad(Integer id);

	/**
	 * 修改药品
	 */
	int updatePad(TPadInfo padInfo);
	
	
}
