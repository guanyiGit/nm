package com.soholy.service.activemq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.soholy.common.ApplicationContextProvider;
import com.soholy.pojo.aep.rq.BaseAepRq;
import com.soholy.pojo.aep.rq.EntiretyAepRq;
import com.soholy.pojo.aep.vo_v2.TData;
import com.soholy.pojo.aep.vo_v2.TDataGps;
import com.soholy.pojo.aep.vo_v2.TDataWifi;
import com.soholy.service.AepCbService;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Log
public class AmqConsumer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(AmqConsumer.class);

    private EntiretyAepRq input;
    private AepCbService aepCbService;
    private RestTemplate restTemplate;

    private final String wifiConvertUrl = "http://223.221.36.183:10086/convert/wifi/?bssid={BSSID}&rssi={RSSI}";

    public AmqConsumer(String message) {
        try {
            this.input = JSON.parseObject(message, EntiretyAepRq.class);
            this.aepCbService = ApplicationContextProvider.getBean(AepCbService.class);
            this.restTemplate = ApplicationContextProvider.getBean(RestTemplate.class);
        } catch (Exception e) {
            e.printStackTrace();
            log.warning("mq consumer init convert msg err !");
        }

    }

    @Override
    public void run() {
        log.info("start consumer input:"+input);
        try {
            if (null == this.input || input.getCommon() == null) {
                return;
            }

            //转置
            this.reverseData();

            //數據入口前處理
            TData tData = this.dataHandle();

            //保存数据
            aepCbService.saveDatas(Arrays.asList(tData));

            //数据检验（禁养区域检测和低电量 告警）
            checkFence(tData);

        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(e.getMessage());
        }
    }

    private void checkFence(TData tData) {

    }

    /**
     * 组装公共数据
     *
     * @return
     */
    private TData dataHandle() {
        BaseAepRq.Common device = input.getCommon();
        TData d = new TData();

        d.setVersion(input.getVersion());
        d.setDeviceNo(device.getImei());
        d.setCreateDate(LocalDateTime.now());
        d.setDataTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(device.getTime()), ZoneId.systemDefault()));
        d.setId(UUID.randomUUID().toString());
        Float elec_val = device.getElec_val() != null ? device.getElec_val() : 0f;
        d.setQuantity(Double.valueOf(elec_val));
        d.setInfo_type(input.getType());
        d.setVoltage(Double.valueOf(device.getVoltage()));
        d.setUploadTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(device.getTime()), ZoneId.systemDefault()));

        //默认值
        d.setLat(0d);
        d.setLng(0d);
        // gps 1wifi 2 simple 3warn 4 start
        //0混合 1gps 2wifi 3开机 4 省电 5低电告警
        Integer info_type = input.getType() != null ? input.getType() : 0;
        switch (info_type) {
            case 0://0混合
                info_type = 0;
                gpsHandle(input.getLocations(), d);
                wifiHandle(input.getWifis(), d);
                break;
            case 1://1gps
                gpsHandle(input.getLocations(), d);
                info_type = 0;
                break;
            case 2://2wifi
                info_type = 1;
                wifiHandle(input.getWifis(), d);
                break;
            case 3://3开机
                info_type = 4;
                startHandle(device, d);
                break;
            case 4://4省电
                info_type = 2;
                break;
            case 5://5低电告警
                info_type = 3;
                break;
        }
        d.setDataType(info_type);
        return d;
    }

    private void startHandle(BaseAepRq.Common device, TData d) {
        if (device != null && d != null) {
        }
    }

    private void wifiHandle(final List<EntiretyAepRq.AepWifi> wifis, final TData d) {
        if (wifis == null || wifis.size() == 0) return;
        List<TDataWifi> w1 = wifis.stream()
                .filter(x -> x != null)
                .map(x -> {
                    TDataWifi wifi = new TDataWifi();
                    Date dataTime = x.getTime() != null ? new Date(x.getTime()) : null;
                    wifi.setDataTime(dataTime);
                    wifi.setCreationTime(LocalDateTime.now());
                    wifi.setDeviceDataWifiId(UUID.randomUUID().toString());
                    wifi.setDeviceDataId(d.getId());
                    wifi.setDeviceNo(d.getDeviceNo());
                    wifi.setBssid(x.getBssid());
                    wifi.setRssi(x.getRssi());

                    wifi.setMark(0);
                    return wifi;
                }).collect(Collectors.toList());

        //wifi转换   0未转换，1已成功转换，2转换失败
        if ((d.getLng() == null || d.getLng() == 0) && (d.getLat() == null || d.getLat() == 0) &&
                //还没有一条转换就启用转换
                !w1.stream()
                        .filter(blb -> blb != null)
                        .anyMatch(blb -> 1 == blb.getMark())) {
            w1.stream()
                    .filter(x -> x != null)
                    .filter(x -> 0 == x.getMark())
                    .forEach(x -> {
                        try {
                            //转换成功重新赋值   只转换一条
                            String bssid = x.getBssid();
                            Float rssi = x.getRssi();
                            //原始：A80CCA0D270F
                            if (StringUtils.isBlank(bssid) || bssid.trim().length() != 12) {
                                return;
                            }
                            bssid = bssid.trim();
                            StringBuffer sb = new StringBuffer();
                            //每2个一次，添加冒号
                            for (int i = 0; i < bssid.length(); i += 2) {
                                sb.append(bssid.substring(i, i + 2)).append(":");
                            }
                            bssid = sb.toString().substring(0, sb.toString().length() - 1);

                            //例：A8:0C:CA:0D:27:0F
                            if (bssid.length() != 17) {
                                return;
                            }
                            //0未转换，1已成功转换，2转换失败
                            x.setMark(2);

                            Map<String, String> params = new HashMap<String, String>();
                            params.put("BSSID", bssid);
                            params.put("RSSI", String.valueOf(rssi));
                            ResponseEntity<JSONObject> entity = restTemplate.exchange(wifiConvertUrl, HttpMethod.GET, null, JSONObject.class, params);

                            JSONObject body = entity.getBody();
                            if (body != null && 200 == body.getIntValue("code")) {
                                //0 没能转换 remark：1 转换成功 remark：2 转换成功，但是无结果
                                int resMake = body.getIntValue("make");
                                x.setMark(resMake);
                                if (resMake == 1) {
                                    Double latitude = body.getDouble("latitude");
                                    Double longitude = body.getDouble("longitude");
                                    d.setLat(latitude);
                                    d.setLng(longitude);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
        }
        boolean bl = aepCbService.saveWifis(w1);
        if (!bl) {
            logger.warn("wifi数据保存失败！");
        }

    }


    private void gpsHandle(List<EntiretyAepRq.Aeplocation> locations, TData d) {
        if (locations == null || locations.size() == 0) return;
        List<TDataGps> gs = locations.stream()
                .filter(x -> x != null)
                .map(x -> {
                    if (x.getLan() == null) {
                        x.setLan(0f);
                    }
                    if (x.getLng() == null) {
                        x.setLng(0f);
                    }
                    return x;
                })
                .map(x -> {
                    TDataGps gps = new TDataGps();
                    try {
                        gps.setCretion_time(Calendar.getInstance().getTime());
                        Date dataTime = x.getTime() != null ? new Date(x.getTime()) : null;
                        gps.setData_time(dataTime);
                        gps.setGps_record_id(UUID.randomUUID().toString());
                        gps.setLan(x.getLan().doubleValue());
                        gps.setLng(x.getLng().doubleValue());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    return gps;
                }).collect(Collectors.toList());


        //gps赋值数据处理
        if ((d.getLat() == null || d.getLat() == 0) && (d.getLng() == null || d.getLng() == 0)) {
            Optional<TDataGps> first = gs.stream()
                    .filter(x -> x != null)
                    .filter(x -> x.getLan() != null && x.getLan() > 0 && x.getLng() != null && x.getLng() > 0)
                    .findFirst();

            TDataGps orD = first.orElseGet(() -> {
                TDataGps id = new TDataGps();
                id.setLan(0d);
                id.setLng(0d);
                return id;
            });
            d.setLng(orD.getLng().doubleValue());
            d.setLat(orD.getLan().doubleValue());
        }

//        boolean bl = dataService_l2.saveGpss(gs);
//        if (!bl) {
//
//            logger.warn("wifi数据保存失败！");
//        }

    }

    /**
     * 转置
     */
    private void reverseData() {
        List<EntiretyAepRq.Aeplocation> locations = input.getLocations();
        if (locations != null && locations.size() > 0) {
            Collections.reverse(locations);
        }
        List<EntiretyAepRq.AepWifi> wifis = input.getWifis();
        if (wifis != null && wifis.size() > 0) {
            Collections.reverse(wifis);
        }

    }

}
