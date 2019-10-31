package com.soholy.cb.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.soholy.cb.config.IotPropertiesConfig;
import com.soholy.cb.dao.TDeviceInfoDb;
import com.soholy.cb.entity.TDeviceDataWifiEntity;
import com.soholy.cb.entity.TDeviceInfoEntity;
import com.soholy.cb.entity.app.TaskType;
import com.soholy.cb.entity.cdoec.*;
import com.soholy.cb.entity.iot.deviceManager.UpdateDeviceInfoReqDTO;
import com.soholy.cb.service.TDeviceIotService;
import com.soholy.cb.service.app.AuthService;
import com.soholy.cb.service.app.ManageService;
import com.soholy.cb.utils.HttpClientUtil;
import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@Log
public class TDeviceIotServiceImpl implements TDeviceIotService {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TDeviceIotServiceImpl.class);

    @Autowired
    private TDeviceInfoDb deviceInfoDb;

    @Autowired
    private IotPropertiesConfig conf;

    @Autowired
    private AuthService authService;

    @Autowired
    private ManageService managerService;

    public List<TDeviceInfoEntity> findDevicesByIotId(String deviceIdIot) {
        if (deviceIdIot != null)
            return this.deviceInfoDb.selectList((Wrapper) Wrappers.<TDeviceInfoEntity>lambdaQuery().eq(TDeviceInfoEntity::getDeviceIotId, deviceIdIot));
        return null;
    }

    public CallBackData dataPrepare(UploadBean uploadBean) {
        if (uploadBean == null)
            return null;
        CallBackData parasData = new CallBackData();
        TDeviceInfoEntity device = (TDeviceInfoEntity) uploadBean.getT();
        parasData.setDeviceNo(device.getDeviceNo());
        parasData.setQuantity(uploadBean.getElectricQuantity());
        parasData.setDataTime(LocalDateTime.now());
        parasData.setUploadTime(uploadBean.getUpLoadTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        parasData.setCreateDate(LocalDateTime.now());
        parasData.setId(UUID.randomUUID().toString());
        Integer voltage = uploadBean.getVoltage();
        voltage = Integer.valueOf((voltage == null) ? 0 : voltage.intValue());
        parasData.setVoltage(Double.valueOf(voltage.doubleValue()));
        int dataType = 0;
        if (uploadBean instanceof WifiUpload) {
            WifiUpload wifiUpload = (WifiUpload) uploadBean;
            List<TDeviceDataWifiEntity> dwifiDataList = new ArrayList<TDeviceDataWifiEntity>();
            for (WifiLocation wifiLocation : wifiUpload.getWifiLocation()) {
                TDeviceDataWifiEntity wifi = new TDeviceDataWifiEntity();
                wifi.setDeviceNo(Long.valueOf(device.getDeviceNo()));
                wifi.setDeviceDataWifiId(UUID.randomUUID().toString());
                wifi.setCreationTime(LocalDateTime.now());
                wifi.setBssid(wifiLocation.getBssid());
                wifi.setDeviceDataId(parasData.getId());
                wifi.setRssi(Float.valueOf(wifiLocation.getRssi()));
                dwifiDataList.add(wifi);
            }
            parasData.settDeviceDataWifis(dwifiDataList);
            dataType = 1;
        } else if (uploadBean instanceof GpsUpload) {
            GpsUpload gpsUpload = (GpsUpload) uploadBean;
            parasData.setLat(Double.valueOf(gpsUpload.getLatitude()));
            parasData.setLng(Double.valueOf(gpsUpload.getLongitude()));
            dataType = 0;
        } else if (uploadBean instanceof WarnUpload) {
            WarnUpload warnUpload = (WarnUpload) uploadBean;
            warnUpload.setWarnTime(uploadBean.getUpLoadTime());
            dataType = 3;
        } else if (uploadBean instanceof SimpleUpload) {
            SimpleUpload simpleUpload = (SimpleUpload) uploadBean;
            dataType = 2;
        } else if (uploadBean instanceof StartUpBean) {
            StartUpBean startUpBean = (StartUpBean) uploadBean;
            dataType = 4;
        } else {
            log.info("数据上传标识码解析错误！");
            return null;
        }
        parasData.setDataType(Integer.valueOf(dataType));
        return parasData;
    }

    public boolean modifyDeviceInfo(String deviceIdIot, String imei) throws Exception {
        if (StringUtils.isBlank(deviceIdIot)) {
            logger.warn("modifyDeviceInfo 方法参数有误");
            return false;
        }
        String url = this.authService.iotServerBaseUrl() + "/iocm/app/dm/v1.2.0/devices" + "/{deviceId}";
        url = url.replace("{deviceId}", deviceIdIot);
        Map<String, String> headers = this.authService.setAuthentication();
        UpdateDeviceInfoReqDTO dto = new UpdateDeviceInfoReqDTO();
        dto.setDeviceType(this.conf.getDeviceType());
        dto.setManufacturerId(this.conf.getManufacturerId());
        dto.setManufacturerName(this.conf.getManufacturerId());
        dto.setProtocolType("CoAP");
        dto.setName(imei);
        dto.setLocation("Shenzhen");
        dto.setModel(this.conf.getModel());
        HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "PUT", null, headers, JSON.toJSONString(dto), null);
        if (resp == null || resp.getStatusCode().intValue() != 204) {
            logger.error("setEncryption error,result statusCode:" + resp.getStatusCode());
            return false;
        }
        return true;
    }

    public void registerList(List<Long> deviceIdList, int cmdValue) throws Exception {
        String taskName = Calendar.getInstance().getTime().toString();
        String url = this.authService.iotServerBaseUrl() + "/iocm/app/batchtask/v1.1.0/tasks";
        Map<String, String> headers = this.authService.setAuthentication();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appId", this.authService.iotServerBaseUrl() + this.conf.getAppid());
        jsonObject.put("timeout", Integer.valueOf(9999999));
        jsonObject.put("taskName", taskName);
        jsonObject.put("taskType", "DeviceCmd");
        JSONObject param = new JSONObject();
        TaskType ttype = TaskType.DeviceList;
        param.put("type", ttype);
        if (TaskType.DeviceType.equals(ttype)) {
            param.put("deviceType", ttype);
        } else if (TaskType.DeviceArea.equals(ttype)) {
            param.put("deviceLocation", ttype);
        } else if (TaskType.GroupList.equals(ttype)) {
            param.put("groupList", ttype);
        } else if (TaskType.DeviceList.equals(ttype)) {
            List<TDeviceInfoEntity> tdeviceList = this.deviceInfoDb.selectBatchIds(deviceIdList);
            List<String> tdeviceNumList = new ArrayList<String>();
            for (TDeviceInfoEntity tDevice : tdeviceList)
                tdeviceNumList.add(tDevice.getDeviceNo());
            param.put("deviceList", tdeviceNumList);
        }
        JSONObject CommandDTOV1 = new JSONObject();
        CommandDTOV1.put("serviceId", this.conf.getServiceId());
        CommandDTOV1.put("method", this.conf.getCmdName());
        JSONObject parasNode = new JSONObject();
        parasNode.put(this.conf.getCmdProp(), Integer.valueOf(cmdValue));
        CommandDTOV1.put("paras", parasNode);
        param.put("command", CommandDTOV1);
        JSONArray deviceList = new JSONArray();
        param.put("deviceList", deviceList);
        jsonObject.put("param", param);
        HttpClientUtil.HttpResult resp = HttpClientUtil.executeHttpParams(url, "PUT", null, headers, jsonObject, null);
    }

    public boolean deleteDeviceByIotId(String deviceIdIot) throws Exception {
        if (this.deviceInfoDb.delete((Wrapper) Wrappers.<TDeviceInfoEntity>lambdaQuery().eq(TDeviceInfoEntity::getDeviceIotId, deviceIdIot)) >= 0)
            return this.managerService.deleteDevice(deviceIdIot);
        return false;
    }

    public void logout(String deviceIotId) {
        if (StringUtils.isNotBlank(deviceIotId)) {
            LambdaQueryWrapper<TDeviceInfoEntity> wrapper = (LambdaQueryWrapper) Wrappers.<TDeviceInfoEntity>lambdaQuery().eq(TDeviceInfoEntity::getDeviceIotId, deviceIotId);
            if (0 != this.deviceInfoDb.selectCount(wrapper).intValue() &&
                    0 == this.deviceInfoDb.delete(wrapper))
                throw new RuntimeException("设备删除失败");
            try {
                this.managerService.deleteDevice(deviceIotId);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("iot设备删除失败");
            }
        }
    }

    public TDeviceInfoEntity register(String imei, String deviceBrand, String deviceBatch) throws Exception {
        JSONObject result = this.managerService.register(imei, null, null, Integer.valueOf(0));
        if (result != null) {
            String deviceIdIot = result.getString("deviceId");
            String verifyCode = result.getString("verifyCode");
            String psk = result.getString("psk");
            TDeviceInfoEntity tDevice = new TDeviceInfoEntity();
            tDevice.setImei(imei);
            tDevice.setBatch(StringUtils.isBlank(deviceBrand) ? deviceBrand : "无");
            tDevice.setBrand(StringUtils.isBlank(deviceBrand) ? deviceBrand : "无");
            tDevice.setCreateDate(LocalDateTime.now());
            tDevice.setType(Integer.valueOf(1));
            tDevice.setPsk(psk);
            tDevice.setDeviceNo(imei);
            tDevice.setDeviceIotId(deviceIdIot);
            tDevice.setDateOfProduction(LocalDateTime.now());
            tDevice.setOrgId(Integer.valueOf(1));
            tDevice.setStartTime(LocalDateTime.now());
            tDevice.setActivateTime(LocalDateTime.now());
            tDevice.setStatus(Integer.valueOf(1));
            tDevice.setModel(StringUtils.isBlank(deviceBrand) ? deviceBrand : "无");
            tDevice.setRemarks(verifyCode);
            if (this.deviceInfoDb.insert(tDevice) == 1 && modifyDeviceInfo(deviceIdIot, imei))
                return tDevice;
            deleteDeviceByIotId(deviceIdIot);
            throw new RuntimeException();
        }
        return null;
    }

    public List<TDeviceInfoEntity> registerAll(List<TDeviceInfoEntity> deviceList) throws Exception {
        if (deviceList != null && deviceList.size() > 0) {
            for (TDeviceInfoEntity tDevice : deviceList) {
                JSONObject result = this.managerService.register(tDevice.getDeviceNo(), null, null, Integer.valueOf(0));
                if (result != null) {
                    String deviceIdIot = result.getString("deviceId");
                    modifyDeviceInfo(deviceIdIot, deviceIdIot);
                    String verifyCode = result.getString("verifyCode");
                    String psk = result.getString("psk");
                    tDevice.setDeviceIotId(deviceIdIot);
                    continue;
                }
                logger.error("设备注册失败！ 设备信息:" + tDevice);
                return null;
            }
            return deviceList;
        }
        return null;
    }
}
