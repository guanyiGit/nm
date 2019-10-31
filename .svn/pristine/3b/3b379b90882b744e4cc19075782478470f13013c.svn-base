package com.publicitytraining.dao;

import com.entities.ActivityInfoDO;
import com.epmanagement.vo.MediaUrl;
import com.publicitytraining.vo.ActivityInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-05 15:17:03
 */
@Mapper
public interface ActivityInfoDao {

	ActivityInfoVO get(Integer id);

	List<MediaUrl> getMediaUrlById(Map<String,Object> map);
	
	List<ActivityInfoVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ActivityInfoDO activityInfo);
	
	int update(ActivityInfoDO activityInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
