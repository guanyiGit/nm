package atshunhengli.com.service.impl;

import atshunhengli.com.entity.callback.*;
import atshunhengli.com.entity.callback.Header;
import atshunhengli.com.entity.cdoec.*;
import atshunhengli.com.entity.iot.CmdType;
import atshunhengli.com.mapper.*;
import atshunhengli.com.service.CodecService;
import atshunhengli.com.service.DeviceCommandIotService;
import atshunhengli.com.service.IotCallbackService;
import atshunhengli.com.service.LogService;
import atshunhengli.com.utils.Converter;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.entities.DeviceInfo;
import com.entities.DeviceRecord;
import com.entities.TDeviceCommand;
import com.entities.TDeviceDataWifi;
import com.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@PropertySource({"classpath:config/conf.properties"})
public class IotCallbackServiceImpl implements IotCallbackService {

    private static Map<String, Object> map = new HashMap<>();

    @Value("${iot.codec.param.name}")
    private String codecParamName;

    @Value("${iot.codec.scan.warn.thresholdValue}")
    private Integer thresholdValue;

    private static final String DECODE_CHARSET = "ISO8859-1";

    @Autowired
    private LogService logService;


    private static final Logger logger = LoggerFactory.getLogger(IotCallbackServiceImpl.class);

    @Autowired
    private CodecService codecService;

    @Autowired
    private TDeviceMapper tDeviceMapper;

    @Autowired
    private DeviceCommandIotService deviceCommandIotService;

    @Autowired
    private TDeviceDataWifiMapper tDeviceDataWifiMapper;

    @Autowired
    private TDeviceDataMapper tDeviceDataMapper;

    @Autowired
    private AlarmRelatedInforMapper alarmRelatedInforMapper;

    @Autowired
    private TDeviceCommandMapper tDeviceCommandMapper;

    @Override
    public boolean deviceDatasChangedMsg(JSONObject json) {
        try {//一台设备的多条数据
            String requestId = json.getString("requestId");
            String deviceIdIot = json.getString("deviceId");
            String gatewayId = json.getString("gatewayId");
            JSONArray jsonArray = json.getJSONArray("services");

            // 根据设备验证码查询设备信息
            List<DeviceInfo> tdeviceList = tDeviceMapper.findDeviceInfoByDeviceidIot(deviceIdIot);
            String deviceNO = null;// 设备id
            if (tdeviceList != null && tdeviceList.size() == 1) {// 判断设备是否在系统中已存在
                deviceNO = tdeviceList.get(0).getDeviceNo();
            } else {
                logger.info("设备上传数据设备id不存在！");
                return false;
            }

            //设备批量数据处理
            List<CallBackData> datas = new ArrayList<CallBackData>();
            for (int i = 0; jsonArray != null && i < jsonArray.size(); i++) {// 遍历设备数据
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String serviceId = jsonObject.getString("serviceId");
                String serviceType = jsonObject.getString("serviceType");
                String eventTime = jsonObject.getString("eventTime");
                JSONObject dataNode = jsonObject.getJSONObject("data");
                String dataStr = dataNode.getString(codecParamName);
                byte[] inputBinary = dataStr.getBytes(DECODE_CHARSET);
                logger.info("serviceId:" + serviceId);
                logger.info("serviceType:" + serviceType);
                logger.info("eventTime:" + eventTime);

                CodecBean codecBean = codecService.decodec(inputBinary);//数据解析

                //日志输出
                logService.printLog(codecBean, inputBinary, null);


                if (codecBean != null) {
                    UploadBean uploadBean = codecBean.getUploadBean();
                    DecodeRsp decodeRsp = codecBean.getDecodeRsp();
                    //成功解析请求的数据
                    if (uploadBean != null && uploadBean.getImei() != null) {
                        uploadBean.setDeviceNo(deviceNO);
                        // 组装 设备数据
                        CallBackData dwifiData = dataPrepare(uploadBean);
                        if (dwifiData != null) {
                            datas.add(dwifiData);
                        } else {
                            logger.warn("数据组装失败！");
                        }
                    }
                    //设备对命令的响应 上报结果
                    if (decodeRsp != null && decodeRsp.getIMEI() != null) {
                        decodeRsp.setDeviceNo(deviceNO);
                        this.cmdResHandle(decodeRsp);//命令入口操作
                    }
                }
            }
            logger.info("deviceId msg input:" + json);
            logger.info("requestId:" + requestId);
            logger.info("gatewayId:" + gatewayId);


            if (datas != null && datas.size() > 0) {

                // 接收到的数据处理  分析 入库 告警短信和告警新增
                this.iotDataHandle(tdeviceList.get(0), datas);
            }

        } catch (Exception e) {
            logger.warn("数据解析失败 ", e);
            return false;
        }
        return true;
    }

    private int getMid() {
        return (int) (Math.random() * 999);
    }

    /**
     * 命令响应处理
     *
     * @param decodeRsp
     * @return
     */
    private boolean cmdResHandle(DecodeRsp decodeRsp) {
        if (decodeRsp != null) {
            int resMid = decodeRsp.getMid();//命令响应后改变命令状态和时间

            TDeviceCommand command = new TDeviceCommand();
            command.setCmdStatus(5);
            command.setCmdRspTime(decodeRsp.getRspTime());
            String deviceNo = decodeRsp.getDeviceNo();
            Integer cmdStatus = 0;
            int count = tDeviceCommandMapper.updateStatusByNo(command, deviceNo, cmdStatus);
            if (count == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param deviceIdIot
     * @throws Exception
     * @Description (检查设备有没有缓存的命令等待下发)
     */
    private void findDeviceConmandAndSend(String deviceIdIot) throws Exception {
        if (StringUtils.isNoneBlank(deviceIdIot)) {
            //查询该设备的待发送命令
            List<TDeviceCommand> cmds = tDeviceCommandMapper.findCommandByDeviceIotId(deviceIdIot, 0);
            if (cmds != null && cmds.size() > 0) {
                for (TDeviceCommand tDeviceCommand : cmds) {
                    //0 模式设置  1间隔时间设置
                    CmdType cmdType = tDeviceCommand.getCmdType() == 0 ? CmdType.WORKPATTERN : CmdType.INTERVALTIME;
                    Integer cmdValue = tDeviceCommand.getCmdValue();
                    Integer cmdMid = tDeviceCommand.getCmdMid();
                    //命令发送
                    JSONObject cmdJson = deviceCommandIotService.sendCommand(cmdType, cmdValue, cmdMid, deviceIdIot);
                    String iotCommandId = null;
                    if (cmdJson != null) {
                        iotCommandId = cmdJson.getString("commandId");
                    }

//                    JSONObject sendCommandJson = deviceCommandIotService.sendCommand(cmdType, cmdValue, cmdMid, deviceIdIot);

                    if (cmdJson != null && StringUtils.isNoneBlank(cmdJson.getString("commandId"))) {
                        //修改命令状态
                        TDeviceCommand tdevCmd = new TDeviceCommand();
                        tdevCmd.setCmdStatus(1);
                        tdevCmd.setIotCommandId(iotCommandId);// 返回的结果和iotcmdid
                        tdevCmd.setDeviceCommandId(tDeviceCommand.getDeviceCommandId());
                        tdevCmd.setCmdIssuedTime(new Date());

                        tDeviceCommandMapper.updateStatusById(tdevCmd);

                    } else {// 命令下发成功 修改命令状态
                        logger.error("命令下发失败，命令id:" + tDeviceCommand.getDeviceCommandId());
                    }
                }
            }
        }
    }


    /**
     * @param tDevice
     * @param datas
     * @throws Exception
     * @Description (数据处理)
     */
    public boolean iotDataHandle(DeviceInfo tDevice, List<CallBackData> datas) throws Exception {
        if (datas == null || datas.size() == 0) {
            return false;
        }

        //0 gps 1wifi 2 simple 3warn 4 start

        //是否是开机数据 开机数据不校验告警
        if (datas.get(0).getDataType() == 4) {
            int resultCode = 0;//默认未激活
            if (tDevice != null && tDevice.getStatus() == 1) {//设备已激活
                resultCode = 1;//已激活
            }
            //下发开机回复
            JSONObject sendCommand = deviceCommandIotService.sendCommand(CmdType.STARTING_UP, resultCode, this.getMid(), tDevice.getDeviceIotId());
            return true;
        }

        // 收到设数据，检查有没有要下发的命令进行下发 开机不下发命令
        this.findDeviceConmandAndSend(tDevice.getDeviceIotId());

        // wifi数据
        List<TDeviceDataWifi> wifiDatas = new ArrayList<>();
        for (CallBackData callBackData : datas) {
            List<TDeviceDataWifi> wifiData = callBackData.gettDeviceDataWifis();
            if (wifiData != null) {
                wifiDatas.addAll(wifiData);// wifi数据
            }
        }
        if (wifiDatas != null && wifiDatas.size() > 0) {// 保存wifi数据
            if (tDeviceDataWifiMapper.saveDeviceWifiDatas(wifiDatas) != wifiDatas.size()) {
                logger.warn("设备上传wifi数据保存失败！");
            } else {
                //TODO 执行转换wifi定位逻辑

            }
        }
        //wifi和 gps数据 禁养区域检测和低电量 告警
        RequiredForAlarm requiredForAlarm = null;
        List<Point> points = null;
        List<Point> points1 = null;
        Msg msg = new Msg();

        HashMap<String, RequiredForAlarm> map = new HashMap<>();
        List<RequiredForAlarm> allInfo = alarmRelatedInforMapper.queryAllInfo();
        for (RequiredForAlarm info : allInfo) {
            map.put(info.getDeviceNo(), info);
        }

        for (CallBackData callBackData : datas) {
            if (callBackData.getDataType() == 0 || callBackData.getDataType() == 1 || callBackData.getDataType() == 2) {
                requiredForAlarm = null;
                //出栏告警判断
                try {
//                    requiredForAlarm = alarmRelatedInforMapper.queryInfo(tDevice.getDeviceNo());
                    requiredForAlarm = map.containsKey(tDevice.getDeviceNo()) ? map.get(tDevice.getDeviceNo()) : null;

                    if (requiredForAlarm.getFenceId() != null) {
                        points = null;
                        points = alarmRelatedInforMapper.queryPoints(requiredForAlarm.getFenceId());
                        if (points.size() >= 3) {
                            points1 = new ArrayList<Point>();
                            Point point = new Point();
                            point.setLatitude(callBackData.getLat());
                            point.setLongitude(callBackData.getLng());
                            points1.add(point);
                            points1 = Converter.WGS_84GCJ_02(points1);
                            point = points1.get(0);
                            int iSum, iCount, iIndex;
                            double dLon1 = 0, dLon2 = 0, dLat1 = 0, dLat2 = 0, dLon;
                            iSum = 0; // 交点个数
                            iCount = points.size(); // 顶点个数
                            for (iIndex = 0; iIndex < iCount; iIndex++) {
                                // 如果当前坐标是最后一个坐标，则与第一个点组成两点连线
                                if (iIndex == points.size() - 1) {
                                    // 坐标一
                                    dLon1 = points.get(iIndex).getLongitude();
                                    dLat1 = points.get(iIndex).getLatitude();
                                    // 坐标二
                                    dLon2 = points.get(0).getLongitude();
                                    dLat2 = points.get(0).getLatitude();
                                } else {
                                    // 坐标一
                                    dLon1 = points.get(iIndex).getLongitude();
                                    dLat1 = points.get(iIndex).getLatitude();
                                    // 坐标二
                                    dLon2 = points.get(iIndex + 1).getLongitude();
                                    dLat2 = points.get(iIndex + 1).getLatitude();
                                }
                                // 以下语句判断A点是否在边的两端点的水平平行线之间，在则可能有交点，开始判断交点是否在左射线上
                                if (((point.getLatitude() >= dLat1) && (point.getLatitude() < dLat2)) || ((point.getLatitude() >= dLat2) && (point.getLatitude() < dLat1))) {
                                    if (Math.abs(dLat1 - dLat2) > 0) {
                                        // 得到 A点向左射线与边的交点的x坐标：
                                        dLon = dLon1 - ((dLon1 - dLon2) * (dLat1 - point.getLatitude())) / (dLat1 - dLat2);
                                        // 判断坐标点与交点之间的距离
                                        double distance = FenceUtils.GetDistance(point.getLatitude(), dLon, point.getLatitude(), point.getLongitude());
                                        // 如果交点在A点左侧且两点之间距离大于x米（说明是做射线与 边的交点），则射线与边的全部交点数加一：
                                        if (dLon < point.getLongitude() && distance >= 0) {
                                            iSum++;
                                        }
                                    }
                                }
                                // 如果交点个数为奇数，则在区域范围类
                                // 如果交点个数为偶数，则在区域范围外
                                if ((iSum % 2) == 0) {
                                    msg.setTitle("出栏告警");
                                    msg.setContent("犬只活动超出围栏范围");
                                    msg.setStatus(0);
                                    msg.setType(0);
                                    msg.setCreateDate(new Date());
                                    alarmRelatedInforMapper.insertMsg(msg);
                                    alarmRelatedInforMapper.refMsg(requiredForAlarm.getProtectorId(), msg.getId());
                                }
                            }
                        }
                    }

                    //低电告警判断
                    if (callBackData.getQuantity() <= thresholdValue) {
                        Msg mg = new Msg();
                        /**
                         * 告警信息中要有:时间，电量，溯源号，犬主，犬名。
                         */
                        String deviceNo = callBackData.getDeviceNo();
                        String warningTime = DateUtils.format(new Date());
                        boolean flag = true;
                        if (IotCallbackServiceImpl.map.containsKey(deviceNo + warningTime)) {
                            IotCallbackServiceImpl.map.put(deviceNo + warningTime, deviceNo);
                            flag = false;
                        } else {
                            IotCallbackServiceImpl.map.put(deviceNo + warningTime, deviceNo);
                        }
                        Map<String, Object> infos = tDeviceMapper.selectInfosByDeviceNo(deviceNo);
                            mg.setTitle("低电告警");
                            mg.setContent("$  $: " + warningTime + "  $:" + callBackData.getQuantity()
                                    + "  $:" + infos.get("traceId").toString() + "  $:" + infos.get("ownName").toString() + "  $:" + infos.get("dogName").toString());
                            mg.setStatus(0);
                            mg.setType(1);
                            mg.setCreateDate(new Date());
                            if (flag) {
                            alarmRelatedInforMapper.insertMsg(mg);
                            alarmRelatedInforMapper.refMsg(requiredForAlarm.getProtectorId(), mg.getId());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("告警异常！");
                }
            }
        }

        //保存设备数据
        List<DeviceRecord> dtdeviceDatas = datas.stream()
                .map(x -> {
                    String deviceNo = x.getDeviceNo();
                    //获取traceId
                    String traceId = tDeviceMapper.selectTraceIdByDeviceNo(deviceNo);

                    DeviceRecord data = new DeviceRecord();
                    data.setCreateDate(new Date());
                    data.setDataTime(x.getUploadTime());
                    data.setDeviceNo(deviceNo);
                    data.setQuantity(x.getQuantity());
                    data.setUploadTime(x.getUploadTime());
                    data.setId(x.getId());
                    data.setTraceId(traceId);

                    Integer dataType = x.getDataType();
                    data.setDataType(dataType);
                    if (dataType == 0 || dataType == 1) {//gps数据
                        data.setLat(x.getLat());
                        data.setLng(x.getLng());
                    }

                    return data;
                })
                .collect(Collectors.toList());

        //保存数据
        int count = tDeviceDataMapper.saveDatas(dtdeviceDatas);
        if (count < dtdeviceDatas.size()) {
            logger.warn(dtdeviceDatas.size() - count + "条上传数据保存失败！");
        }
        return true;
    }

    @Override
    public boolean deviceAddedMeg(JSONObject json) throws Exception {
        String deviceId = json.getString("deviceId");
//        String resultCode = json.getString("resultCode");
        DeviceInfo deviceInfo = json.getObject("deviceInfo", DeviceInfo.class);
        if (StringUtils.isNotBlank(deviceId)) {
            logger.info("设备 deviceId:[" + deviceId + "]添加成功！deviceInfo:[" + deviceInfo + "]");
            return true;
        }
        logger.info("设备 deviceId:[" + deviceId + "]添加失败！deviceInfo:[" + deviceInfo + "]");
        return false;
    }

    @Override
    public void deviceInfoChangedMsg(JSONObject json) {
        String deviceId = json.getString("deviceId");
        DeviceInfo deviceInfo = json.getObject("deviceInfo", DeviceInfo.class);
        logger.info("设备信息修改成功 ! deviceId：[" + deviceId + "] deviceInfo" + deviceInfo + "]");
    }

    @Override
    public void deviceDeletedMsg(JSONObject json) {
        String deviceId = json.getString("deviceId");
        logger.info("设备 deviceId：[" + deviceId + "] 已删除!");
    }

    @Override
    public void messageConfirmMsg(JSONObject json) {
        Header header = json.getObject("header", Header.class);
        JSONObject body = json.getJSONObject("body");
        String status = header.getStatus();
        if ("sent".equals(status)) {
            logger.info("消息已发送至设备网关");
        } else if ("delivered".equals(status)) {
            logger.info("消息已送达至设备网关");
        } else if ("executed".equals(status)) {
            logger.info("设备网关消息已执行");
        }

    }

    @Override
    public void commandRspMsg(JSONObject json) {
        Header header = json.getObject("header", Header.class);
        // JSONObject body = json.getJSONObject("body");
        logger.info("response command :命令已响应" + "deviceId:[" + header.getDeviceId() + "]");
    }

    @Override
    public void deviceEventMsg(JSONObject json) {
        Header header = json.getObject("header", Header.class);
        // JSONObject body = json.getJSONObject("body");
        logger.info("设备事件上传,事件为：" + header.getEventType() + "  deviceId:[" + header.getDeviceId() + "]");
    }

    @Override
    public void bindDevicetMsg(JSONObject json) {
        String deviceId = json.getString("deviceId");
        String resultCode = json.getString("resultCode");
        if ("expired".equals(resultCode)) {
            logger.info("deviceId:[" + deviceId + "]设备登录成功");
        } else if ("succeeded".equals(resultCode)) {
            logger.info("deviceId:[" + deviceId + "]设备登录失败");
        }
    }

    /**
     * @param uploadBean
     * @return
     * @Description (数据组装)
     */
    private CallBackData dataPrepare(UploadBean uploadBean) {
        if (uploadBean == null) {
            return null;
        }
        CallBackData parasData = new CallBackData();// 需要保存的记录

        parasData.setDeviceNo(uploadBean.getDeviceNo());
        parasData.setQuantity(uploadBean.getElectricQuantity());
        parasData.setDataTime(Calendar.getInstance().getTime());
        parasData.setUploadTime(uploadBean.getUpLoadTime());
        parasData.setCreateDate(new Date());
        parasData.setId(UUID.randomUUID().toString());

        int dataType = 0;// 上传的数据类型
        if (uploadBean instanceof WifiUpload) {
            WifiUpload wifiUpload = (WifiUpload) uploadBean;
            List<TDeviceDataWifi> dwifiDataList = new ArrayList<TDeviceDataWifi>();
            for (WifiLocation wifiLocation : wifiUpload.getWifiLocation()) {
                TDeviceDataWifi wifi = new TDeviceDataWifi();
                wifi.setDeviceNo(uploadBean.getDeviceNo());
                wifi.setDeviceDataWifiId(UUID.randomUUID().toString());
                wifi.setCreationTime(new Date());
                wifi.setBssid(wifiLocation.getBssid());
                wifi.setDeviceDataId(parasData.getId());
                wifi.setRssi(wifiLocation.getRssi());
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
            logger.info("数据上传标识码解析错误！");
            return null;
        }
        parasData.setDataType(dataType);

        return parasData;
    }

    @Override
    public void deviceDataChangedMeg(JSONObject json) {
        // TODO Auto-generated method stub 数据变化已实现

    }

    @Override
    public List<DeviceInfo> testLowerPower() {
        List<DeviceInfo> deviceInfos = tDeviceMapper.testLowerPower();
        return deviceInfos;
    }

    @Override
    public Map<String, Object> getMap() {
        return map;
    }

    @Override
    public void setMap(HashMap<String, Object> maps) {
        map = maps;
    }

}
