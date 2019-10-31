package com.soholy.service.impl;

import com.alibaba.fastjson.JSON;
import com.soholy.entity.ConvertResult;
import com.soholy.entity.StationConverToGpsResult;
import com.soholy.entity.WifiConvertGpsResult;
import com.soholy.entity.po.Station;
import com.soholy.entity.po.StationExample;
import com.soholy.entity.po.Wifi;
import com.soholy.entity.po.WifiExample;
import com.soholy.mapper.StationMapper;
import com.soholy.mapper.WifiMapper;
import com.soholy.service.ConvertService;
import com.soholy.utils.HttpClientUtil;
import com.soholy.utils.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class ConvertServiceImpl implements ConvertService {


    private static final Logger logger = LoggerFactory.getLogger(ConvertServiceImpl.class);

    @Autowired
    private WifiMapper wifiMapper;
    @Autowired
    private StationMapper stationMapper;

    //WIFI热点位置数据
    // mac	  string	是	WIFI热点的MAC地址(BSSID) （00:87:36:05:5d:ea）
    // coord  string	否	坐标类型(wgs84/gcj02/bd09)，默认wgs84
    // output  string	否	返回格式(csv/json/xml)，默认csv

    @Value("${ConvertGps.url}")
    private String url;

    @Override
    public ConvertResult wifiConverToGps(String bssid, Float rssi) {
        ConvertResult rt = new ConvertResult();
        rt.setMake(2);
        if (StringUtils.isNotBlank(bssid)) {
            try {
                Wifi wifi = new Wifi();
                wifi.setBssid1(bssid);
                wifi.setRssi1(rssi);
                wifi.setPriId(UUID.randomUUID().toString());
                wifi.setUpdateTime(Calendar.getInstance().getTime());
                wifi.setCreationTime(Calendar.getInstance().getTime());

                //查
                ConvertResult cvrDb = this.findWifi(bssid);
                if (cvrDb != null) {
                    return cvrDb;
                }

                //没有查询调用接口 保存数据 再返回
                WifiConvertGpsResult wifiCvr = this.wifiConvertGps(bssid);
                if (wifiCvr != null) {
                    //1 成功  2 无结果
                    int make = wifiCvr != null && wifiCvr.getErrcode() == 0 ? 1 : 2;
                    wifi.setRemark(make);
                    wifi.setAddress(wifiCvr.getAddress());
                    wifi.setLatitude(wifiCvr.getLat());
                    wifi.setLongitude(wifiCvr.getLon());
                    wifi.setRssi1(wifiCvr.getRadius());

                    //存
                    this.saveWifi(wifi);
                }
            } finally {
            }
        }
        return rt;

    }

    private ConvertResult findWifi(String bssid) {
        if (StringUtils.isNotBlank(bssid)) {
            try {
                WifiExample example = new WifiExample();
                example.createCriteria().andBssid1EqualTo(bssid).andRemarkBetween(1, 2);//启用的,无结果的
                WifiExample.Criteria equal2 = example.createCriteria().andBssid2EqualTo(bssid).andRemarkBetween(1, 2);
                example.or(equal2);
                WifiExample.Criteria equal3 = example.createCriteria().andBssid3EqualTo(bssid).andRemarkBetween(1, 2);
                example.or(equal3);
                List<Wifi> wifis = wifiMapper.selectByExample(example);
                if (wifis != null && wifis.size() >= 1) {
                    Wifi wifi = wifis.get(0);
                    return new ConvertResult(wifi.getLongitude(), wifi.getLatitude(), wifi.getRemark());
                }
            } catch (Exception e) {
                logger.info("wifi数据查询失败", e);
            }
        }
        return null;
    }


    private boolean saveWifi(Wifi wifi) {
        if (wifi != null) {
            try {
                return wifiMapper.insertSelective(wifi) != 1;
            } catch (Exception e) {
                logger.warn("wifi数据入库失败", e);
            }
        }
        return false;
    }

    /**
     * wifi转gps
     *
     * @param bssid
     * @return
     */
    private WifiConvertGpsResult wifiConvertGps(String bssid) {
        //数据库没有调用接口查询
        //http://api.cellocation.com:81/wifi/?mac=00:87:36:05:5d:ea&coord=wgs84&output=json
        String tempUrl = (url + "/wifi/?mac=MAC&coord=wgs84&output=json").replace("MAC", bssid);
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("Accept", "text/plain;charset=utf-8");
            headers.put("Content-Type", "application/json; charset=utf-8");
            HttpResult httpResult = HttpClientUtil.executeHttpParams(tempUrl, "get", null, headers, null, null);
            logger.info(ReflectionToStringBuilder.toString(httpResult, ToStringStyle.MULTI_LINE_STYLE));
            if (httpResult != null && httpResult.getStatusCode() == 200) {
                //{"errcode":0, "lat":"39.950027", "lon":"116.229858", "radius":"180", "address":"北京市海淀区四季青镇益园文创基地c区11号楼;南平庄中路与西平庄路路口西北574米"}
                WifiConvertGpsResult wifiConvertGpsResult = JSON.parseObject(httpResult.getContent(), WifiConvertGpsResult.class);
                return wifiConvertGpsResult;
            }
        } catch (Exception e) {
            logger.info("wifi坐标转换接口调用失败", e);
        }
        return null;
    }


    /**
     * @param mnc 网络类型：0移动、1联通(电信对应sid)
     * @param lac 小区号(电信对应nid)
     * @param ci  基站号(电信对应bid)
     *            private Double longitude; 经度
     *            <p>
     *            private Double latitude; 纬度
     *            <p>
     *            private int make; 0 待处理  1 成功  2 无结果
     */
    @Override
    public ConvertResult stationConverToGps(String mnc, String lac, String ci) {
        ConvertResult rt = new ConvertResult();
        rt.setMake(2);
        if (mnc != null && lac != null && ci != null) {
            try {
                Station st = new Station();
                st.setMnc(mnc);
                st.setLac(lac);
                st.setCi(ci);
                st.setCretionTime(Calendar.getInstance().getTime());
                st.setSeqId(UUID.randomUUID().toString());

                //查
                ConvertResult cvrDb = this.findStation(mnc, lac, ci);
                if (cvrDb != null) {
                    return cvrDb;
                }

                //转
                StationConverToGpsResult station = this.stationConvertGps(Integer.valueOf(mnc, 16), Integer.valueOf(lac, 16), Integer.valueOf(ci, 16));
                if (station != null && station.getErrcode() == 0) {
                    //1 成功  2 无结果
                    int make = station != null && station.getErrcode() == 0 ? 1 : 2;

                    st.setAddress(station.getAddress());
                    st.setRadius(Float.valueOf(station.getRadius()));
                    st.setLat(station.getLat());
                    st.setLon(station.getLon());
                    st.setRemark(make);

                    //存
                    this.saveStation(st);
                    rt.setMake(make);
                    rt.setLatitude(station.getLat());
                    rt.setLongitude(station.getLon());
                }
            } finally {
            }
        }
        return rt;
    }


    /**
     * @param mnc mnc网络类型：0移动，1联通(电信对应sid)，十进制
     * @param lac 小区号
     * @param ci  电信对应bid
     *            <p>
     *            "errcode": 0, 成功 10000: 参数错误 10001: 无查询结果
     *            "lat": "40.00598145",
     *            "lon": "116.48539734",
     *            "radius": "937",
     *            "address": "北京市朝阳区来广营地区东湖渠;溪阳东路与屏翠东路路口东70米"
     */
    private StationConverToGpsResult stationConvertGps(Integer mnc, Integer lac, Integer ci) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("output", "json");
            params.put("mcc", "460");//网络类型 默认 460
            params.put("mnc", String.valueOf(mnc));
            params.put("lac", String.valueOf(lac));
            params.put("ci", String.valueOf(ci));
            HttpResult resp = HttpClientUtil.executeGetParams(null, url + "/cell", null, null, null, params);
            logger.info(ReflectionToStringBuilder.toString(resp, ToStringStyle.MULTI_LINE_STYLE));
            if (resp != null && resp.getStatusCode() == 200) {
                return JSON.parseObject(resp.getContent(), StationConverToGpsResult.class);
            }
        } catch (IOException | URISyntaxException e) {
            logger.warn("地址接口转换失败", e);
        }
        return null;
    }

    private boolean saveStation(Station record) {
        try {
            if (record != null)
                return stationMapper.insert(record) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("站点信息保存失败", e);
        }
        return false;
    }

    private ConvertResult findStation(String mnc, String lac, String ci) {
        if (mnc != null && lac != null && ci != null) {
            try {
                StationExample example = new StationExample();
                example.createCriteria().andMncEqualTo(mnc).andLacEqualTo(lac).andCiEqualTo(ci).andRemarkBetween(1, 2);//启用的,无结果的
                List<Station> ls = stationMapper.selectByExample(example);
                if (ls != null && ls.size() >= 1) {
                    Station station = ls.get(0);
                    return new ConvertResult(station.getLon(), station.getLat(), station.getRemark());
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("基站数据查询失败", e);
            }
        }
        return null;
    }
}
