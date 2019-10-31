package com.soholy.service;

import com.alibaba.fastjson.JSONObject;
import com.soholy.pojo.aep.vo_v2.TData;
import com.soholy.pojo.aep.vo_v2.TDataWifi;

import java.util.List;

public interface AepCbService {

    void deviceDatasChangedMsg_v2(JSONObject json);

    /**
     * 保存数据
     *
     * @param data
     */
    <T extends TData> boolean saveDatas(List<T> data);

    /**
     * 保存wifi数据
     * @param w1
     * @return
     */
    boolean saveWifis(List<TDataWifi> w1);
}
