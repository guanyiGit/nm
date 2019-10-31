package com.devicemanagement.service;

import java.util.List;

import com.entities.DeviceRecord;
import com.utils.PageUtils;

public interface PathService {
	
	/**
	 * 查询轨迹犬只列表
	 * @param index:偏移量
	 * @param pageSize:页面大小
	 * searchKey:(溯源id,犬主姓名,手机号码,身份证号)
	 */
	PageUtils findDogList(Integer index, Integer pageSize, String searchKey,String selectedDate);
	
	/**
	 * 获得犬只活动轨迹
	 * @param deviceNo:溯源id
	 * @param selectedDate:选中的日期
	 */
	List<DeviceRecord> getDogPath(String traceId, String selectedDate);

}
