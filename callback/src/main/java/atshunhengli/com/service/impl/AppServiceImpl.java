package atshunhengli.com.service.impl;

import atshunhengli.com.common.ConstantIot;
import atshunhengli.com.entity.app.RuleDTO1_2;
import atshunhengli.com.entity.deviceManager.CommandDTOV4;
import atshunhengli.com.entity.deviceManager.CommandNA2CloudHeader;
import atshunhengli.com.entity.iot.CallbackType;
import atshunhengli.com.service.AppService;
import atshunhengli.com.service.AuthService;
import atshunhengli.com.utils.HttpClientUtil;
import atshunhengli.com.utils.HttpResult;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 平台与电信交互相关
 *
 * @author guanY
 * @version 1.0.0
 * @ClassName AppServiceImpl
 * @Description (Application订阅设备变更通知 ， 当设备发生变更时平台会推送给Application 。)
 * @Date 2018年4月10日 下午4:28:32
 */
@Service
@PropertySource({"classpath:config/conf.properties"})
public class AppServiceImpl implements AppService {

    private static final Logger logger = Logger.getLogger(AppServiceImpl.class);

    @Autowired
    private AuthService authService;



    @Value("${iot.application.appid}")
    private String appid;

    /**
     * @param notifyType:必选  String  通知类型，如：
     *                       1、deviceAdded（添加新设备）
     *                       2、deviceInfoChanged（设备信息变化）
     *                       3、deviceDataChanged（设备数据变化）
     *                       4、deviceDeleted（删除设备）
     *                       5、deviceEvent（设备事件）
     *                       6、messageConfirm（消息确认）
     *                       7、commandRsp（响应命令）
     *                       8、serviceInfoChanged（设备信息）
     *                       9、ruleEvent（规则事件）
     *                       10、bindDevice（设备绑定激活）
     *                       11、deviceDatasChanged（设备数据批量变化）
     * @param callbackurl:必选 String  回调的url地址。必须使用HTTPS信道回调地址，同时回调地址中必须指定回调地址的端口。(说明：HTTP信道只可用于调测)
     * @return 401  100002  Invalid access token.   错误的token信息
     * 400 100222  The request callbackurl is illegal.     回调地址非法
     * 200 100203  The application is not existed.     鉴权应用不存在
     * 500 101017  Get newCallbackUrl from oss failed.     从oss获取新的回调地址失败
     * 500 101016  Get iotws address failed.   获取iotws地址失败
     * @throws Exception
     * @Description (Application订阅设备变更通知 ， 当设备发生变更时平台会推送给Application)
     */
    @Override
    public boolean subscribe(CallbackType notifyType, String callbackurl) throws Exception {
        String url = authService.iotServerBaseUrl() + ConstantIot.SUBSCRIBE_NOTIFYCATION;

        Map<String, String> headers = authService.setAuthentication();

        Map<String, String> paramsObj = new HashMap<String, String>();
        paramsObj.put("notifyType", notifyType.toString());
        paramsObj.put("callbackurl", callbackurl);// 服務器地址
        HttpResult resp = HttpClientUtil.executeHttpParams(url, "POST", null, headers,
                JSONObject.toJSONString(paramsObj), null);

        if (resp != null && resp.getStatusCode() == 201) {
            return true;
        }
        logger.info("subscribe error ,result: " + resp);
        return false;
    }

    @Override
    public JSONObject findDeviceHistory(String deviceId, String gatewayId, String serviceId, String property,
                                        int pageNo, int pageSize, String startTime, String endTime) throws Exception {
        if (StringUtils.isBlank(deviceId) || StringUtils.isBlank(gatewayId)) {
            logger.warn("findDeviceHistory 方法参数有误");
            return null;
        }
        String url = authService.iotServerBaseUrl() + ConstantIot.QUERY_DEVICE_HISTORY_DATA;

        Map<String, String> headers = authService.setAuthentication();

        Map<String, String> paramsObj = new HashMap<String, String>();
        paramsObj.put("deviceId", deviceId);
        paramsObj.put("gatewayId", gatewayId);
        paramsObj.put("appId ", authService.iotServerBaseUrl() + this.appid);
        if (StringUtils.isNotBlank(serviceId)) {
            paramsObj.put("serviceId", serviceId);
        }
        if (StringUtils.isNotBlank(startTime)) {
            paramsObj.put("startTime ", startTime);
        }

        HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, headers, paramsObj, null);
        if (resp == null || resp.getStatusCode() != 200 || resp.getContent() == null) {
            logger.error("findDeviceHistory error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    @Override
    public JSONObject findDeviceCapabilities(String deviceId, String gatewayId) throws Exception {
        if (StringUtils.isBlank(deviceId)) {
            logger.warn("findDeviceCapabilities 方法参数有误");
            return null;
        }
        String url = authService.iotServerBaseUrl() + ConstantIot.QUERY_DEVICE_CAPABILITIES;
        url = url + ("?deviceId={deviceId}").replace("{deviceId}", deviceId);
        Map<String, String> headers = authService.setAuthentication();

        Map<String, String> paramsObj = null;
        if (StringUtils.isNotBlank(gatewayId)) {
            paramsObj = new HashMap<String, String>();
            paramsObj.put("deviceId", deviceId);
        }

        HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, headers,
                JSONObject.toJSONString(paramsObj), null);
        if (resp == null || resp.getStatusCode() != 200 || resp.getContent() == null) {
            logger.error("findDeviceCapabilities error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    /**
     * @param deviceId      必选   String(64)  下发命令的设备ID
     * @param command       必选    CommandDTOV4    下发命令的信息
     * @param callbackUrl   可选    String(1024)    命令状态变化通知地址，必须使用HTTPS信道回调地址 (说明：HTTP信道只可用于调测，不可用于商用环境)；
     *                      当命令状态变化时(执行失败，执行成功，超时，发送，已送达)会通知NA，平台会以POST方式发送HTTP消息给应用，请求Body为json字符串，格式形如：{"deviceId":"deviceId","commandId":"commandId","result":{"status":"SUCCESS","result":{…}}}
     * @param expireTime:可选 Integer(>=0)    下发命令有效的超期时间，单位为秒，表示设备命令在创建后expireTime秒内有效，超过这个时间范围后命令将不再下发，如果未设置则默认为48小时
     * @return
     * @throws Exception
     * @Description (应用创建设备命令 。)
     */
    @Override
    public JSONObject createDeviceCommand(String deviceId, CommandDTOV4 command, String callbackUrl, Integer expireTime)
            throws Exception {
        if (StringUtils.isBlank(deviceId) || command == null || command.getServiceId() != null
                || command.getMethod() != null) {
            logger.warn("createDevice 方法参数有误");
            return null;
        }
        String url = authService.iotServerBaseUrl() + ConstantIot.POST_ASYN_CMD;

        Map<String, String> headers = authService.setAuthentication();

        JSONObject parameObj = new JSONObject();
        parameObj.put("deviceId", deviceId);
        parameObj.put("command", command);
        if (StringUtils.isNotBlank(callbackUrl)) {
            parameObj.put("callbackUrl", callbackUrl);
        }
        if (null != expireTime) {
            parameObj.put("expireTime", expireTime);
        }

        HttpResult resp = HttpClientUtil.executeHttpParams(url, "POST", null, headers, parameObj.toJSONString(), null);

        if (resp == null || resp.getStatusCode() != 201 || resp.getContent() == null) {
            logger.error("createDevice error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    /**
     * @param pageNo    可选 Integer(>=0)    query   查询的页码，大于等于0，默认0
     * @param pageSize  可选   Integer(>=1&&<=1000)    query   查询每页的数量，大于等于1，最大值1000，默认1000
     * @param deviceId  可选   String(64)  query   指定查询设备命令的设备ID
     * @param startTime 可选      query   查询开始时间，查询下发命令时间在startTime之后的记录。时间格式：yyyyMMdd'T'HHmmss'Z' 如： 20151212T121212Z
     * @param endTime   可选    String  query   查询结束时间，查询下发命令时间在endTime之前的记录。时间格式：yyyyMMdd'T'HHmmss'Z' 如： 20151212T121212Z
     * @return
     * @throws Exception
     * @Description (应用查询设备命令 。)
     */
    @Override
    public JSONObject findDevice_V4_Commands(Integer pageNo, Integer pageSize, String deviceId, String startTime,
                                             String endTime) throws Exception {
        String url = authService.iotServerBaseUrl() + ConstantIot.POST_ASYN_CMD;

        Map<String, String> headers = authService.setAuthentication();

        JSONObject paramsObj = new JSONObject();
        if (pageNo != null) {
            paramsObj.put("pageNo", String.valueOf(pageNo));
        }
        if (pageSize != null) {
            paramsObj.put("pageSize", String.valueOf(pageSize));
        }
        if (StringUtils.isNotBlank(deviceId)) {
            paramsObj.put("deviceId", deviceId);
        }
        if (StringUtils.isNotBlank(startTime)) {
            paramsObj.put("startTime", startTime);
        }
        if (StringUtils.isNotBlank(endTime)) {
            paramsObj.put("endTime", endTime);
        }

        HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, headers, paramsObj, null);
        if (resp == null || resp.getStatusCode() != 200 || resp.getContent() == null) {
            logger.error("findDevice_V4_Commands error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    /**
     * 应用修改命令信息，当前仅支持修改设备命令状态为撤销，即为将设备命令撤销
     *
     * @param deviceCommandId 必选    String  待修改的设备命令ID
     * @param status          必选 命令执行结果，可选值：CANCELED
     * @return
     * @throws Exception
     * @Description (1.2.4.3 修改设备命令V4)
     */
    @Override
    public JSONObject modifyDevice_V4_Commands(String deviceCommandId, String status) throws Exception {
        if (StringUtils.isBlank(deviceCommandId) || StringUtils.isBlank(status)) {
            logger.warn("modifyDevice_V4_Commands 方法参数有误");
            return null;
        }
        String url = authService.iotServerBaseUrl() + ConstantIot.POST_ASYN_CMD;
        url = url + ("/{deviceCommandId}".replace("{deviceCommandId}", deviceCommandId));

        Map<String, String> headers = authService.setAuthentication();

        Map<String, String> paramsObj = new HashMap<String, String>();
        paramsObj.put("status", status);

        HttpResult resp = HttpClientUtil.executeHttpParams(url, "PUT", null, headers,
                JSONObject.toJSONString(paramsObj), null);
        if (resp == null || resp.getStatusCode() != 200 || resp.getContent() == null) {
            logger.error("modifyDevice_V4_Commands error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    /**
     * 应用查询设备命令撤销任务
     *
     * @param pageNo    可选 Integer(>=0)    query   查询的页码，大于等于0，默认0
     * @param pageSize  可选   Integer(>=1&&<=1000)    query   查询每页的数量，大于等于1，最大值1000，默认1000
     * @param taskId    可选 String  query   指定任务ID查询设备命令撤销任务
     * @param deviceId  可选   String  query   指定设备ID查询设备命令撤销任务
     * @param status    可选 String  query   查询指定任务状态的设备命令撤销任务
     * @param startTime startTime   可选  String  query   查询开始时间，查询创建撤销设备命令任务时间在startTime之后的记录。时间格式：yyyyMMdd'T'HHmmss'Z' 如： 20151212T121212Z
     * @param endTime   可选    String  query   查询结束时间，查询创建撤销设备命令任务时间在endTime之前的记录。时间格式：yyyyMMdd'T'HHmmss'Z' 如： 20151212T121212Z
     * @return
     * @throws Exception
     * @Description (查询设备命令撤销任务V)
     */
    @Override
    public JSONObject createCancelAndRevoke_V4(Integer pageNo, Integer pageSize, String taskId, String deviceId,
                                               String status, String startTime, String endTime) throws Exception {
        String url = authService.iotServerBaseUrl() + ConstantIot.CREATE_DEVICECMD_CANCEL_TASK;

        JSONObject paramsObj = new JSONObject();
        if (pageNo != null) {
            paramsObj.put("pageNo", pageNo);
        }
        if (pageSize != null) {
            paramsObj.put("pageSize", pageSize);
        }
        if (StringUtils.isNotBlank(deviceId)) {
            paramsObj.put("deviceId", deviceId);
        }
        if (StringUtils.isNotBlank(status)) {
            paramsObj.put("status", status);
        }
        if (StringUtils.isNotBlank(startTime)) {
            paramsObj.put("startTime", startTime);
        }
        if (StringUtils.isNotBlank(endTime)) {
            paramsObj.put("endTime", endTime);
        }

        HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, authService.setAuthentication(),
                paramsObj.toJSONString(), null);
        if (resp == null || resp.getStatusCode() != 200 || resp.getContent() == null) {
            logger.error("createCancelAndRevoke_V4 error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    /**
     * Application发送命令给网关（或者网关下的设备）指定的服务下发命令/事件
     *
     * @param deviceId  必选   String  path    设备唯一标识，1-64个字节
     * @param serviceId 必选  String  path    服务id，1-64个字节 唯一标识一个服务，设备的服务类型不冲突，服务类型就是服务id。
     * @param header    [mode,method必选] 必选
     * @param body      必选
     * @return
     * @throws Exception
     * @Description (1.2.5.1 设备服务调用)
     */
    @Override
    public JSONObject callDevice(String deviceId, String serviceId, CommandNA2CloudHeader header, JSONObject body)
            throws Exception {
        if (StringUtils.isBlank(serviceId) || StringUtils.isBlank(serviceId) || header == null || body == null
                || header.getMode() == null || header.getMethod() == null) {
            logger.warn("callDevice 方法参数有误");
            return null;
        }

        String url = authService.iotServerBaseUrl() + ConstantIot.DISCOVER_INDIRECT_DEVICE;
        url = url.replace("{deviceId}", deviceId).replace("{serviceId}", serviceId);
        JSONObject paramsObj = new JSONObject();
        paramsObj.put("deviceId", deviceId);
        paramsObj.put("serviceId", serviceId);
        paramsObj.put("header", header);
        paramsObj.put("body", body);

        HttpResult resp = HttpClientUtil.executeHttpParams(url, "POST", null, authService.setAuthentication(),
                paramsObj.toJSONString(), null);
        if (resp == null || resp.getStatusCode() != (200 | 202) || resp.getContent() == null) {
            logger.error("callDevice error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    /**
     * @param timeout  必选    Integer body    任务超时时间
     * @param taskName 必选   String  body    任务名称
     * @param taskType 必选   String  body    任务类型DeviceCmd
     * @param param    必选  ObjectNode  body
     * @return
     * @throws Exception
     * @Description (1.2.6.1 应用创建批量任务)
     */
    @Override
    public JSONObject createTasks(String timeout, String taskName, String taskType, JSONObject param) throws Exception {
        if (StringUtils.isBlank(timeout) || StringUtils.isBlank(taskName) || StringUtils.isBlank(taskType)
                || param == null) {
            logger.warn("callDevice 方法参数有误");
            return null;
        }
        JSONObject paramsObj = new JSONObject();
        paramsObj.put("appId", authService.iotServerBaseUrl() + this.appid);
        paramsObj.put("timeout", timeout);
        paramsObj.put("taskName", taskName);
        paramsObj.put("taskType", taskType);
        paramsObj.put("param", param);

        String url = authService.iotServerBaseUrl() + ConstantIot.CREATE_TASK_ALL;
        HttpResult resp = HttpClientUtil.executeHttpParams(url, "POST", null, authService.setAuthentication(),
                paramsObj.toJSONString(), null);
        if (resp == null || resp.getStatusCode() != 200 || resp.getContent() == null) {
            logger.error("createTasks error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());

    }

    /**
     * @param taskId
     * @return
     * @throws Exception
     * @Description (创建的批量任务信息)
     */
    @Override
    public JSONObject findTask(String taskId) throws Exception {
        if (StringUtils.isBlank(taskId)) {
            logger.warn("findTask 方法参数有误");
            return null;
        }
        String url = authService.iotServerBaseUrl() + ConstantIot.CREATE_TASK_ALL;
        url = url + ("/{taskId}".replace("{taskId}", taskId));

        HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, authService.setAuthentication(), null,
                null);
        if (resp == null || resp.getStatusCode() != 200 || resp.getContent() == null) {
            logger.error("findTask error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    /**
     * @param taskId    必选 String  query   任务Id
     * @param status    可选 String  query   任务详情状态 Pending/WaitResult/Success/Fail/Timeout
     * @param pageNo    可选 Integer query   分页查询参数，pageNo=null时查询内容不分页；取值大于等于0的整数时分页查询,等于0时查询第一页
     * @param pageSize  可选   Integer query   分页查询参数，取值大于等于1的整数,缺省：1
     * @param index     可选  Integer query   文件里第几行,查询批量注册设备使用
     * @param nodeId    可选 String  query   设备nodeId，查询批量注册设备使用
     * @param deviceId  可选   String  query   设备Id，查询批量命令使用
     * @param commandId 可选  String  query   命令Id，查询批量命令使用
     * @return
     * @throws Exception
     * @Description (查询任务详情)
     */
    @Override
    public JSONObject findTaskInfo(String taskId, String status, Integer pageNo, Integer pageSize, String index,
                                   String nodeId, String deviceId, String commandId) throws Exception {
        if (StringUtils.isBlank(taskId)) {
            logger.warn("findTaskInfo 方法参数有误");
            return null;
        }

        String url = authService.iotServerBaseUrl() + ConstantIot.QUERY_TASK_INFO;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("taskId", taskId);
        if (StringUtils.isNotBlank(status)) {
            jsonObject.put("status", status);
        }
        if (pageNo != null) {
            jsonObject.put("pageNo", pageNo);
        }
        if (pageNo != null) {
            jsonObject.put("pageSize", pageSize);
        }
        if (StringUtils.isNotBlank(index)) {
            jsonObject.put("index", index);
        }
        if (StringUtils.isNotBlank(nodeId)) {
            jsonObject.put("nodeId", nodeId);
        }
        if (StringUtils.isNotBlank(deviceId)) {
            jsonObject.put("deviceId", deviceId);
        }
        if (StringUtils.isNotBlank(commandId)) {
            jsonObject.put("commandId", commandId);
        }

        HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, authService.setAuthentication(), null,
                null);
        if (resp == null || resp.getStatusCode() != 200 || resp.getContent() == null) {
            logger.error("findTaskInfo error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    /**
     * @param ruleDTO1_2:不能为空
     * @return
     * @throws Exception
     * @Description (创建规则)
     */
    @Override
    public JSONObject createRule(RuleDTO1_2 ruleDTO1_2) throws Exception {
        if (ruleDTO1_2 == null) {
            logger.warn("createRule 方法参数有误");
            return null;
        }

        String url = authService.iotServerBaseUrl() + ConstantIot.RULE;

        HttpResult resp = HttpClientUtil.executeHttpParams(url, "POST", null, authService.setAuthentication(),
                JSONObject.toJSONString(ruleDTO1_2), null);
        if (resp == null || resp.getStatusCode() != 200 || resp.getContent() == null) {
            logger.error("getAccessToken error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    /**
     * @param ruleDTO1_2
     * @return
     * @throws Exception
     * @Description (更新规则)
     */
    @Override
    public JSONObject toUpdateRule(RuleDTO1_2 ruleDTO1_2) throws Exception {
        if (ruleDTO1_2 == null) {
            logger.warn("toUpdateRule 方法参数有误");
            return null;
        }
        String url = authService.iotServerBaseUrl() + ConstantIot.RULE;

        HttpResult resp = HttpClientUtil.executeHttpParams(url, "PUT", null, authService.setAuthentication(),
                JSONObject.toJSONString(ruleDTO1_2), null);
        if (resp == null || resp.getStatusCode() != 200 || resp.getContent() == null) {
            logger.error("toUpdateRule error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    /**
     * @param ruleId 必选 String  path    规则的ID
     * @param status 必选 String  path    规则的状态，“active”代表激活状态，“inactive”代表未激活
     * @return
     * @throws Exception
     * @Description (修改规则状态)
     */
    @Override
    public JSONObject modifyRule(String ruleId, String status) throws Exception {
        if (StringUtils.isBlank(ruleId) || StringUtils.isBlank(status)) {
            logger.warn("modifyRule 方法参数有误");
            return null;
        }
        String url = authService.iotServerBaseUrl() + ConstantIot.RULE;
        url = url + ("/{ruleId}/status/{status}".replace("{ruleId}", ruleId).replace("{status}", status));
        HttpResult resp = HttpClientUtil.executeHttpParams(url, "PUT", null, authService.setAuthentication(), null,
                null);
        if (resp == null || resp.getStatusCode() != 200 || resp.getContent() == null) {
            logger.error("modifyRule error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    /**
     * @param ruleId 必选    String  path    规则实例的id
     * @return
     * @throws Exception
     * @Description (删除规则)
     */
    @Override
    public boolean deleteRule(String ruleId) throws Exception {
        if (StringUtils.isBlank(ruleId)) {
            logger.warn("deleteRule 方法参数有误");
            return false;
        }
        String url = authService.iotServerBaseUrl() + ConstantIot.RULE;
        url = url + ("/{ruleId}".replace("{ruleId}", ruleId));
        HttpResult resp = HttpClientUtil.executeHttpParams(url, "DELETE", null, authService.setAuthentication(), null,
                null);
        if (resp == null || resp.getStatusCode() != 204) {
            logger.error("deleteRule error ,result statusCode:" + resp.getStatusCode());
            return true;
        }
        return false;
    }

    /**
     * @param author author    必选  String  query   用户id
     * @param name   可选  String  query   规则名称
     * @return
     * @throws Exception
     * @Description (查找规则)
     */
    @Override
    public JSONObject findRule(String author, String name) throws Exception {
        if (StringUtils.isBlank(author)) {
            logger.warn("findRule 方法参数有误");
            return null;
        }
        String url = authService.iotServerBaseUrl() + ConstantIot.RULE;
        url = url + ("?author={author}".replace("{author}", author));
        if (StringUtils.isBlank(name)) {
            url = url + ("&name={name}".replace("{name}", name));
        }
        HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, authService.setAuthentication(), null,
                null);
        if (resp == null || resp.getStatusCode() != 200 || resp.getContent() == null) {
            logger.error("findRule error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    /**
     * @param ruleStatusUpdateReqDTO{requests:[{ruleId:'必选 规则Id',status:'必选 状态，取值可为active和inactive'},{}...]}
     * @return
     * @throws Exception
     * @Description (批量修改规则状态)
     */
    @Override
    public JSONObject modefyRules(List<JSONObject> ruleStatusUpdateReqDTO) throws Exception {
        if (ruleStatusUpdateReqDTO == null || ruleStatusUpdateReqDTO.size() == 0) {
            logger.warn("modefyRules 方法参数有误");
            return null;
        }
        String url = authService.iotServerBaseUrl() + ConstantIot.RULE;

        JSONObject paramsObj = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        paramsObj.put("requests", jsonArray);
        for (JSONObject obj : ruleStatusUpdateReqDTO) {
            String ruleId = obj.getString("ruleId");
            String status = obj.getString("status");
            if (StringUtils.isNotBlank(ruleId) && StringUtils.isNotBlank(status)) {
                jsonArray.add(obj);
            } else {
                logger.warn("modefyRules 方法参数有误");
                return null;
            }
        }

        HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, authService.setAuthentication(), paramsObj,
                null);
        if (resp == null || resp.getStatusCode() != 200 || resp.getContent() == null) {
            logger.error("modefyRules error ,result statusCode:" + resp.getStatusCode());
            return null;
        }
        return JSONObject.parseObject(resp.getContent());
    }

    @Override
    public boolean deleteSubs() throws Exception {
        String url = authService.iotServerBaseUrl() + ConstantIot.SUBSCRIBE_DELETE_ALL;
        // url += "?appId={appId}&notifyType={notifyType}&callbackUrl={callbackUrl}";
        HttpResult resp = HttpClientUtil.executeHttpParams(url, "DELETE", null, authService.setAuthentication(), null,
                null);
        if (resp != null && 204 == resp.getStatusCode()) {
            return true;
        }
        return false;
    }

    @Override
    public String findSubs() throws Exception{
        String url = authService.iotServerBaseUrl() + ConstantIot.SUBSCRIBE_DELETE_ALL;
        // url += "?appId={appId}&notifyType={notifyType}&callbackUrl={callbackUrl}";
        HttpResult resp = HttpClientUtil.executeHttpParams(url, "GET", null, authService.setAuthentication(), null,
                null);
        if (resp != null && 200 == resp.getStatusCode()) {
            return resp.getContent();
        }
        logger.info(resp);
        return null;
    }

}
