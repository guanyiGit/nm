package com.soholy.service;

import com.soholy.entity.ConvertResult;

public interface ConvertService {


    /**
     * 根据bssid查询wifi地址信息 wgs84
     *
     * @param bssid mac
     * @param rssi  强度
     * @return remark：0 没能转换
     * remark：1 转换成功
     * remark：2 转换成功，但是无结果
     */
    ConvertResult wifiConverToGps(String bssid, Float rssi);



    /**
     * @param mnc  网络类型：0移动、1联通(电信对应sid)
     * @param lac  小区号(电信对应nid)
     * @param ci   基站号(电信对应bid)
     *             private Double longitude; 经度
     *             <p>
     *             private Double latitude; 纬度
     *             <p>
     *             private int make; 0 待处理  1 成功  2 无结果
     */
    ConvertResult stationConverToGps(String mnc, String lac, String ci);

}
