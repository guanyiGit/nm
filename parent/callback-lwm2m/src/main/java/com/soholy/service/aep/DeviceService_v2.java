package com.soholy.service.aep;


import com.soholy.entity.TDeviceInfo;

import java.util.List;

public interface DeviceService_v2 {

    /**
     * 注册涉笔
     *
     * @param imei        imei
     * @param deviceBrand 品牌
     * @param deviceBatch 批次
     * @return
     */
    boolean register(String imei, String deviceBrand, String deviceBatch);

    /**
     * 注销设备  参数选其一
     *
     * @param deviceIdIot 平台id
     * @param imei
     * @return
     */
    boolean logout(String deviceIdIot, String imei);

    /**
     * 查询设备信息
     *
     * @param id
     * @param type 0:iotid 1:imei
     * @return
     */
    List<TDeviceInfo> findDeviceInifo(String id, int type);
}
