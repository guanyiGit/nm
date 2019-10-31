package com.basicInfo.service;

import com.basicInfo.vo.DogOwnerVO;
import com.entities.TDogOwner;
import com.utils.PageUtils;
import com.utils.R;

import java.io.UnsupportedEncodingException;

public interface DogOwnerService {
	
	/**新增犬主信息
	 * @author ZhongZhong
	 * @param record
	 * @return
	 */
	int insertSelective(TDogOwner record);

	/**
	 * 查看所有犬主信息
	 * @param query
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	PageUtils findDogOwnerList(Integer index,Integer pageSize,String searchKey,Integer orgId,Integer pro) throws UnsupportedEncodingException;
	
	/**
	 * 犬主详情
	 * @param  id 兽医id
	 */
	DogOwnerVO findDogOwnerDetail(Integer id);

	/**
	 * 删除饲草
	 */
	int deleteDogOwner(Integer id);

	/**
	 * 修改犬主
	 */
	int updateDogOwner(TDogOwner dogOwner);

	/**
	 * 根据身份证或者电话号码进行唯一性判断
	 * @param cardNum:犬主身份证号
	 * @param phoneNum:犬主电话 号码
	 */
	int judugeExit(String cardNum, String phoneNum);

	/**
	 * 修改时根据身份证或者电话号码进行唯一性判断
	 * @param id:主键id
	 * @param cardNum:犬主身份证号
	 * @param phoneNum:犬主电话
	 */
	int judugeUpdateExit(Integer id, String cardNum, String phoneNum);


	/**
	 * 修改时根据身份证或者电话号码进行唯一性判断
	 * @param id:主键id
	 * @param cardNum:犬主身份证号
	 * @param phoneNum:犬主电话 号码wxjudugeUpdateExit
	 */
	R wxjudugeUpdateExit(Integer id, String cardNum, String phoneNum);

}
