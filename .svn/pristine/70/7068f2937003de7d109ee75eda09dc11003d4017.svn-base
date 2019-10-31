package com.basicInfo.service;

import java.io.UnsupportedEncodingException;

import org.apache.ibatis.annotations.Mapper;

import com.entities.TForageInfo;
import com.utils.PageUtils;

public interface ForageService {
	
	/**
	 * 新增饲草
	 * @param forage
	 * @return
	 */
	int addForage(TForageInfo forage);
	
	/**
	 * 饲草列表
	 * @param  pageNumber:页码
	 * 		   pageSize:页面大小
	 * 		   orgId:当前登录人部门id
	 * @throws UnsupportedEncodingException 
	 */
	PageUtils findForageList(Integer index, Integer pageSize,String searchKey) throws UnsupportedEncodingException;

	
	/**
	 * 饲草详情
	 * @param  id 兽医id
	 */
	TForageInfo findForageDetail(Integer id);

	/**
	 * 删除饲草
	 */
	int deleteForage(Integer id);

	/**
	 * 修改饲草
	 */
	int updateForage(TForageInfo forageInfo);

}
