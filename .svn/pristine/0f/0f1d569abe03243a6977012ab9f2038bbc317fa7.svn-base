package com.basicInfo.service;

import com.basicInfo.vo.ProtectorVO;
import com.entities.TProtector;
import com.entities.TSysDict;
import com.utils.PageUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface ProtectorService {
	/**
	 * 新增防疫员
	 * @param protector
	 * @return
	 */
	int addProtector(TProtector protector);

	/**
	 * 删除防疫员
	 * @param id
	 * @return
	 */
	int deleteProtector(Integer id);

	/**
	 * 修改防疫员
	 * @param protector
	 * @return
	 */
	int updateProtector(TProtector protector);
	
	/**
	 * 防疫员详情
	 * @param id
	 * @return
	 */
	ProtectorVO findProtectorDetail(Integer id);

	/**
	 * 防疫员列表
	 * @param pageNumber
	 * @param pageSize
	 * @param orgId
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	PageUtils findProtectorList(Integer index, Integer pageSize, Integer orgId,String searchKey) throws UnsupportedEncodingException;

	//初始话防疫员下拉

	List<Map<String,Object>> initProtectorSel(Map<String,Object> map);

	/**
	 * 查找民族
	 */
	List<TSysDict> findTotalNation();
	/**
	 * 查找民族
	 */
	List<TSysDict> selectEthnic();
	/**
	 * 查找学历
	 */
	List<TSysDict> selectEducationBackground();

	
	/**
	 * 根据身份证或者电话号码进行唯一性判断
	 * @param cardNum:防疫员身份证号
	 * @param phoneNum:防疫员电话 号码
	 */
	int judugeExit(String cardNum, String phoneNum);

	/**
	 * 修改时根据身份证或者电话号码进行唯一性判断
	 * @param id:主键id
	 * @param cardNum:防疫员身份证号
	 * @param phoneNum:防疫员电话 号码
	 */
	int judugeUpdateExit(Integer id, String cardNum, String phoneNum);

	/**
	 * 根据用户id查看防疫员详情
	 * @param  userId 用户id
	 */
	int findProtectorDetailByUserId(Integer userId);

	List<TProtector> initProtectorSel2(Long deptId);

}
