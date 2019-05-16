package com.devicemanagement.service.impl;

import com.devicemanagement.dao.DeviceInfoDao;
import com.devicemanagement.entities.ImportDevice;
import com.devicemanagement.service.DeviceInfoService;
import com.entities.DeviceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {
	@Autowired
	private DeviceInfoDao deviceInfoDao;
	
	@Override
	public Map<String, String> get(Integer id){
		return deviceInfoDao.get(id);
	}
	
	@Override
	public List<Map<String, Object>> list(Map<String, Object> map){
		return deviceInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return deviceInfoDao.count(map);
	}
	
	@Override
	public int save(DeviceInfo deviceInfo){
		return deviceInfoDao.save(deviceInfo);
	}
	
	@Override
	public int update(DeviceInfo deviceInfo){
		return deviceInfoDao.update(deviceInfo);
	}
	
	@Override
	public int remove(Integer id){
		return deviceInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return deviceInfoDao.batchRemove(ids);
	}

	@Override
	public List<Map<String,Object>>  selectByDeviceNo(Map<String, Object> map) {
		return deviceInfoDao.selectByDeviceNo(map);
	}

	@Override
	public int batchInsert(List<ImportDevice> list) {
		return deviceInfoDao.batchInsert(list);
	}

}
