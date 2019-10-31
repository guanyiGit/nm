package com.devicemanagement.dao;


import com.entities.TFenceDef;
import com.entities.TFenceDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FenceDao {
    List<Map<String,Object> > list (Map<String, Object> map);
    int count(Map<String, Object> map);
    //根据fenceId 查询详情
    List<Map<String,Object>> getFenceDetailByFenceId(Integer id);
    List<TFenceDef>  getAllFences(Map<String, Object> map);

    int deleteFenceById (Integer id);

    int deleteFenceDetailByFenceId (Integer fenceId);

    //  插入围栏
    int saveFence(TFenceDef fence);
    //批量添加围栏详情信息
    int batchSaveFenceDetail(List<TFenceDetail> list);
}

