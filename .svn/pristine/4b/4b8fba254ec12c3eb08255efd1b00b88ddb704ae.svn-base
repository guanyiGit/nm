package com.soholy.service.aep.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.soholy.entity.TDeviceInfo;
import com.soholy.mapper.TDeviceInfoMapper;
import com.soholy.pojo.vo.AddDevice;
import com.soholy.service.aep.AepService;
import com.soholy.service.aep.DeviceService_v2;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
class DeviceService_v2Impl implements DeviceService_v2 {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AepService aepService;

    @Autowired
    private TDeviceInfoMapper tDeviceInfoMapper;

    @Override
    public boolean register(String imei, String deviceBrand, String deviceBatch) {
        AddDevice iotDevice = new AddDevice();
        iotDevice.setDeviceName(imei);
        iotDevice.setImei(imei);
        String deviceStr = aepService.createDevice(iotDevice);
        JSONObject registerInfo = JSON.parseObject(deviceStr);
        //{"code":0,"msg":"ok","result":{"deviceId":"b0850dda049b4117a3c6a9466b78c297","deviceName":"151964040592564","dmpId":null,"tenantId":"10176562","productId":10007954,"imei":"151964040592564","imsi":null,"firmwareVersion":null,"deviceStatus":0,"deviceStatusStr":null,"token":null,"secret":null,"autoObserver":0,"lastTime":null,"createTime":null,"createBy":"GUANYI","updateTime":null,"updateBy":null,"isDelete":null,"apn":null,"iotVer":null,"mvendor":null,"mver":null,"netStatus":null,"onlineAt":null,"offlineAt":null,"tupVendorId":null,"tupDeviceModel":null,"tupDeviceType":null,"tupIsProfile":1,"psk":null,"nbDeviceResourceInfos":null}}
        if (0 == registerInfo.getIntValue("code")) {
            JSONObject registerDateil = registerInfo.getJSONObject("result");
            String deviceIdIot = registerDateil.getString("deviceId");
            String psk = registerDateil.getString("psk");

            TDeviceInfo tDevice = new TDeviceInfo();
            tDevice.setDeviceNo(imei);
            tDevice.setStatus(1);
            tDevice.setBrand(deviceBrand);
            tDevice.setModel("model");
            tDevice.setDateOfProduction(LocalDateTime.now());
            tDevice.setRemarks(imei);
            tDevice.setType(1);
            tDevice.setStartTime(LocalDateTime.now());
//            tDevice.setWirteOffTime()
            tDevice.setOrgId(1);
//            tDevice.setReceiveOrg()
//        tDevice.setReceiver()
            tDevice.setCreateDate(LocalDateTime.now());
//            tDevice.setUpdateDate()
//            tDevice.setIsDistribution()
            tDevice.setActivateTime(LocalDateTime.now());
//            tDevice.setFreezeTime(?)
            tDevice.setImei(imei);
//            tDevice.setManufacturerNum()
            tDevice.setPsk(psk);
            tDevice.setDeviceIotId(deviceIdIot);
//            tDevice.setFirm()
//            tDevice.setImportBy()

            if (1 == tDeviceInfoMapper.insert(tDevice)) {
                return true;
            }
            this.logout(deviceIdIot, null);
        }
        return false;
    }

    @Override
    public boolean logout(String deviceIdIot, String imei) {
        if (StringUtils.isBlank(deviceIdIot)) {
            List<TDeviceInfo> tDevices = tDeviceInfoMapper.selectList(Wrappers.<TDeviceInfo>lambdaQuery()
                    .eq(TDeviceInfo::getImei, imei));
            if (tDevices != null && tDevices.size() == 1) {
                deviceIdIot = tDevices.get(0).getDeviceIotId();
            } else {
                throw new RuntimeException("输入的设备imei不存在");
            }
        }
        String[] deviceIds = {deviceIdIot};
        JSONObject result = JSON.parseObject(aepService.delDevices(deviceIds));
        if (0 == result.getIntValue("code")) {
            if (1 != tDeviceInfoMapper.delete(Wrappers.<TDeviceInfo>lambdaQuery()
                    .eq(TDeviceInfo::getDeviceIotId, deviceIdIot))) {
                logger.warn("设备删除,删除失败;");
            }
            return true;
        }
        return false;
    }


    @Override
    public List<TDeviceInfo> findDeviceInifo(String id, int type) {
        if (StringUtils.isNoneBlank(id)) {
            LambdaQueryWrapper<TDeviceInfo> query = Wrappers.lambdaQuery();
            if (type == 0)
                query.eq(TDeviceInfo::getDeviceIotId, id);
            else
                query.eq(TDeviceInfo::getImei, id);
            return tDeviceInfoMapper.selectList(query);
        }
        return null;
    }
}
