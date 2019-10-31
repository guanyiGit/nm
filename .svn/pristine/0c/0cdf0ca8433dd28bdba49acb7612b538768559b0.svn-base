package com.goodsmanagement.service.impl;

import com.entities.DeviceDistributionDO;
import com.goodsmanagement.dao.DeviceDistributionDao;
import com.goodsmanagement.service.DeviceDistributionService;
import com.goodsmanagement.vo.DeviceDistributionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class DeviceDistributionServiceImpl implements DeviceDistributionService {
	@Autowired
	private DeviceDistributionDao deviceDistributionDao;
	
	@Override
	public DeviceDistributionVO get(Integer id){
		return deviceDistributionDao.get(id);
	}
	
	@Override
	public List<DeviceDistributionVO> list(Map<String, Object> map){
		return deviceDistributionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return deviceDistributionDao.count(map);
	}
	
	@Override
	public int save(DeviceDistributionDO deviceDistribution){
		return deviceDistributionDao.save(deviceDistribution);
	}
	
	@Override
	public int update(DeviceDistributionDO deviceDistribution){
		return deviceDistributionDao.update(deviceDistribution);
	}
	
	@Override
	public int remove(Integer id){
		return deviceDistributionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return deviceDistributionDao.batchRemove(ids);
	}
	
}
