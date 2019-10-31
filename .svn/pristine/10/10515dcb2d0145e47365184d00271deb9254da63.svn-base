package com.soholy.cb.service.activemq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.soholy.cb.common.ApplicationContextProvider;
import com.soholy.cb.dao.AlarmRelatedInforMapper;
import com.soholy.cb.dao.TDeviceInfoDb;
import com.soholy.cb.entity.TDeviceCommandEntity;
import com.soholy.cb.entity.TDeviceDataWifiEntity;
import com.soholy.cb.entity.TDeviceInfoEntity;
import com.soholy.cb.entity.TDeviceRecordEntity;
import com.soholy.cb.entity.cdoec.CallBackData;
import com.soholy.cb.entity.iot.check.Msg;
import com.soholy.cb.entity.iot.check.Point;
import com.soholy.cb.entity.iot.check.RequiredForAlarm;
import com.soholy.cb.enums.CmdType;
import com.soholy.cb.service.TDeviceCommandService;
import com.soholy.cb.service.TDeviceDataWifiService;
import com.soholy.cb.service.TDeviceRecordService;
import com.soholy.cb.service.app.CmdService;
import com.soholy.cb.service.convert.WifiConvertService;
import com.soholy.cb.utils.Converter;
import com.soholy.cb.utils.DateUtils;
import com.soholy.cb.utils.FenceUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AmqConsumer implements Runnable {
    private static final Logger log = Logger.getLogger(AmqConsumer.class.getName());

    private static Map<String, Object> MAP = new HashMap();

    private WifiConvertService wifiConvertService;

    private CallBackData input;

    private TDeviceDataWifiService tDeviceDataWifiService;

    private TDeviceRecordService tDeviceRecordService;

    private TDeviceCommandService tDeviceCommandService;

    private CmdService cmdService;

    private AlarmRelatedInforMapper alarmRelatedInforMapper;

    private String thresholdValue;

    private TDeviceInfoDb tDeviceMapper;

    public AmqConsumer(String message) {
        try {
            this.input = (CallBackData) JSON.parseObject(message, CallBackData.class);
            this.tDeviceDataWifiService = (TDeviceDataWifiService) ApplicationContextProvider.getBean(TDeviceDataWifiService.class);
            this.tDeviceRecordService = (TDeviceRecordService) ApplicationContextProvider.getBean(TDeviceRecordService.class);
            this.tDeviceCommandService = (TDeviceCommandService) ApplicationContextProvider.getBean(TDeviceCommandService.class);
            this.cmdService = (CmdService) ApplicationContextProvider.getBean(CmdService.class);
            this.wifiConvertService = (WifiConvertService) ApplicationContextProvider.getBean(WifiConvertService.class);
            this.alarmRelatedInforMapper = (AlarmRelatedInforMapper) ApplicationContextProvider.getBean(AlarmRelatedInforMapper.class);
            Environment env = (Environment) ApplicationContextProvider.getBean(Environment.class);
            this.tDeviceMapper = (TDeviceInfoDb) ApplicationContextProvider.getBean(TDeviceInfoDb.class);
            this.thresholdValue = env.getProperty("iot.codec.scan.warn.thresholdValue");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        if (null == this.input || this.input.getDataType() == null)
            return;

        wifiToGps();

        saveData();

        checkData();

        scanCommand(this.input.gettDevice());
    }

    private void wifiToGps() {
        List<TDeviceDataWifiEntity> wfs = this.input.gettDeviceDataWifis();
        if (wfs != null && wfs.size() > 0)
            this.input.settDeviceDataWifis((List) wfs
                    .stream()
                    .filter(x -> (x != null))
                    .filter(x -> (x.getBssid() != null && x.getRssi() != null))
                    .map(x -> {
                        try {
                            String bssid = formatBssid(x.getBssid());
                            Float rssi = x.getRssi();
                            WifiConvertService.ConvertResult conver = this.wifiConvertService.wifiConverToGps(bssid, rssi);
                            Integer mark = conver.getMake();
                            x.setMark(mark);
                            if (mark.intValue() == 1) {
                                this.input.setLng(Double.valueOf(conver.getLongitude().doubleValue()));
                                this.input.setLat(Double.valueOf(conver.getLatitude().doubleValue()));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return x;
                    }).collect(Collectors.toCollection(ArrayList::new)));
    }

    private String formatBssid(String bssid) {
        if (StringUtils.isBlank(bssid) || bssid.trim().length() != 12)
            return null;
        bssid = bssid.trim();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bssid.length(); i += 2)
            sb.append(bssid.substring(i, i + 2)).append(":");
        bssid = sb.toString().substring(0, sb.toString().length() - 1);
        if (bssid.length() != 17)
            return null;
        return bssid;
    }

    private void scanCommand(TDeviceInfoEntity tDevice) {
        try {
            List<TDeviceCommandEntity> cmds = this.tDeviceCommandService.findCmdByNoAndStatus(tDevice.getDeviceNo(), Integer.valueOf(0));
            if (cmds != null)
                cmds.stream()
                        .forEach(x -> {
                            CmdType cmdType = (x.getCmdType().intValue() == 0) ? CmdType.WORKPATTERN : CmdType.INTERVALTIME;
                            Integer cmdValue = x.getCmdValue();
                            Integer cmdMid = Integer.valueOf((x.getCmdMid() == null) ? this.cmdService.generateMid() : x.getCmdMid().intValue());
                            JSONObject cmdJson = null;
                            try {
                                cmdJson = this.cmdService.sendCommand(cmdType, cmdValue, cmdMid, tDevice.getDeviceIotId(), input.getCodecVersion());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            String iotCommandId = null;
                            if (cmdJson != null)
                                iotCommandId = cmdJson.getString("commandId");
                            if (cmdJson != null && StringUtils.isNoneBlank(new CharSequence[]{cmdJson.getString("commandId")})) {
                                TDeviceCommandEntity tdevCmd = new TDeviceCommandEntity();
                                tdevCmd.setCmdStatus(Integer.valueOf(1));
                                tdevCmd.setIotCommandId(iotCommandId);
                                tdevCmd.setDeviceCommandId(x.getDeviceCommandId());
                                tdevCmd.setCmdIssuedTime(LocalDateTime.now());
                                this.tDeviceCommandService.updateById(tdevCmd);
                            } else {
                                log.warning("命令下发失败，命令id:" + x.getDeviceCommandId());
                            }
                        });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkData() {
        try {
            Integer dataType = this.input.getDataType();
            TDeviceInfoEntity tDevice = this.input.gettDevice();
            if (3 != dataType.intValue() && dataType.intValue() != 4) {
                RequiredForAlarm requiredForAlarm = null;
                List<Point> points = null;
                List<Point> points1 = null;
                Msg msg = new Msg();
                HashMap<String, RequiredForAlarm> map = new HashMap<String, RequiredForAlarm>();
                List<RequiredForAlarm> allInfo = this.alarmRelatedInforMapper.queryAllInfo();
                for (RequiredForAlarm info : allInfo)
                    map.put(info.getDeviceNo(), info);
                CallBackData callBackData = this.input;
                if (callBackData.getDataType().intValue() == 0 || callBackData.getDataType().intValue() == 1 || callBackData.getDataType().intValue() == 2) {
                    requiredForAlarm = null;
                    try {
                        requiredForAlarm = map.containsKey(tDevice.getDeviceNo()) ? (RequiredForAlarm) map.get(tDevice.getDeviceNo()) : null;
                        if (requiredForAlarm.getFenceId() != null) {
                            points = null;
                            points = this.alarmRelatedInforMapper.queryPoints(requiredForAlarm.getFenceId());
                            if (points.size() < 3)
                                log.warning("电子围栏校验，围栏数据有误，数量小于3!");
                            points1 = new ArrayList<Point>();
                            Point point = new Point();
                            point.setLatitude(callBackData.getLat());
                            point.setLongitude(callBackData.getLng());
                            points1.add(point);
                            points1 = Converter.WGS_84GCJ_02(points1);
                            point = (Point) points1.get(0);
                            double dLon1 = 0.0D, dLon2 = 0.0D, dLat1 = 0.0D, dLat2 = 0.0D;
                            int iSum = 0;
                            int iCount = points.size();
                            for (int iIndex = 0; iIndex < iCount; iIndex++) {
                                if (iIndex == points.size() - 1) {
                                    dLon1 = ((Point) points.get(iIndex)).getLongitude().doubleValue();
                                    dLat1 = ((Point) points.get(iIndex)).getLatitude().doubleValue();
                                    dLon2 = ((Point) points.get(0)).getLongitude().doubleValue();
                                    dLat2 = ((Point) points.get(0)).getLatitude().doubleValue();
                                } else {
                                    dLon1 = ((Point) points.get(iIndex)).getLongitude().doubleValue();
                                    dLat1 = ((Point) points.get(iIndex)).getLatitude().doubleValue();
                                    dLon2 = ((Point) points.get(iIndex + 1)).getLongitude().doubleValue();
                                    dLat2 = ((Point) points.get(iIndex + 1)).getLatitude().doubleValue();
                                }
                                if (((point.getLatitude().doubleValue() >= dLat1 && point.getLatitude().doubleValue() < dLat2) || (point.getLatitude().doubleValue() >= dLat2 && point.getLatitude().doubleValue() < dLat1)) &&
                                        Math.abs(dLat1 - dLat2) > 0.0D) {
                                    double dLon = dLon1 - (dLon1 - dLon2) * (dLat1 - point.getLatitude().doubleValue()) / (dLat1 - dLat2);
                                    double distance = FenceUtils.GetDistance(point.getLatitude().doubleValue(), dLon, point.getLatitude().doubleValue(), point.getLongitude().doubleValue());
                                    if (dLon < point.getLongitude().doubleValue() && distance >= 0.0D)
                                        iSum++;
                                }
                                if (iSum % 2 == 0) {
                                    msg.setTitle("出栏告警");
                                    msg.setContent("犬只活动超出围栏范围");
                                    msg.setStatus(Integer.valueOf(0));
                                    msg.setType(Integer.valueOf(1));
                                    msg.setCreateDate(new Date());
                                    Integer id = this.alarmRelatedInforMapper.insertMsg(msg);
                                    this.alarmRelatedInforMapper.refMsg(requiredForAlarm.getProtectorId(), id);
                                }
                            }
                        }
                        if (callBackData.getQuantity().doubleValue() <= Integer.valueOf(this.thresholdValue).intValue()) {
                            String deviceNo = callBackData.getDeviceNo();
                            String warningTime = DateUtils.format(new Date());
                            boolean flag = true;
                            if (MAP.containsKey(deviceNo + warningTime)) {
                                MAP.put(deviceNo + warningTime, deviceNo);
                                flag = false;
                            } else {
                                MAP.put(deviceNo + warningTime, deviceNo);
                            }
                            Map<String, Object> infos = this.tDeviceMapper.selectInfosByDeviceNo(deviceNo);
                            msg.setTitle("低电告警");
                            msg.setContent("项圈设备电量过低  告警时间: " + warningTime + "  电量:" + callBackData.getQuantity() + "  溯源ID:" + infos
                                    .get("traceId").toString() + "  犬主:" + infos.get("ownName").toString() + "  犬名:" + infos.get("dogName").toString());
                            msg.setStatus(Integer.valueOf(0));
                            msg.setType(Integer.valueOf(1));
                            msg.setCreateDate(new Date());
                            if (flag) {
                                Integer id = this.alarmRelatedInforMapper.insertMsg(msg);
                                this.alarmRelatedInforMapper.refMsg(requiredForAlarm.getProtectorId(), id);
                            }
                        }
                    } catch (Exception e) {
                        log.warning("告警异常！");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        try {
            this.tDeviceDataWifiService.saves(this.input.gettDeviceDataWifis());
            TDeviceInfoEntity device = this.input.gettDevice();
            String traceId = this.tDeviceMapper.selectTraceIdByDeviceNo(device.getDeviceNo());
            traceId = (traceId == null) ? "0" : traceId;
            TDeviceRecordEntity data = new TDeviceRecordEntity();
            data.setCreateDate(LocalDateTime.now());
            data.setDataTime(this.input.getDataTime());
            data.setDeviceNo(device.getDeviceNo());
            data.setQuantity(this.input.getQuantity());
            data.setUploadTime(this.input.getUploadTime());
            data.setId(this.input.getId());
            data.setTraceId(traceId);
            data.setVoltage(this.input.getVoltage());
            Integer dataType = this.input.getDataType();
            data.setDataType(dataType);
            double Lat = 0.0D;
            double Lng = 0.0D;
            if (dataType.intValue() == 0 || dataType.intValue() == 1) {
                Lat = this.input.getLat().doubleValue();
                Lng = this.input.getLng().doubleValue();
            }
            data.setLat(Double.valueOf(Lat));
            data.setLng(Double.valueOf(Lng));
            this.tDeviceRecordService.save(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
