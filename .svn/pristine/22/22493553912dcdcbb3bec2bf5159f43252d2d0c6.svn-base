package com.devicemanagement.service;


import com.devicemanagement.entities.ImportDevice;
import com.entities.DeviceInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-30 14:01:54
 */
public interface DeviceInfoService {
	
	Map<String,String> get(Integer id);
	
	List<Map<String,Object>> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeviceInfo deviceInfo);
	
	int update(DeviceInfo deviceInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<Map<String,Object>> selectByDeviceNo(Map<String,Object> map);

	int batchInsert(@Param("list") List<ImportDevice> list);
}
