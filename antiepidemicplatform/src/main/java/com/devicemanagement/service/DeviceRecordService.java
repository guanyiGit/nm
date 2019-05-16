package com.devicemanagement.service;

import com.entities.DeviceRecord;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-30 14:41:44
 */
public interface DeviceRecordService {
	
	Map<String,String> get(Integer id);
	
	List<Map<String,Object>> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeviceRecord deviceRecord);
	
	int update(DeviceRecord deviceRecord);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	Map<String,String> selectByDeviceNo(Map<String, Object> map);

	Map<String,String> selectSiteByDeviceNo(Map<String,Object> map);

	Map<String,String> selectTrailByDeviceNoAndDate(Map<String,Object> map);
}
