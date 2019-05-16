package atshunhengli.com.service.impl;


import atshunhengli.com.entity.cdoec.*;
import atshunhengli.com.service.LogService;
import atshunhengli.com.utils.FileIoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@PropertySource({"classpath:config/conf.properties"})
public class LogServiceImpl implements LogService {

    @Value("${write.log.path}")
    private String logPath;

    @Override
    public void printLog(CodecBean codecBean, byte[] inputBinary, String inputStr) {
        if (StringUtils.isNotBlank(inputStr)) {
            writeLogFile(inputStr);
        }
        if (inputBinary != null) {
            this.writeLogFile("bytes:" + Arrays.toString(inputBinary));
        }

        if (codecBean != null) {
            int resultCode = 0;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DecodeRsp decodeRsp = codecBean.getDecodeRsp();
            UploadBean uploadBean = codecBean.getUploadBean();
            String result = "";
            if (uploadBean != null) {
                result = "upload data:";
                result += " 电量:" + uploadBean.getElectricQuantity();
                result += " imei:" + uploadBean.getImei();
                result += " 厂商编号:" + uploadBean.getFactoryNo();
                result += " 设备型号:" + uploadBean.getDeviceType();
                Date upLoadTime = uploadBean.getUpLoadTime();
                if (upLoadTime != null) {
                    String format2 = format.format(upLoadTime);
                    result += " 上传时间" + format2;
                }
                if (uploadBean instanceof WifiUpload) {
                    WifiUpload wifiUpload = (WifiUpload) uploadBean;
                    List<WifiLocation> list = wifiUpload.getWifiLocation();
                    result += " length:" + wifiUpload.getLength();
                    for (int i = 0; i < list.size(); i++) {
                        result += " Bssid(" + i + "):" + list.get(i).getBssid();
                        result += " Rssi(" + i + "):" + list.get(i).getRssi();
                    }
                    resultCode = 10;
                } else if (uploadBean instanceof GpsUpload) {
                    GpsUpload gpsUpload = (GpsUpload) uploadBean;
                    result += " latitude:" + gpsUpload.getLatitude();
                    result += " longitude:" + gpsUpload.getLongitude();
                    resultCode = 1;
                } else if (uploadBean instanceof WarnUpload) {
                    WarnUpload warnUpload = (WarnUpload) uploadBean;
                    Date warnTime = warnUpload.getUpLoadTime();
                    if (warnTime != null) {
                        String format2 = format.format(warnTime);
                        result += " 告警时间:" + format2;
                    }
                    resultCode = 2;
                } else if (uploadBean instanceof SimpleUpload) {
                    resultCode = 5;
                } else if (uploadBean instanceof StartUpBean) {
                    resultCode = 8;
                }
                result += "[code:" + resultCode + "]\n";
            }
            if (decodeRsp != null && decodeRsp.getIMEI() != null) {
                result = "response data:";
                result += " IMEI:" + decodeRsp.getIMEI();
                result += " Mid:" + decodeRsp.getMid();
                result += " result:" + decodeRsp.getResultCode();
                result += " rspTime:" + format.format(decodeRsp.getRspTime());
                result += "\n";
            }
            this.writeLogFile(result);
        }
    }


    @Override
    public void printLog(CodecBean codecBean) {
        this.printLog(codecBean, null, null);
    }

    @Override
    public void printLog(byte[] inputBinary) {
        this.printLog(null, inputBinary, null);
    }

    @Override
    public void printLog(String inputStr) {
        this.printLog(null, null, inputStr);
    }

    private void writeLogFile(String resultStr) {
        if (StringUtils.isNotBlank(logPath)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                File file = new File(logPath + "/" + sdf.format(new Date()) + ".txt");
                resultStr = "[" + sdf2.format(new Date()) + "]" + resultStr + "\r\n";
                FileIoUtils.writeFile(file, resultStr, "utf-8", true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
