package com.soholy.controller;

import com.soholy.service.ConvertService;
import com.soholy.utils.R;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/convert")
@Log
public class ConvertController {

    @Autowired
    private ConvertService convertService;

    /**
     * http://localhost:10086/convert/wifi?bssid=f1:43:47:f6:c6:e1&rssi=475
     * 根据bssid查询wifi地址信息 wgs84
     *
     * @param bssid mac
     * @param rssi  强度
     * @return remark：0 没能转换
     * remark：1 转换成功
     * remark：2 转换成功，但是无结果
     */
    @RequestMapping("/wifi")
    public R Wifi2Gps(@RequestParam String bssid, @RequestParam float rssi) {
        try {
            log.info("convert wifi params: bssid-->" + bssid + ", rssi-->" + rssi);
            return R.ok(convertService.wifiConverToGps(bssid, rssi));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error();
    }


    /**
     * http://localhost:10086/convert/station?mnc=1&lac=4311&ci=20986
     * @param mnc 网络类型：0移动、1联通(电信对应sid)
     * @param lac 小区号(电信对应nid)
     * @param ci  基站号(电信对应bid)
     *            private Double longitude; 经度
     *            <p>
     *            private Double latitude; 纬度
     *            <p>
     *            private int make; 0 待处理  1 成功  2 无结果
     */
    @RequestMapping("/station")
    public R Wifi2Station(@RequestParam String mnc, @RequestParam String lac, @RequestParam String ci) {
        try {
            log.info("convert station params: mnc-->" + mnc + ", lac-->" + lac + ", ci-->" + ci);
            return R.ok(convertService.stationConverToGps(mnc, lac, ci));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error();
    }

}
