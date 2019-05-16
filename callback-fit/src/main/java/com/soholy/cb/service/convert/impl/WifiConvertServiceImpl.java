package com.soholy.cb.service.convert.impl;

import com.alibaba.fastjson.JSON;
import com.soholy.cb.common.DynamicDataSourceHolder;
import com.soholy.cb.dao.otherDb.WifiMapper;
import com.soholy.cb.entity.otherDb.WifiConvertGpsResult;
import com.soholy.cb.entity.otherDb.po.Wifi;
import com.soholy.cb.entity.otherDb.po.WifiExample;
import com.soholy.cb.service.convert.WifiConvertService;
import com.soholy.cb.utils.HttpClientUtil;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WifiConvertServiceImpl implements WifiConvertService {
  private static final Logger logger = LoggerFactory.getLogger(WifiConvertServiceImpl.class);
  
  @Autowired
  private WifiMapper wifiMapper;
  
  @Value("${ConvertGps.url}")
  private String url;
  
  public WifiConvertService.ConvertResult wifiConverToGps(String bssid, Float rssi) {
    WifiConvertService.ConvertResult rt = new WifiConvertService.ConvertResult();
    rt.setMake(Integer.valueOf(2));
    if (StringUtils.isNotBlank(bssid)) {
      DynamicDataSourceHolder.setWifiDataSource();
      try {
        Wifi wifi = new Wifi();
        wifi.setBssid1(bssid);
        wifi.setRssi1(rssi);
        wifi.setPriId(UUID.randomUUID().toString());
        wifi.setUpdateTime(Calendar.getInstance().getTime());
        wifi.setCreationTime(Calendar.getInstance().getTime());
        WifiConvertService.ConvertResult cvrDb = findWifi(bssid);
        if (cvrDb != null)
          return cvrDb; 
        WifiConvertGpsResult wifiCvr = wifiConvertGps(bssid);
        if (wifiCvr != null) {
          int make = (wifiCvr != null && wifiCvr.getErrcode().intValue() == 0) ? 1 : 2;
          wifi.setRemark(Integer.valueOf(make));
          wifi.setAddress(wifiCvr.getAddress());
          wifi.setLatitude(wifiCvr.getLat());
          wifi.setLongitude(wifiCvr.getLon());
          wifi.setRssi1(wifiCvr.getRadius());
          saveWifi(wifi);
        } 
      } finally {
        DynamicDataSourceHolder.setDefaultDataSouce();
      } 
    } 
    return rt;
  }
  
  private WifiConvertService.ConvertResult findWifi(String bssid) {
    if (StringUtils.isNotBlank(bssid))
      try {
        WifiExample example = new WifiExample();
        example.createCriteria().andBssid1EqualTo(bssid).andRemarkBetween(Integer.valueOf(1), Integer.valueOf(2));
        WifiExample.Criteria equal2 = example.createCriteria().andBssid2EqualTo(bssid).andRemarkBetween(Integer.valueOf(1), Integer.valueOf(2));
        example.or(equal2);
        WifiExample.Criteria equal3 = example.createCriteria().andBssid3EqualTo(bssid).andRemarkBetween(Integer.valueOf(1), Integer.valueOf(2));
        example.or(equal3);
        List<Wifi> wifis = this.wifiMapper.selectByExample(example);
        if (wifis != null && wifis.size() >= 1) {
          Wifi wifi = (Wifi)wifis.get(0);
          return new WifiConvertService.ConvertResult(wifi.getLongitude(), wifi.getLatitude(), wifi.getRemark());
        } 
      } catch (Exception e) {
        logger.info("wifi数据查询失败", e);
      }  
    return null;
  }
  
  private boolean saveWifi(Wifi wifi) {
    if (wifi != null)
      try {
        return (this.wifiMapper.insertSelective(wifi) != 1);
      } catch (Exception e) {
        logger.warn("wifi数据入库失败", e);
      }  
    return false;
  }
  
  private WifiConvertGpsResult wifiConvertGps(String bssid) {
    String tempUrl = (this.url + "/wifi/?mac=MAC&coord=wgs84&output=json").replace("MAC", bssid);
    try {
      Map<String, String> headers = new HashMap<String, String>();
      headers.put("Accept", "text/plain;charset=utf-8");
      headers.put("Content-Type", "application/json; charset=utf-8");
      HttpClientUtil.HttpResult httpResult = HttpClientUtil.executeHttpParams(tempUrl, "get", null, headers, null, null);
      if (httpResult != null && httpResult.getStatusCode().intValue() == 200)
        return (WifiConvertGpsResult)JSON.parseObject(httpResult.getContent(), WifiConvertGpsResult.class); 
    } catch (Exception e) {
      logger.info("wifi坐标转换接口调用失败", e);
    } 
    return null;
  }
}
