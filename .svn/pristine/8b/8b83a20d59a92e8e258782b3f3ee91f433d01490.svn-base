package com.devicemanagement.service;


import com.entities.TFenceDef;
import com.entities.TFenceDetail;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-09-30 14:01:54
 */
public interface FenceService {
	List<Map<String,Object> > list (Map<String, Object> map);
	int count(Map<String, Object> map);
	//根据fenceId 查询详情
	List<Map<String,Object>> getFenceDetailByFenceId(Integer id);

	List<TFenceDef>  getAllFences(Map<String, Object> map);

	int deleteFenceById (Integer id);

	int deleteFenceDetailByFenceId (Integer fenceId);

	//  插入围栏
	int saveFence(Map<String,Object> map);
	//批量添加围栏详情信息
	int batchSaveFenceDetail(List<TFenceDetail> list);
}
