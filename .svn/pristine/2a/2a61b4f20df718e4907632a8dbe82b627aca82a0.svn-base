package com.publicitytraining.service;

import com.entities.ActivityInfoDO;
import com.epmanagement.vo.MediaUrl;
import com.publicitytraining.vo.ActivityInfoVO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-11-05 15:17:03
 */
public interface ActivityInfoService {

	ActivityInfoVO get(Integer id);

	List<MediaUrl> getMediaUrlById(Map<String,Object> map);
	
	List<ActivityInfoVO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ActivityInfoDO activityInfo);
	
	int update(ActivityInfoDO activityInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	ActivityInfoVO getActivityDetail(Integer id);
}
