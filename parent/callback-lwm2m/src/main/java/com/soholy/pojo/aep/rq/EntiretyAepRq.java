package com.soholy.pojo.aep.rq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntiretyAepRq extends BaseAepRq {

    /**
     * gps位置信息,多组
     */
    private List<Aeplocation> locations;
    /**
     * 步数信息,多组
     */
//    private List<AepStepNum> step_nums;
    /**
     * wifi信息,多组
     */
    private List<AepWifi> wifis;
    /**
     * 基站信息,多组
     */
//    private List<AepStation> stations;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Aeplocation {
        /**
         * 经度float32,非空,
         */
        private Float lng;
        /**
         * 纬度,非空,float32
         */
        private Float lan;
        /**
         * 数据获取时间,非空,int64
         */
        private Long time;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AepStepNum {
        /**
         * 步数,int32
         */
        private Integer val;
        /**
         * 计步开始时间,非空,int64
         */
        private Long start_time;

        /**
         * 计步结束时间,非空,int64
         */
        private Long time;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AepWifi {
        /**
         * 无线名称,string
         */
        private String ssid;
        /**
         * mac地址,非空,string
         */
        private String bssid;
        /**
         * 信号强度,float32
         */
        private Float rssi;
        /**
         * 数据获取时间,非空,int64
         */
        private Long time;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AepStation {

        /**
         * 网络类型：0移动、1联通(电信对应sid),非空,string
         */
        private String mnc;
        /**
         * 小区号(电信对应nid),非空,string
         */
        private String lac;
        /**
         * 基站号(电信对应bid),非空,string
         */
        private String ci;
        /**
         * 基站信息获取时间,非空,int64
         */
        private Long time;
    }
}
