package com.devicemanagement.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.devicemanagement.dao.FenceDao;
import com.devicemanagement.service.FenceService;
import com.entities.TFenceDef;
import com.entities.TFenceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FenceServiceImpl implements FenceService {

    @Autowired
    private FenceDao fenceDao;

    @Override
    public List<Map<String, Object>> list(Map<String, Object> map) {
        return fenceDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return fenceDao.count(map);
    }

    @Override
    public List<Map<String, Object>> getFenceDetailByFenceId(Integer id) {
        return fenceDao.getFenceDetailByFenceId(id);
    }

    @Override
    public List<TFenceDef> getAllFences(Map<String, Object> map) {
        return fenceDao.getAllFences(map);
    }

    @Transactional
    @Override
    public int deleteFenceById(Integer id) {
        fenceDao.deleteFenceDetailByFenceId(id);
         return  fenceDao.deleteFenceById(id);
    }

    @Override
    public int deleteFenceDetailByFenceId(Integer fenceId) {
        return fenceDao.deleteFenceDetailByFenceId(fenceId);
    }

    @Transactional
    @Override
    public int saveFence(Map<String,Object> map) {
        Date currDate = new Date();
        String paths = map.get("paths").toString();
        JSONArray points = JSONObject.parseArray(paths);
        String fenceName = map.get("fenceName").toString();
        String fenceNo = map.get("fenceNo").toString();

        String userId = map.get("userId").toString();
        String deptId = map.get("deptId").toString();
        String areaId = map.get("areaId").toString();

        TFenceDef fence = new TFenceDef();
        fence.setFence_type(1);
        fence.setCreate_by(Integer.parseInt(userId));
        fence.setStatus(1);
        fence.setArea_id(Integer.parseInt(areaId));
        fence.setCreate_date(currDate);
        fence.setFence_name(fenceName);
        fence.setFence_no(fenceNo);
        fence.setOrg_id(Integer.parseInt(deptId));
        fenceDao.saveFence(fence);

        Integer fenceId = fence.getId();
        //批量保存围栏详情
        ArrayList<TFenceDetail> list = new ArrayList<>();
        for (Object point : points) {
            JSONArray lng_lat = JSONObject.parseArray(point.toString());
            double lng = Double.valueOf(lng_lat.get(0).toString());
            double lat = Double.valueOf(lng_lat.get(1).toString());
            TFenceDetail fd = new TFenceDetail();
            fd.setFenceId(fenceId);
            fd.setLng(lng);
            fd.setLat(lat);
            fd.setCreateDate(currDate);
            list.add(fd);
        }
        return fenceDao.batchSaveFenceDetail(list);
    }

    @Override
    public int batchSaveFenceDetail(List<TFenceDetail> list) {
        return fenceDao.batchSaveFenceDetail(list);
    }
}
