package com.soholy.cb.service.activemq;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.soholy.cb.common.ApplicationContextProvider;
import com.soholy.cb.entity.TDeviceInfoEntity;
import com.soholy.cb.entity.cdoec.CallBackData;
import com.soholy.cb.entity.cdoec.CodecBean;
import com.soholy.cb.entity.cdoec.DecodeRsp;
import com.soholy.cb.entity.cdoec.UploadBean;
import com.soholy.cb.service.AcmqService;
import com.soholy.cb.service.TDeviceCommandService;
import com.soholy.cb.service.TDeviceIotService;
import com.soholy.cb.service.codec.CodecService;
import com.soholy.cb.service.log.LogService;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class AmqProducer implements Runnable {
  private static final Logger log = Logger.getLogger(AmqProducer.class.getName());
  
  private static final String DECODE_CHARSET = "ISO8859-1";
  
  private String codecParamName;
  
  private JSONObject json;
  
  private TDeviceIotService tDeviceIotService;
  
  private TDeviceCommandService tDeviceCommandService;
  
  private CodecService codecService;
  
  private AcmqService acmqService;
  
  private LogService logService;
  
  public AmqProducer(JSONObject json, String codecParamName) {
    this.json = json;
    this.codecParamName = codecParamName;
    this.tDeviceIotService = (TDeviceIotService)ApplicationContextProvider.getBean(TDeviceIotService.class);
    this.tDeviceCommandService = (TDeviceCommandService)ApplicationContextProvider.getBean(TDeviceCommandService.class);
    this.codecService = (CodecService)ApplicationContextProvider.getBean(CodecService.class);
    this.acmqService = (AcmqService)ApplicationContextProvider.getBean(AcmqService.class);
    this.logService = (LogService)ApplicationContextProvider.getBean(LogService.class);
  }
  
  public void run() {
    try {
      String requestId = this.json.getString("requestId");
      String deviceIdIot = this.json.getString("deviceId");
      String gatewayId = this.json.getString("gatewayId");
      JSONArray jsonArray = this.json.getJSONArray("services");
      List<TDeviceInfoEntity> tdeviceList = this.tDeviceIotService.findDevicesByIotId(deviceIdIot);
      TDeviceInfoEntity device = null;
      Optional<TDeviceInfoEntity> first = tdeviceList.stream().findFirst();
      if (first.isPresent()) {
        device = (TDeviceInfoEntity)first.get();
      } else {
        log.info("设备上传数据设备id不存在！");
        return;
      } 
      log.info("receive device push deviceIdIot:" + deviceIdIot);
      log.info("deviceId msg input:" + this.json);
      log.info("requestId:" + requestId);
      log.info("gatewayId:" + gatewayId);
      for (int i = 0; jsonArray != null && i < jsonArray.size(); i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        String serviceId = jsonObject.getString("serviceId");
        String serviceType = jsonObject.getString("serviceType");
        String eventTime = jsonObject.getString("eventTime");
        JSONObject dataNode = jsonObject.getJSONObject("data");
        String dataStr = dataNode.getString(this.codecParamName);
        byte[] inputBinary = null;
        try {
          inputBinary = dataStr.getBytes("ISO8859-1");
        } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
          log.warning(e.getMessage());
          return;
        } 
        log.info("serviceId:" + serviceId);
        log.info("serviceType:" + serviceType);
        log.info("eventTime:" + eventTime);
        CodecBean codecBean = this.codecService.decodec(inputBinary);
        this.logService.saveLog(codecBean, inputBinary, this.json.toJSONString());
        if (codecBean != null) {
          UploadBean uploadBean = codecBean.getUploadBean();
          if (uploadBean != null && uploadBean.getImei() != null) {
            uploadBean.setT(device);
            CallBackData data = this.tDeviceIotService.dataPrepare(uploadBean);
            data.settDevice(device);
            if (uploadBean instanceof com.soholy.cb.entity.cdoec.StartUpBean)
              this.tDeviceCommandService.resStart(data, device); 
            this.acmqService.dataPushMq(this.json.toJSONString(data));
          } 
          DecodeRsp decodeRsp = codecBean.getDecodeRsp();
          if (decodeRsp != null && decodeRsp.getIMEI() != null) {
            decodeRsp.setT(device);
            this.tDeviceCommandService.cmdResHandle(decodeRsp);
          } 
        } 
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
