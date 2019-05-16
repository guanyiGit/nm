package com.soholy.cb.service.log.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.soholy.cb.config.IotPropertiesConfig;
import com.soholy.cb.dao.IotDataDb_dev;
import com.soholy.cb.entity.cdoec.*;
import com.soholy.cb.service.log.LogService;
import com.soholy.cb.utils.ByteUtils;
import com.soholy.cb.utils.FileIoUtils;
import com.soholy.cb.utils.ReqPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private IotPropertiesConfig conf;

    @Autowired
    private IotDataDb_dev iotDataDb_dev;

    public void saveLog(CodecBean codecBean, byte[] inputBinary, String fullStr) {
        if (StringUtils.isNotBlank(fullStr))
            fullStr = fullStr.replaceAll("\"", "'");
        if (codecBean != null) {
            int type = 0;
            T_iot_data data = new T_iot_data();
            DecodeRsp rsp = codecBean.getDecodeRsp();
            UploadBean up = codecBean.getUploadBean();
            if (up != null) {
                data.setCodec(JSON.toJSONString(up, new SerializerFeature[]{SerializerFeature.UseSingleQuotes}));
                type = 1;
            } else if (rsp != null) {
                data.setCodec(JSON.toJSONString(rsp, new SerializerFeature[]{SerializerFeature.UseSingleQuotes}));
                type = 2;
            } else if (inputBinary != null) {
                type = 3;
            }
            data.setSource(ByteUtils.byte2ToIntegerStr(inputBinary, 0, inputBinary.length).replaceAll("(.{2})", "0x$1 "));
            data.setFull(fullStr);
            data.setLocal_Time(LocalDateTime.now());
            data.setType(Integer.valueOf(type));
            this.iotDataDb_dev.saves(Arrays.asList(new T_iot_data[]{data}));
        }
    }

    public List<T_iot_data> findLog(String imei, int pageNo, int pageSize) {
        ReqPage page = new ReqPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
        return this.iotDataDb_dev.find(imei, page.getOffset().intValue(), page.getLimit().intValue());
    }

    public void printLog(CodecBean codecBean, byte[] inputBinary, String inputStr) {
        if (StringUtils.isNotBlank(inputStr))
            writeLogFile(inputStr);
        if (inputBinary != null)
            writeLogFile("bytes:" + ByteUtils.byte2ToIntegerStr(inputBinary, 0, inputBinary.length).replaceAll("(.{2})", "0x$1 "));
        if (codecBean != null) {
            int resultCode = 0;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DecodeRsp decodeRsp = codecBean.getDecodeRsp();
            UploadBean uploadBean = codecBean.getUploadBean();
            String result = "";
            if (uploadBean != null) {
                result = "upload data:";
                result = result + " 电量:" + uploadBean.getElectricQuantity();
                result = result + " imei:" + uploadBean.getImei();
                result = result + " 厂商编号:" + uploadBean.getFactoryNo();
                result = result + " 设备型号:" + uploadBean.getDeviceType();
                Date upLoadTime = uploadBean.getUpLoadTime();
                if (upLoadTime != null) {
                    String format2 = format.format(upLoadTime);
                    result = result + " 上传时间" + format2;
                }
                if (uploadBean instanceof WifiUpload) {
                    WifiUpload wifiUpload = (WifiUpload) uploadBean;
                    List<WifiLocation> list = wifiUpload.getWifiLocation();
                    result = result + " length:" + wifiUpload.getLength();
                    for (int i = 0; i < list.size(); i++) {
                        result = result + " Bssid(" + i + "):" + ((WifiLocation) list.get(i)).getBssid();
                        result = result + " Rssi(" + i + "):" + ((WifiLocation) list.get(i)).getRssi();
                    }
                    resultCode = 10;
                } else if (uploadBean instanceof GpsUpload) {
                    GpsUpload gpsUpload = (GpsUpload) uploadBean;
                    result = result + " latitude:" + gpsUpload.getLatitude();
                    result = result + " longitude:" + gpsUpload.getLongitude();
                    resultCode = 1;
                } else if (uploadBean instanceof WarnUpload) {
                    WarnUpload warnUpload = (WarnUpload) uploadBean;
                    Date warnTime = warnUpload.getUpLoadTime();
                    if (warnTime != null) {
                        String format2 = format.format(warnTime);
                        result = result + " 告警时间:" + format2;
                    }
                    resultCode = 2;
                } else if (uploadBean instanceof com.soholy.cb.entity.cdoec.SimpleUpload) {
                    resultCode = 5;
                } else if (uploadBean instanceof com.soholy.cb.entity.cdoec.StartUpBean) {
                    resultCode = 8;
                }
                result = result + "[code:" + resultCode + "]\n";
            }
            if (decodeRsp != null && decodeRsp.getIMEI() != null) {
                result = "response data:";
                result = result + " IMEI:" + decodeRsp.getIMEI();
                result = result + " Mid:" + decodeRsp.getMid();
                result = result + " result:" + decodeRsp.getResultCode();
                result = result + " rspTime:" + format.format(decodeRsp.getRspTime());
                result = result + "\n";
            }
            writeLogFile(result);
        }
    }

    public void printLog(CodecBean codecBean) {
        printLog(codecBean, null, null);
    }

    public void printLog(byte[] inputBinary) {
        printLog(null, inputBinary, null);
    }

    public void printLog(String inputStr) {
        printLog(null, null, inputStr);
    }

    private void writeLogFile(String resultStr) {
        if (StringUtils.isNotBlank(this.conf.getLogPath()))
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                File file = new File(this.conf.getLogPath() + "/" + sdf.format(new Date()) + ".txt");
                resultStr = "[" + sdf2.format(new Date()) + "]" + resultStr + "\r\n";
                FileIoUtils.writeFile(file, resultStr, "utf-8", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
