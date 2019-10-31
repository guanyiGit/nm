package com.soholy.pojo.aep.rq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseAepRq<T> implements Serializable {


    /**
     *     0:混合数据包                   (locations + locations + wifis)  
     *     1:gps位置                     (common + locations)  
     *     2:wifi数据                    (common + wifis)  
     *     3:设备开机数据                 (common)  
     *     4:省电模式下的心跳数据包        (common)  
     *     5:设备电量不足告警              (common) 
     */
    private Integer type;
    /**
     * 协议版本号,固定2.0,string 
     */
    private String version = Float.valueOf(2.0f).toString();
    /**
     * 设备公共数据包(开机数据，省电数据，告警数据)，非空，单组
     */
    private Common common;

    /**
     * 自定义
     */
    private T t;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Common {
        /**
         * ：设备imei号,非空,string
         */
        private String imei;
        /**
         * :电量,非空,float32
         */
        private Float elec_val;
        /**
         * :电压（单位mv）,int32
         */
        private Float voltage;
        /**
         * :厂商编号,非空,string
         */
        private String fact;
        /**
         * :产品型号,非空,string
         */
        private String model;
        /**
         * ：上传时间,非空,int64
         */
        private Long time;
    }

}
