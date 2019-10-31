package atshunhengli.com.service;

import atshunhengli.com.entity.app.RuleDTO1_2;
import atshunhengli.com.entity.deviceManager.CommandDTOV4;
import atshunhengli.com.entity.deviceManager.CommandNA2CloudHeader;
import atshunhengli.com.entity.iot.CallbackType;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * 
 * @ClassName AppService
 * @Description TODO(Application订阅设备变更通知，当设备发生变更时平台会推送给Application。)
 * @author guanY
 * @Date 2018年4月10日 下午4:27:58
 * @version 1.0.0
 */
public interface AppService {

    /**
     * 
     * @Description (Application订阅设备变更通知，当设备发生变更时平台会推送给Application)
     * @param notifyType:必选 String  通知类型
     * @param callbackurl:必选    String  回调的url地址。必须使用HTTPS信道回调地址，同时回调地址中必须指定回调地址的端口。(说明：HTTP信道只可用于调测)
     * @return
     * @throws Exception
     */
    boolean subscribe(CallbackType notifyType, String callbackurl) throws Exception;

    /**
     * @Description (1.2.3.4 Application查询设备历史数据)
     * @param deviceId:必选   String  设备唯一标识，1-64个字节
     * @param gatewayId 必选  String  查询参数，网关的设备唯一标识。当设备是直连设备时，gatewayId为设备本身的deviceId。当设备是非直连设备时，gatewayId为设备所关联的直连设备（即网关）的deviceId。
     * @param serviceId 可选  String  服务Id
     * @param property 可选   String  服务属性数据
     * @param pageNo 可选 String  应用唯一标识
     * @param pageSize 可选   Integer 分页查询参数， pageNo=null时查询内容不分页；取值大于等于0的整数时分页查询，等于0时查询第一页
     * @param startTime 可选  Integer 分页查询参数，取值大于等于1的整数，缺省值为1 设备历史数据最多保存10万条，这个数值有可能被运营商修改
     * @param endTime 可选    String  查询参数，根据时间段查询的起始时间; 时间格式：yyyyMMdd’T’HHmmss’Z’ 如： 20151212T121212Z
                                                                    设备历史数据最多保存90天，这个数值有可能被运营商修改
     * @return
     * @throws Exception
     */
    JSONObject findDeviceHistory(String deviceId, String gatewayId, String serviceId, String property, int pageNo,
                                 int pageSize, String startTime, String endTime) throws Exception;

    /**
     * @Description (查询设备能力)
     * @param deviceId 必选   String  设备唯一标识，1-64个字节
     * @param gatewayId 可选  String  设备的网关id。当设备是直连设备时，gatewayId务必与deviceId保持一致。当设备是非直连设备时，gatewayId为设备所关联的直连设备（即网关）的deviceId。
     * @return
     * @throws Exception
     */
    JSONObject findDeviceCapabilities(String deviceId, String gatewayId) throws Exception;

    /**
     * @Description (应用创建设备命令。)
     * @param deviceId 必选   String(64)  下发命令的设备ID
     * @param command 必选    CommandDTOV4    下发命令的信息
     * @param callbackUrl 可选    String(1024)    命令状态变化通知地址，必须使用HTTPS信道回调地址 (说明：HTTP信道只可用于调测，不可用于商用环境)；
                                                                    当命令状态变化时(执行失败，执行成功，超时，发送，已送达)会通知NA，平台会以POST方式发送HTTP消息给应用，请求Body为json字符串，格式形如：{"deviceId":"deviceId","commandId":"commandId","result":{"status":"SUCCESS","result":{…}}}
     * @param expireTime:可选 Integer(>=0)    下发命令有效的超期时间，单位为秒，表示设备命令在创建后expireTime秒内有效，超过这个时间范围后命令将不再下发，如果未设置则默认为48小时
     * @return
     * @throws Exception
     */
    JSONObject createDeviceCommand(String deviceId, CommandDTOV4 command, String callbackUrl, Integer expireTime)
            throws Exception;

    /**
     * @Description (应用查询设备命令。)
     * @param pageNo 可选 Integer(>=0)    query   查询的页码，大于等于0，默认0
     * @param pageSize 可选   Integer(>=1&&<=1000)    query   查询每页的数量，大于等于1，最大值1000，默认1000
     * @param deviceId 可选   String(64)  query   指定查询设备命令的设备ID
     * @param startTime 可选      query   查询开始时间，查询下发命令时间在startTime之后的记录。时间格式：yyyyMMdd'T'HHmmss'Z' 如： 20151212T121212Z
     * @param endTime 可选    String  query   查询结束时间，查询下发命令时间在endTime之前的记录。时间格式：yyyyMMdd'T'HHmmss'Z' 如： 20151212T121212Z
     * @return
     * @throws Exception
     */
    JSONObject findDevice_V4_Commands(Integer pageNo, Integer pageSize, String deviceId, String startTime,
                                      String endTime) throws Exception;

    /**
     * 应用修改命令信息，当前仅支持修改设备命令状态为撤销，即为将设备命令撤销
     * @Description (1.2.4.3 修改设备命令V4)
     * @param deviceCommandId 必选    String  待修改的设备命令ID
     * @param status 必选 命令执行结果，可选值：CANCELED
     * @return
     * @throws Exception
     */
    JSONObject modifyDevice_V4_Commands(String deviceCommandId, String status) throws Exception;

    /**
     * 应用查询设备命令撤销任务
     * @Description (查询设备命令撤销任务V)
     * @param pageNo 可选 Integer(>=0)    query   查询的页码，大于等于0，默认0
     * @param pageSize 可选   Integer(>=1&&<=1000)    query   查询每页的数量，大于等于1，最大值1000，默认1000
     * @param taskId 可选 String  query   指定任务ID查询设备命令撤销任务
     * @param deviceId 可选   String  query   指定设备ID查询设备命令撤销任务
     * @param status 可选 String  query   查询指定任务状态的设备命令撤销任务
     * @param startTime startTime   可选  String  query   查询开始时间，查询创建撤销设备命令任务时间在startTime之后的记录。时间格式：yyyyMMdd'T'HHmmss'Z' 如： 20151212T121212Z
     * @param endTime 可选    String  query   查询结束时间，查询创建撤销设备命令任务时间在endTime之前的记录。时间格式：yyyyMMdd'T'HHmmss'Z' 如： 20151212T121212Z
     * @return
     * @throws Exception
     */
    JSONObject createCancelAndRevoke_V4(Integer pageNo, Integer pageSize, String taskId, String deviceId, String status,
                                        String startTime, String endTime) throws Exception;

    /**
     * Application发送命令给网关（或者网关下的设备）指定的服务下发命令/事件
     * @Description (1.2.5.1 设备服务调用)
     * @param deviceId 必选   String  path    设备唯一标识，1-64个字节
     * @param serviceId 必选  String  path    服务id，1-64个字节 唯一标识一个服务，设备的服务类型不冲突，服务类型就是服务id。
     * @param header [mode,method必选] 必选
     * @param body 必选
     * @return
     * @throws Exception
     */
    JSONObject callDevice(String deviceId, String serviceId, CommandNA2CloudHeader header, JSONObject body)
            throws Exception;

    /**
     *
     * @Description (1.2.6.1 应用创建批量任务)
     * @param timeout 必选    Integer body    任务超时时间
     * @param taskName 必选   String  body    任务名称
     * @param taskType 必选   String  body    任务类型DeviceCmd
     * @param param 必选  ObjectNode  body
     * @return
     * @throws Exception
     */
    JSONObject createTasks(String timeout, String taskName, String taskType, JSONObject param) throws Exception;

    /**
     * @Description (创建的批量任务信息)
     * @param taskId
     * @return
     * @throws Exception
     */
    JSONObject findTask(String taskId) throws Exception;

    /**
     *
     * @Description (查询任务)
     * @param taskId 必选 String  query   任务Id
     * @param status 可选 String  query   任务详情状态 Pending/WaitResult/Success/Fail/Timeout
     * @param pageNo 可选 Integer query   分页查询参数，pageNo=null时查询内容不分页；取值大于等于0的整数时分页查询,等于0时查询第一页
     * @param pageSize 可选   Integer query   分页查询参数，取值大于等于1的整数,缺省：1
     * @param index 可选  Integer query   文件里第几行,查询批量注册设备使用
     * @param nodeId 可选 String  query   设备nodeId，查询批量注册设备使用
     * @param deviceId 可选   String  query   设备Id，查询批量命令使用
     * @param commandId 可选  String  query   命令Id，查询批量命令使用
     * @return
     * @throws Exception
     */
    JSONObject findTaskInfo(String taskId, String status, Integer pageNo, Integer pageSize, String index, String nodeId,
                            String deviceId, String commandId) throws Exception;

    /**
     * 
     * @Description (创建规则)
     * @param ruleDTO1_2:不能为空
     * @return
     * @throws Exception
     */
    JSONObject createRule(RuleDTO1_2 ruleDTO1_2) throws Exception;

    /**
     * 
     * @Description (更新规则)
     * @param ruleDTO1_2
     * @return
     * @throws Exception
     */
    JSONObject toUpdateRule(RuleDTO1_2 ruleDTO1_2) throws Exception;

    /**
     * 
     * @Description (修改规则状态)
     * @param ruleId 必选 String  path    规则的ID
     * @param status 必选 String  path    规则的状态，“active”代表激活状态，“inactive”代表未激活
     * @return
     * @throws Exception
     */
    JSONObject modifyRule(String ruleId, String status) throws Exception;

    /**
     * 
     * @Description (删除规则)
     * @param ruleId  必选    String  path    规则实例的id
     * @return
     * @throws Exception
     */
    boolean deleteRule(String ruleId) throws Exception;

    /**
     * @Description (查找规则)
     * @param author  author    必选  String  query   用户id
     * @param name  可选  String  query   规则名称
     * @return
     * @throws Exception
     */
    JSONObject findRule(String author, String name) throws Exception;

    /**
     * 
     * @Description (批量修改规则状态)
     * @param ruleStatusUpdateReqDTO{requests:[{ruleId:'必选 规则Id',status:'必选 状态，取值可为active和inactive'},{}...]}
     * @return
     * @throws Exception
     */
    JSONObject modefyRules(List<JSONObject> ruleStatusUpdateReqDTO) throws Exception;

    /**
     * 
     * @Description (批量删除订阅)
     */
    public boolean deleteSubs() throws Exception;

    /**
     * 查询订阅
     * @return
     */
    String findSubs() throws Exception;
}
