package com.goodsmanagement.dao;


import com.entities.DeviceDistributionDO;
import com.goodsmanagement.vo.DeviceDistributionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-05 15:17:22
 */
@Mapper
public interface DeviceDistributionDao {

	DeviceDistributionVO get(Integer id);
	
	List<DeviceDistributionVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(DeviceDistributionDO deviceDistribution);
	
	int update(DeviceDistributionDO deviceDistribution);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
