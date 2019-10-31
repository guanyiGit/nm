package com.soholy.cb.service.convert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface WifiConvertService {


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


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class ConvertResult {

        private Double longitude;

        private Double latitude;

        private Integer make;
    }

}
