package com.devicemanagement.service.impl;

import com.devicemanagement.dao.DeviceRecordDao;
import com.devicemanagement.service.DeviceRecordService;
import com.entities.DeviceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class DeviceRecordServiceImpl implements DeviceRecordService {
	@Autowired
	private DeviceRecordDao deviceRecordDao;
	
	@Override
	public Map<String, String> get(Integer id){
		return deviceRecordDao.get(id);
	}
	
	@Override
	public List<Map<String, Object>> list(Map<String, Object> map){
		return deviceRecordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return deviceRecordDao.count(map);
	}
	
	@Override
	public int save(DeviceRecord deviceRecord){
		return deviceRecordDao.save(deviceRecord);
	}
	
	@Override
	public int update(DeviceRecord deviceRecord){
		return deviceRecordDao.update(deviceRecord);
	}
	
	@Override
	public int remove(Integer id){
		return deviceRecordDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return deviceRecordDao.batchRemove(ids);
	}

	@Override
	public Map<String, String> selectByDeviceNo(Map<String, Object> map) {
		return deviceRecordDao.selectByDeviceNo(map);
	}

	@Override
	public Map<String, String> selectSiteByDeviceNo(Map<String, Object> map) {
		return deviceRecordDao.selectSiteByDeviceNo(map);
	}

	@Override
	public Map<String, String> selectTrailByDeviceNoAndDate(Map<String, Object> map) {
		return deviceRecordDao.selectTrailByDeviceNoAndDate(map);
	}

}
