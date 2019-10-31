package com.basicInfo.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import com.entities.TDrugInfo;
import com.utils.PageUtils;

public interface DrugService {

	
	/**
	 * 新增Pad
	 */
	int addDrug(TDrugInfo drug);

	
	/**
	 * 药品列表
	 * @param  pageNumber:页码
	 * 		   pageSize:页面大小
	 * 		   type:药品类型
	 * @throws UnsupportedEncodingException 
	 */
	PageUtils findDrugList(Integer index, Integer pageSize,Integer type,String searchKey) throws UnsupportedEncodingException;


	/**
	 * 药品详情
	 * @param  id 药品id
	 */
	TDrugInfo findDrugDetail(Integer id);

	
	/**
	 * 删除药品
	 */
	int deleteDrug(Integer id);


	/**
	 * 修改药品
	 */
	int updateDrug(TDrugInfo drugInfo);

//	初始话药品下拉框
	List<TDrugInfo> initDrugSelect(HashMap<String,Object> map);

	
}
