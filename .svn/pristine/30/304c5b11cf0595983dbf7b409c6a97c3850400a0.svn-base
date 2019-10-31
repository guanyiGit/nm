package atshunhengli.com.service.impl;

import atshunhengli.com.common.ConstantIot;
import atshunhengli.com.entity.deviceManager.CommandDTONA2Cloud;
import atshunhengli.com.entity.deviceManager.UpdateDeviceInfoReqDTO;
import atshunhengli.com.entity.iot.TaskType;
import atshunhengli.com.mapper.TDeviceMapper;
import atshunhengli.com.service.AuthService;
import atshunhengli.com.service.DeviceManagerService;
import atshunhengli.com.utils.HttpClientUtil;
import atshunhengli.com.utils.HttpResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.entities.DeviceInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@PropertySource({"classpath:config/conf.properties"})
public class DeviceManagerServiceImpl implements DeviceManagerService {

    private static final Logger logger = LoggerFactory.getLogger(DeviceManagerServiceImpl.class);

    @Autowired
    private AuthService authService;

    @Value("${iot.codec.server.name}")
    private String serviceId;

    @Value("${iot.device.info.type}")
    private String deviceType;

    @Value("${iot.device.info.manufacturerId}")
    private String manufacturerId;
    @Value("${iot.device.info.model}")
    private String model;

    @Value("${iot.codec.command.name}")
    private String cmdName;

    @Value("${iot.codec.command.value}")
    private String cmdProp;

    @Value("${iot.application.appid}")
    private String appid;

    @Value("${iot.application.secret}")
    private String secret;

    @Autowired
    private TDeviceMapper tDeviceMapper;

    @Override
    public JSONObject register(String nodeId, String endUserId, String psk, Integer timeout) throws Exception {
        if (StringUtils.isBlank(nodeId)) {
            logger.warn("register方法  参数 nodeId 为空");
            return null;
        }
        String url = authService.iotServerBaseUrl() + ConstantIot.REGISTER_DEVICE;

        Map<String, String> headers = authService.setAuthentication();

        JSONObject paramsJSON = new JSONObject();
        paramsJSON.put("nodeId", nodeId);

        paramsJSON.put("verifyCode", nodeId);

        if (StringUtils.isNotBlank(endUserId)) {
            paramsJSON.put("endUserId", endUserId);
        }
        if (StringUtils.isNotBlank(psk)) {
            paramsJSON.put("psk", psk);
        }
        if (timeout != null) {
            paramsJSON.put("timeout", timeout);
        }

        HttpResult resp = HttpClientUtil.executePostParams(null, url, null, null, headers, paramsJSON.toJSONString());
        if (resp == null || resp.getStatusCode() != 200 || resp.getContent() == null) {
            logger.error("register error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    @Override
    public JSONObject register_gateway(String deviceId, String serviceId, CommandDTONA2Cloud message) throws Exception {
        String url = authService.iotServerBaseUrl() + ConstantIot.DISCOVER_INDIRECT_DEVICE;
        url = url.replace("{deviceId}", deviceId).replace("{serviceId}", serviceId);
        if (StringUtils.isBlank(deviceId) || StringUtils.isBlank(serviceId) || message == null
                || message.getHeader() == null) {
            logger.warn("register_gateway 方法参数有误");
            return null;
        }
        Map<String, String> headers = authService.setAuthentication();
        HttpResult resp = HttpClientUtil.executePostParams(null, url, null, null, headers,
                JSONObject.toJSONString(message));
        if (resp == null || resp.getStatusCode() != 202 || resp.getContent() == null) {
            logger.error("register_gateway error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    public JSONObject findDeviceStatus(String deviceId) throws Exception {
        if (StringUtils.isBlank(deviceId)) {
            logger.warn("getDeviceStatus 方法参数有误");
            return null;
        }
        String url = authService.iotServerBaseUrl() + ConstantIot.REGISTER_DEVICE + "/{deviceId}".replace("{deviceId}", deviceId);
        Map<String, String> paramsObj = new HashMap<String, String>();
        paramsObj.put("appId", authService.iotServerBaseUrl() + this.appid);
        HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, authService.setAuthentication(), paramsObj,
                null);
        if (resp == null || resp.getStatusCode() != 200 || resp.getContent() == null) {
            logger.error("getDeviceStatus error,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    @Override
    public boolean deleteDevice(String deviceId) throws Exception {
        String url = authService.iotServerBaseUrl() + ConstantIot.DELETE_DEVICE + "/{deviceId}";
        url = url.replace("{deviceId}", deviceId).replace("{appId}", authService.iotServerBaseUrl() + this.appid).replace("{cascade}", "0");

        if (StringUtils.isBlank(deviceId)) {
            logger.warn("deleteDevice 方法参数有误");
            return false;
        }

        Map<String, String> headers = authService.setAuthentication();
        HttpResult resp = HttpClientUtil.executeHttpParams(url, "DELETE", null, headers, null, null);
        if (resp != null && resp.getStatusCode() == 204) {
            logger.error("deleteDevice error,result statusCode:" + resp.getStatusCode());
            return true;
        }
        return false;
    }

    @Override
    public JSONObject deleteDevice_gateway(CommandDTONA2Cloud message) throws Exception {
        String url = authService.iotServerBaseUrl() + ConstantIot.DISCOVER_INDIRECT_DEVICE;
        url = url/* .replace("{deviceId}", deviceId).replace("{serviceId}", serviceId) */.replace("{appId}",
                authService.iotServerBaseUrl() + this.appid);

        if (message == null || message.getHeader() == null) {
            logger.warn("deleteDevice_gateway 方法参数有误");
            return null;
        }

        Map<String, String> headers = authService.setAuthentication();

        HttpResult resp = HttpClientUtil.executeHttpParams(url, "POST", null, headers, JSONObject.toJSONString(message),
                null);
        if (resp == null || resp.getStatusCode() != 202) {
            logger.error("deleteDevice_gateway error,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    @Override
    public boolean modifyDeviceId(String deviceId, UpdateDeviceInfoReqDTO updateDeviceInfoReqDTO) throws Exception {
        if (deviceId == null) {
            logger.warn("modifyDeviceName 方法参数有误");
            return false;
        }
        String url = authService.iotServerBaseUrl() + ConstantIot.MODIFY_DEVICE_INFO + "/{deviceId}?appId={appId}";
        url = url.replace("{deviceId}", deviceId).replace("{appId}", authService.iotServerBaseUrl() + this.appid);

        Map<String, String> headers = authService.setAuthentication();
        String paramsObj = null;
        if (updateDeviceInfoReqDTO != null) {
            paramsObj = JSON.toJSONString(updateDeviceInfoReqDTO);
        }
        HttpResult resp = HttpClientUtil.executeHttpParams(url, "PUT", null, headers, paramsObj, null);
        if (resp != null && resp.getStatusCode() == 204) {
            return true;
        }
        logger.error("modifyDeviceName error,result statusCode:" + resp.getStatusCode());
        return false;
    }

    @Override
    public JSONObject freshDeviceAuth(String deviceId, String verifyCode, String nodeId, Integer timeout)
            throws Exception {
        if (deviceId == null) {
            logger.warn("freshDeviceAuth 方法参数有误");
            return null;
        }
        String url = authService.iotServerBaseUrl() + ConstantIot.REGISTER_DEVICE + "/{deviceId}?appId={appId}";
        url = url.replace("{deviceId}", deviceId).replace("{appId}", authService.iotServerBaseUrl() + this.appid);

        Map<String, String> headers = authService.setAuthentication();

        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isNotBlank(verifyCode)) {
            jsonObject.put("verifyCode", verifyCode);
        }
        if (StringUtils.isNotBlank(nodeId)) {
            jsonObject.put("nodeId", nodeId);
        }
        if (timeout != null) {
            jsonObject.put("timeout", timeout);
        }

        HttpResult resp = HttpClientUtil.executeHttpParams(url, "PUT", null, headers, jsonObject.toJSONString(), null);

        if (resp == null || resp.getStatusCode() != 200) {
            logger.error("freshDeviceAuth error,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    @Override
    public JSONObject setEncryption(String deviceId, String serviceType, JSONObject jsonObject) throws Exception {
        if (deviceId == null || serviceType == null) {
            logger.warn("setEncryption 方法参数有误");
            return null;
        }
        String url = authService.iotServerBaseUrl() + ConstantIot.DELETE_DEVICE + "/{deviceId}/services/{serviceType}";
        url = url.replace("{deviceId}", deviceId).replace("{serviceType}", serviceType);

        Map<String, String> headers = authService.setAuthentication();

        HttpResult resp = HttpClientUtil.executeHttpParams(url, "PUT", null, headers, jsonObject, null);

        if (resp == null || resp.getStatusCode() != 200) {
            logger.error("setEncryption error,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    @Override
    public JSONObject findDeviceInfo(String deviceIdIot) throws Exception {
        if (StringUtils.isBlank(deviceIdIot)) {
            logger.warn("setEncryption 方法参数有误");
            return null;
        }
        String url = authService.iotServerBaseUrl() + ConstantIot.QUERY_DEVICES + "/{deviceId}";
        url = url.replace("{deviceId}", deviceIdIot);

        Map<String, String> headers = authService.setAuthentication();

        HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, headers, null, null);

        if (resp == null || resp.getStatusCode() != 200) {
            logger.error("setEncryption error,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    @Override
    public boolean modifyDeviceInfo(String deviceIdIot, String imei) throws Exception {
        if (StringUtils.isBlank(deviceIdIot)) {
            logger.warn("modifyDeviceInfo 方法参数有误");
            return false;
        }
        String url = authService.iotServerBaseUrl() + ConstantIot.MODIFY_DEVICE_INFO + "/{deviceId}";
        url = url.replace("{deviceId}", deviceIdIot);

        Map<String, String> headers = authService.setAuthentication();

        UpdateDeviceInfoReqDTO dto = new UpdateDeviceInfoReqDTO();
        dto.setDeviceType(deviceType);
        dto.setManufacturerId(manufacturerId);
        dto.setManufacturerName(manufacturerId);
//        dto.setMute("FALSE");
        dto.setProtocolType("CoAP");
        dto.setName(imei);
        dto.setLocation("Shenzhen");
        dto.setModel(model);
        HttpResult resp = HttpClientUtil.executeHttpParams(url, "PUT", null, headers, JSON.toJSONString(dto), null);

        if (resp == null || resp.getStatusCode() != 204) {
            logger.error("setEncryption error,result statusCode:" + resp.getStatusCode());
            return false;
        }
        return true;
    }



    public void registerList(List<Long> deviceIdList, int cmdValue) throws Exception {
        String taskName = Calendar.getInstance().getTime().toString();

        String url = authService.iotServerBaseUrl() + ConstantIot.CREATE_TASK_ALL;
        Map<String, String> headers = authService.setAuthentication();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appId", authService.iotServerBaseUrl() + this.appid);
        jsonObject.put("timeout", 9999999);// 超时时间
        jsonObject.put("taskName", taskName);
        jsonObject.put("taskType", "DeviceCmd");

        JSONObject param = new JSONObject();
        TaskType ttype = TaskType.DeviceList;// 批量命令类型
        param.put("type", ttype);
        if (TaskType.DeviceType.equals(ttype)) {
            param.put("deviceType", ttype);// 设备类型
        } else if (TaskType.DeviceArea.equals(ttype)) {
            param.put("deviceLocation", ttype);// 设备位置
        } else if (TaskType.GroupList.equals(ttype)) {
            param.put("groupList", ttype);// 组名字列表 List<String>
        } else if (TaskType.DeviceList.equals(ttype)) {
            List<DeviceInfo> tdeviceList = tDeviceMapper.findDeviceListByIds(deviceIdList);
            List<String> tdeviceNumList = new ArrayList<>();
            for (DeviceInfo tDevice : tdeviceList) {// 设备型号
                tdeviceNumList.add(tDevice.getDeviceNo());
            }
            param.put("deviceList", tdeviceNumList);// 组名字列表 List<String> deviceId列表

        }

        JSONObject CommandDTOV1 = new JSONObject();
        CommandDTOV1.put("serviceId", serviceId);
        CommandDTOV1.put("method", cmdName);// 命令名称，服务属性等
        JSONObject parasNode = new JSONObject();
        parasNode.put(cmdProp, cmdValue);
        CommandDTOV1.put("paras", parasNode);// 命令参数 jsonString

        param.put("command", CommandDTOV1);// 组名字列表 List<String>

        JSONArray deviceList = new JSONArray();
        // deviceList.add(e)

        param.put("deviceList", deviceList);// deviceId列表

        jsonObject.put("param", param);
        HttpResult resp = HttpClientUtil.executeHttpParams(url, "PUT", null, headers, jsonObject, null);

    }

    // "deviceId": "*******",
    // "verifyCode": "*******",
    // "psk": "*********",
    // "timeout": 300

    @Override
    public List<DeviceInfo> registerAll(List<DeviceInfo> deviceList) throws Exception {
        if (deviceList != null && deviceList.size() > 0) {
            for (DeviceInfo tDevice : deviceList) {
                JSONObject result = this.register(tDevice.getImei(), null, null, 0);
                if (result != null) {// 注册成功
                    String deviceIdIot = result.getString("deviceId");
                    this.modifyDeviceInfo(deviceIdIot, deviceIdIot);//完善设备信息
                    String verifyCode = result.getString("verifyCode");
                    String psk = result.getString("psk");
//                    tDevice.setVerifyCode(verifyCode);
                    tDevice.setPsk(psk);
                    tDevice.setDeviceIotId(deviceIdIot);
                } else {
                    logger.error("设备注册失败！ 设备信息:" + tDevice);
                    return null;
                }
            }
            return deviceList;
        }
        return null;
    }


    @Override
    public boolean deleteDeviceByIotId(String deviceIdIot) throws Exception {
        if (tDeviceMapper.removeByDeviceIdIot(deviceIdIot) >= 0) {
            return this.deleteDevice(deviceIdIot);
        }
        return false;
    }

    @Override
    public DeviceInfo register(String imei, String deviceBrand, String deviceBatch) throws Exception {
        JSONObject result = this.register(imei, null, null, 0);
        if (result != null) {
            String deviceIdIot = result.getString("deviceId");
            String verifyCode = result.getString("verifyCode");
            String psk = result.getString("psk");

            DeviceInfo tDevice = new DeviceInfo();
            tDevice.setImei(imei);
            tDevice.setBatch(StringUtils.isBlank(deviceBrand)?deviceBrand:"无");
            tDevice.setBrand(StringUtils.isBlank(deviceBrand)?deviceBrand:"无");
            tDevice.setCreateDate(new Date());
            tDevice.setType(1);
            tDevice.setPsk(psk);
            tDevice.setDeviceNo(imei);
            tDevice.setDeviceIotId(deviceIdIot);


            //TODO  实际不能要
            tDevice.setDateOfProduction(new Date());
            tDevice.setOrgId(1);
            tDevice.setStartTime(new Date());
            tDevice.setActivateTime(new Date());
            tDevice.setStatus(1);
            tDevice.setModel(StringUtils.isBlank(deviceBrand)?deviceBrand:"无");
            tDevice.setRemarks(verifyCode);


            //插入设备并且修改设备信息
            if (tDeviceMapper.save(tDevice) == 1 && this.modifyDeviceInfo(deviceIdIot, imei)) {
                return tDevice;
            }
            this.deleteDeviceByIotId(deviceIdIot);
            throw new RuntimeException();
        }
        return null;
    }

}
