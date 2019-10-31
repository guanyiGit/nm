package com.basicInfo.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.entities.TCooperation;
import com.utils.PageUtils;

public interface CooperationService {

	/**
	 * 添加合作社
	 * @param cooperation
	 * @return
	 */
	int addCooperation(TCooperation cooperation);

	
	/**
	 * 合作社列表
	 * @param pageNumber
	 * @param pageSize
	 * @param orgId
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	PageUtils findCooperationList(Integer index, Integer pageSize, Integer orgId,String searchKey) throws UnsupportedEncodingException;

	/**
	 * 合作社详情
	 * @param id
	 * @return
	 */
	TCooperation findCooperationDetail(Integer id);

	
	/**
	 * 根据
	 * @param cooperation
	 * @return
	 */
	int deleteCooperation(Integer id);

	/**
	 * 修改合作社
	 * @param id
	 * @return
	 */
	int updateCooperation(TCooperation cooperation);

	/**
	 * 根据当前登录人的区域id查找所属的合作社
	 */
	List<TCooperation> findCooperationByAreaId(Integer areaId);

}
