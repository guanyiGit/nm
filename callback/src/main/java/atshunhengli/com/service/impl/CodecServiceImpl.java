package atshunhengli.com.service.impl;


import atshunhengli.com.entity.cdoec.*;
import atshunhengli.com.entity.iot.CmdType;
import atshunhengli.com.service.CodecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static atshunhengli.com.utils.ByteUtils.*;

@Service
public class CodecServiceImpl implements CodecService {

    public static final Logger logger = LoggerFactory.getLogger(CodecServiceImpl.class);
    public static final String CHARSET = "UTF-8";

    @Override
    public CodecBean decodec(byte[] inputBinary) {
        DecodeRsp decodeRsp = null;
        UploadBean uploadBean = null;
        if (inputBinary == null || inputBinary.length < 1) {
            return null;
        }
        try {
            int inputBinaryindex = 0;
            byte code = inputBinary[inputBinaryindex];// 请求码null
            if (0x01 == code) {// 定位数据上传(gps)
                GpsUpload gpsUpload = this.decodeGpsUp(inputBinary, ++inputBinaryindex);
                if (gpsUpload != null) {
                    uploadBean = gpsUpload;
                } else {
                    logger.warn("定位数据上传解析出错！");
                }
            } else if (0x02 == code) {// 设备告警
                WarnUpload warnUpload = this.decodeWarnUp(inputBinary, ++inputBinaryindex);
                if (warnUpload != null) {
                    uploadBean = warnUpload;
                } else {
                    logger.warn("设备告警数据解码失败！");
                }
            } else if (0x04 == code) {// 设备数据(位置)上传间隔设置,响应
                decodeRsp = this.decodeSetIntervalTimeReq(inputBinary, ++inputBinaryindex);
                if (decodeRsp == null) {
                    logger.info("设备响应【间隔时间设备信息】解码错误！");
                } else {
                    logger.warn("设备数据(位置)上传间隔设置,响应解码失败！");
                }
            } else if (0x05 == code) {// 上传心跳数据(省电模式)
                SimpleUpload simpleUpload = this.decodeSimpleUp(inputBinary, ++inputBinaryindex);
                if (simpleUpload != null) {
                    uploadBean = simpleUpload;
                } else {
                    logger.warn("设备心跳数据解码失败！");
                }
            } else if (0x07 == code) {// 设置设备工作模式 ,响应
                decodeRsp = this.decodeWorkPatternRsp(inputBinary, ++inputBinaryindex);
                if (decodeRsp == null) {
                    logger.warn("设置设备工作模式,设备响应【间隔时间设备信息】解码失败！");
                } else {
                    logger.warn("设置设备工作模式 ,响应解码失败！");
                }
            } else if (0x08 == code) {// 开机数据
                StartUpBean startUpBean = this.decodeStartUpData(inputBinary, ++inputBinaryindex);
                if (startUpBean != null) {
                    uploadBean = startUpBean;
                } else {
                    logger.warn("开机数据解码失败！");
                }

            } else if (0x0A == code) {// 定位数据上传(wifi)
                WifiUpload wifiUpload = this.decodeWifi(inputBinary, ++inputBinaryindex);
                if (wifiUpload != null) {
                    uploadBean = wifiUpload;
                } else {
                    logger.warn("定位数据上传(wifi)解码失败！");
                }
            }
            CodecBean codecBean = new CodecBean();
            codecBean.setDecodeRsp(decodeRsp);
            codecBean.setUploadBean(uploadBean);
            return codecBean;
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("decode  err ", e);
        }
        return null;
    }

    /**
     * @param inputBinary      码流
     * @param inputBinaryindex 码流解析开始下标
     * @return
     * @throws Exception
     * @Description (设备开机数据解析 -)
     */
    private StartUpBean decodeStartUpData(byte[] inputBinary, int inputBinaryindex) {
        StartUpBean startUpBean = new StartUpBean();
        try {
            inputBinaryindex = codecBasic(inputBinary, inputBinaryindex, startUpBean);// imei 电量 厂商编号 产品型号 数据采集时间 --》解码
        } catch (Exception e) {
            logger.warn("设备开机请求数据解析失败！");
            e.printStackTrace();// 设备不存在
            return null;
        }
        return startUpBean;
    }

    /**
     * @param inputBinary      码流
     * @param inputBinaryindex 码流解析开始下标
     * @return
     * @throws UnsupportedEncodingException
     * @Description (设置设备工作模式 - - 响应解析)
     */
    private DecodeRsp decodeWorkPatternRsp(byte[] inputBinary, int inputBinaryindex) {
        DecodeRsp decodeRsp = new DecodeRsp();
        try {

            byte[] midBytes = new byte[2];// mid【1-2】
            inputBinaryindex = copyArrays(inputBinary, inputBinaryindex, midBytes.length, midBytes, 0)[0];
            chckAndFillBytes(midBytes);
            int mid = byte2Toint(midBytes, 0);

            byte[] IMEIBytes = new byte[8];// IMEI【3-11】
            inputBinaryindex = copyArrays(inputBinary, ++inputBinaryindex, IMEIBytes.length, IMEIBytes, 0)[0];
            chckAndFillBytes(IMEIBytes);
            String imei = byte2ToIntegerStr(IMEIBytes, 0, IMEIBytes.length);
            StringBuffer sb = new StringBuffer();
            char[] imeiChar = imei.toCharArray();
            for (int i = 1; i < imeiChar.length; i++) {// 去掉最前面的0
                sb.append(imeiChar[i]);
            }
            imei = sb.toString();

            byte[] respTimeBytes = new byte[4];// 响应时间【11-14】
            inputBinaryindex = copyArrays(inputBinary, ++inputBinaryindex, respTimeBytes.length,
                    respTimeBytes, 0)[0];
            chckAndFillBytes(respTimeBytes);
            long byte4Tolong = byte4Tolong(respTimeBytes, 0);

            byte[] resultBytes = new byte[1];// 命令结果【15】
            inputBinaryindex = copyArrays(inputBinary, ++inputBinaryindex, resultBytes.length, resultBytes,
                    0)[0];
            chckAndFillBytes(resultBytes);
            int resultCode = byte1Toint(resultBytes, 0);

            decodeRsp.setIMEI(imei);
            decodeRsp.setMid(mid);
            decodeRsp.setResultCode(resultCode);
            decodeRsp.setRspTime(new Date(byte4Tolong * 1000l));
        } catch (Exception e) {
            logger.warn("设置设备工作模式 --响应解析错误！", e);
            return null;
        }
        return decodeRsp;
    }

    /**
     * @param inputBinary      码流
     * @param inputBinaryindex 码流解析开始下标
     * @return
     * @throws UnsupportedEncodingException
     * @Description (设置设备上传间隔设置, 设备的响应解码)
     */
    private DecodeRsp decodeSetIntervalTimeReq(byte[] inputBinary, int inputBinaryindex) {
        DecodeRsp decodeRsp = new DecodeRsp();
        try {
            byte[] midBytes = new byte[2];// mid【1-2】
            inputBinaryindex = copyArrays(inputBinary, inputBinaryindex, midBytes.length, midBytes, 0)[0];
            chckAndFillBytes(midBytes);// 如果数据无效置为0
            int mid = byte2Toint(midBytes, 0);

            byte[] IMEIBytes = new byte[8];// IMEI【3-11】
            inputBinaryindex = copyArrays(inputBinary, ++inputBinaryindex, IMEIBytes.length, IMEIBytes, 0)[0];
            chckAndFillBytes(IMEIBytes);
            String imei = byte2ToIntegerStr(IMEIBytes, 0, IMEIBytes.length);
            StringBuffer sb = new StringBuffer();
            char[] imeiChar = imei.toCharArray();
            for (int i = 1; i < imeiChar.length; i++) {// 去掉最前面的0
                sb.append(imeiChar[i]);
            }
            imei = sb.toString();

            byte[] respTimeBytes = new byte[4];// 响应时间【11-14】
            inputBinaryindex = copyArrays(inputBinary, ++inputBinaryindex, respTimeBytes.length,
                    respTimeBytes, 0)[0];
            chckAndFillBytes(respTimeBytes);
            long rspTimeInt = byte4Tolong(respTimeBytes, 0);

            byte[] resultBytes = new byte[1];// 命令结果【15】
            inputBinaryindex = copyArrays(inputBinary, ++inputBinaryindex, resultBytes.length, resultBytes,
                    0)[0];
            chckAndFillBytes(resultBytes);
            int result = byte1Toint(resultBytes, 0);

            decodeRsp.setIMEI(imei);
            decodeRsp.setMid(mid);
            decodeRsp.setResultCode(result);
            decodeRsp.setRspTime(new Date(rspTimeInt * 1000l));
        } catch (Exception e) {
            logger.warn("设置设备上传间隔设置,设备的响应解码错误！", e);
            return null;
        }
        return decodeRsp;
    }

    /**
     * @param inputBinary
     * @param inputBinaryindex
     * @return
     * @throws UnsupportedEncodingException
     * @Description (解码wifi数据)
     */
    private WifiUpload decodeWifi(byte[] inputBinary, int inputBinaryindex) {
        WifiUpload wifiUpload = new WifiUpload();
        try {
            inputBinaryindex = codecBasic(inputBinary, inputBinaryindex, wifiUpload);// imei 电量 厂商编号 产品型号 数据采集时间 --》解码

            byte[] lengthBytes = new byte[1];// length 【22】
            inputBinaryindex = copyArrays(inputBinary, ++inputBinaryindex, lengthBytes.length, lengthBytes,
                    0)[0];
            int length = byte1Toint(lengthBytes, 0);

            List<WifiLocation> wifiLocations = new ArrayList<WifiLocation>();
            for (int i = 0; i < length; i++) {// 遍历wifi数据
                byte[] bssidBytes = new byte[6]; // bssid 【23-28】
                inputBinaryindex = copyArrays(inputBinary, ++inputBinaryindex, bssidBytes.length, bssidBytes,
                        0)[0];
                if (!chckBytes(bssidBytes)) {
                    throw new UnsupportedEncodingException();
                }
                String bssid = byteTohex(bssidBytes);

                byte[] rssiBytes = new byte[1]; // rssi 【29】
                inputBinaryindex = copyArrays(inputBinary, ++inputBinaryindex, rssiBytes.length, rssiBytes,
                        0)[0];
                if (!chckBytes(rssiBytes)) {
                    throw new UnsupportedEncodingException();
                }
                float rssi = byte1Toint(rssiBytes, 0);

                WifiLocation wifiLocation = new WifiLocation();
                wifiLocation.setBssid(bssid);
                wifiLocation.setRssi(rssi);

                wifiLocations.add(wifiLocation);
            }
            wifiUpload.setLength(wifiLocations.size());
            wifiUpload.setWifiLocation(wifiLocations);
        } catch (Exception e) {
            logger.warn("解码wifi数据解码错误！", e);
            return null;
        }
        return wifiUpload;
    }

    /**
     * @param inputBinary
     * @param inputBinaryindex
     * @return
     * @throws UnsupportedEncodingException
     * @Description (省电模式数据解析)
     */
    private SimpleUpload decodeSimpleUp(byte[] inputBinary, int inputBinaryindex) {
        SimpleUpload simpleUpload = new SimpleUpload();
        try {
            inputBinaryindex = codecBasic(inputBinary, inputBinaryindex, simpleUpload);// imei 电量 厂商编号 产品型号 数据采集时间 --》解码
        } catch (Exception e) {
            logger.warn("设备开机请求数据解析失败！");
            e.printStackTrace();// 设备不存在
            return null;
        }
        return simpleUpload;
    }

    /**
     * @param inputBinary
     * @param inputBinaryindex
     * @throws UnsupportedEncodingException
     * @Description (告警数据解析)
     */
    private WarnUpload decodeWarnUp(byte[] inputBinary, int inputBinaryindex) {
        WarnUpload uploadBean = new WarnUpload();
        try {
            inputBinaryindex = codecBasic(inputBinary, inputBinaryindex, uploadBean);// imei 电量 厂商编号 产品型号 数据采集时间 --》解码
            if (inputBinary.length < 25) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            logger.warn("告警数据解析失败", e);
            e.printStackTrace();// 设备不存在
            return null;
        }

        byte[] warnTimeBytes = new byte[4]; // 告警时间 【22-25】
        inputBinaryindex = copyArrays(inputBinary, ++inputBinaryindex, warnTimeBytes.length, warnTimeBytes,
                0)[0];
        chckAndFillBytes(warnTimeBytes);

        long wanrTimeMi = byte4Tolong(warnTimeBytes, 0);

        uploadBean.setWarnTime(new Date(wanrTimeMi * 1000l));
        return uploadBean;
    }

    /**
     * @param inputBinary      数据源
     * @param inputBinaryindex 数据源下标
     * @throws UnsupportedEncodingException
     * @Description (gps数据解码)
     */
    private GpsUpload decodeGpsUp(byte[] inputBinary, int inputBinaryindex) {
        GpsUpload uploadBean = new GpsUpload();
        float longitude = 0;
        float latitude = 0;
        try {
            inputBinaryindex = codecBasic(inputBinary, inputBinaryindex, uploadBean);// imei 电量 厂商编号 产品型号 数据采集时间 --》解码
            // 经度
            byte[] longitudeBytes = new byte[4]; // 【22-25】
            inputBinaryindex = copyArrays(inputBinary, ++inputBinaryindex, longitudeBytes.length,
                    longitudeBytes, 0)[0];
            longitude = byte4TofloatIs(longitudeBytes, 0);
//            longitude = ByteUtils.byte4ToFloat(longitudeBytes, 0);

            // 纬度
            byte[] latitudeBytes = new byte[4]; // 【26-29】
            inputBinaryindex = copyArrays(inputBinary, ++inputBinaryindex, latitudeBytes.length,
                    latitudeBytes, 0)[0];
            latitude = byte4TofloatIs(latitudeBytes, 0);
//            latitude = ByteUtils.byte4ToFloat(latitudeBytes, 0);

            uploadBean.setLatitude(latitude);
            uploadBean.setLongitude(longitude);
        } catch (Exception e) {
            logger.warn("gps定位数据上传解析出错！", e);
            return null;
        }

        return uploadBean;
    }

    /**
     * @param cmdType  命令类型
     * @param cmdValue 命令值
     * @param mid      会话id
     * @return
     * @throws Exception
     * @Description (生成命令)
     */
    @Override
    public byte[] generateComanmd(CmdType cmdType, int cmdValue, int mid) {
        byte[] output = null;// 输出数据
        Date reqTime = new Date();// 命令发送时间
        try {
            if (CmdType.INTERVALTIME.equals(cmdType)) {// 设置间隔时间
                output = new byte[10];
                int outputIndex = 0;
                byte[] codeBytes = new byte[1];// 请求码 [0]
                intTobyte1(3, codeBytes, 0);// 00000011 --> 1
                outputIndex = copyArrays(codeBytes, 0, codeBytes.length, output, outputIndex)[1];

                byte[] midBytes = new byte[2];// 会话id [1-2] --> 2
                intTobyte2(mid, midBytes, 0);
                outputIndex = copyArrays(midBytes, 0, midBytes.length, output, ++outputIndex)[1];

                byte[] reqTimeBytes = new byte[4];// 下发时间 [3-6] --> 4
                longTobyte4(reqTime.getTime() / 1000, reqTimeBytes, 0);
                outputIndex = copyArrays(reqTimeBytes, 0, reqTimeBytes.length, output, ++outputIndex)[1];

                byte[] intervalTimeBytes = new byte[3];// 间隔时间 [6-9] --> 3
                intTobyte3(cmdValue, intervalTimeBytes, 0);
                outputIndex = copyArrays(intervalTimeBytes, 0, intervalTimeBytes.length, output,
                        ++outputIndex)[1];

            } else if (CmdType.WORKPATTERN.equals(cmdType)) {// 设置工作模式
                output = new byte[8];
                int outputIndex = 0;

                byte[] codeBytes = new byte[1];// 请求码 [0]
                intTobyte1(6, codeBytes, 0);// 00000011 --> 1
                outputIndex = copyArrays(codeBytes, 0, codeBytes.length, output, outputIndex)[1];

                byte[] midBytes = new byte[2];// 会话id [1-2] --> 2
                intTobyte2(mid, midBytes, 0);
                outputIndex = copyArrays(midBytes, 0, midBytes.length, output, ++outputIndex)[1];

                byte[] reqTimeBytes = new byte[4];// 下发时间 [3-6] --> 4
                longTobyte4(reqTime.getTime() / 1000, reqTimeBytes, 0);
                outputIndex = copyArrays(reqTimeBytes, 0, reqTimeBytes.length, output, ++outputIndex)[1];

                byte[] workTypeBytes = new byte[1];// 工作模式 [7] --> 1
                intTobyte1(cmdValue, workTypeBytes, 0);
                outputIndex = copyArrays(workTypeBytes, 0, workTypeBytes.length, output, ++outputIndex)[1];

            } else if (CmdType.STARTING_UP.equals(cmdType)) {// 响应开机
                output = new byte[6];
                output[0] = 0x09;

                byte[] rspTimeBytes = new byte[4];// 响应时间 [3-6] --> 4
                longTobyte4(reqTime.getTime() / 1000, rspTimeBytes, 0);

                copyArrays(rspTimeBytes, 0, rspTimeBytes.length, output, 1);
                // 输入参数为1为未激活状态
                output[5] = 1 == cmdValue ? (byte) 0x01 : (byte) 0x00;

            }
        } catch (Exception e) {
            logger.warn("命令拼装错误！");
            return null;
        }
        return output;
    }

    /**
     * @param inputBinary
     * @param inputBinaryindex
     * @param uploadBean
     * @return
     * @throws UnsupportedEncodingException 异常表示解析出错
     * @Description (解码请求 imei 电量 厂商编号 产品型号 数据采集时间)
     */
    private int codecBasic(byte[] inputBinary, int inputBinaryindex, UploadBean uploadBean)
            throws RuntimeException, UnsupportedEncodingException {
        if (inputBinary == null || inputBinary.length < 21 || uploadBean == null) {
            throw new RuntimeException();
        }

        byte[] IMEIBytes = new byte[8];// IMEI【1-8】
        inputBinaryindex = copyArrays(inputBinary, inputBinaryindex, IMEIBytes.length, IMEIBytes, 0)[0];
        chckAndFillBytes(IMEIBytes);
        String imei = byte2ToIntegerStr(IMEIBytes, 0, IMEIBytes.length);
        // 去掉最前面的0
        StringBuffer sb = new StringBuffer();
        char[] imeiChar = imei.toCharArray();
        for (int i = 1; i < imeiChar.length; i++) {
            sb.append(imeiChar[i]);
        }
        imei = sb.toString();
        uploadBean.setImei(imei);

        byte[] electricQuantityBytes = new byte[1];// 电量【9】
        inputBinaryindex = copyArrays(inputBinary, ++inputBinaryindex, electricQuantityBytes.length,
                electricQuantityBytes, 0)[0];
        if (!chckBytes(electricQuantityBytes)) {
            throw new RuntimeException();
        }
        int electricQuantity = byte1Toint(electricQuantityBytes, 0);
        uploadBean.setElectricQuantity(Double.valueOf(electricQuantity));

        byte[] factoryNoBytes = new byte[4];// 厂商编号【10-13】
        inputBinaryindex = copyArrays(inputBinary, ++inputBinaryindex, factoryNoBytes.length, factoryNoBytes,
                0)[0];
        if (!chckBytes(factoryNoBytes)) {
            throw new RuntimeException();
        }
        uploadBean.setFactoryNo(new String(factoryNoBytes, CHARSET));

        byte[] deviceTypeBytes = new byte[4];// 设备型号【14-17】
        inputBinaryindex = copyArrays(inputBinary, ++inputBinaryindex, deviceTypeBytes.length,
                deviceTypeBytes, 0)[0];
        if (!chckBytes(deviceTypeBytes)) {
            throw new RuntimeException();
        }
        uploadBean.setDeviceType(new String(deviceTypeBytes, CHARSET));

        byte[] upLoadTimeBytes = new byte[4];// 数据采集时间【18-21】
        inputBinaryindex = copyArrays(inputBinary, ++inputBinaryindex, upLoadTimeBytes.length,
                upLoadTimeBytes, 0)[0];
        if (!chckBytes(upLoadTimeBytes)) {
            throw new RuntimeException();
        }
        long upLoadTime = byte4Tolong(upLoadTimeBytes, 0);
        uploadBean.setUpLoadTime(new Date(upLoadTime * 1000l));

        return inputBinaryindex;
    }

}
