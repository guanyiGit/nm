package atshunhengli.com.service;

import atshunhengli.com.entity.deviceManager.CommandDTONA2Cloud;
import atshunhengli.com.entity.deviceManager.UpdateDeviceInfoReqDTO;
import com.alibaba.fastjson.JSONObject;
import com.entities.DeviceInfo;

import java.util.List;

/**
 * 设备管理,增，删，改，查以及修改接口。 申请新设备分配对应的设备验证码，待设备携带验证码请求接入平台后，需要deviceId和 secret。
 *
 * @author guanY
 * @version 1.0.0
 * @ClassName DeviceService
 * @Description TODO(设备管理)
 * @Date 2018年4月10日 上午10:54:55
 */
public interface DeviceManagerService {

    /**
     * 申请添加设备，获取设备的验证码，并在设备访问南向接口时携带验证码，获取设备唯一标识和密码)
     *
     * @param nodeId    必选 String  设备唯一标识, 如：MAC或SIM卡号或设备esn号等。与设备对接时需携带。
     *                  次id作为连接验证码用
     *                  备注：nodeId和verifyCode需要填写相同的值，如果南向连接SoftRadio的模拟环境，该值可自行定义，格式为TESTS_XXXXXX。如果是现网环境，该值通常为设备的IMEI号。
     *                  jiang
     * @param endUserId 可选  String  终端用户Id，如手机号码，email地址
     * @param psk       可选    String  psk码，用于生成设备鉴权参数；如不传入，系统自动生成
     * @param timeout   可选    Integer 单位秒，不填使用默认值(180s), 填写0则永不过期，非0表示在指定时间内设备进行绑定，超过时间验证码无效
     * @return "deviceId": "*******",
     * "verifyCode": "*******",
     * "psk": "*********",
     * "timeout": 300
     * 401    100002  Invalid access token.   错误的token信息
     * 400 100022  The input is invalid.   输入参数无效
     * 401 100025  AppId for auth not exist.   获取不到appId鉴权信息
     * 200 100203  The application is not existed.     应用不存在
     * 200 100217  The application hasn't been authorized.     应用未被授权
     * 400 100007  Bad request message.    参数不合法
     * 200 100412  The amount of device has reached the limit. 当前应用下设备数量达到上限
     * 400 100003  Invalid verify code.    验证码无效
     * 200 100416  The device has already been binded.     设备已经绑定
     * 500 100001  Internal server error.  服务内部处理错误
     * 200 103026  The license is not exist.   License不存在
     * 200 103028  The license pool resources.     License无资源
     * 200 103027  The license sales is not exist.     License的销售项不存在
     * @throws Exception
     * @Description (注册直连设备)
     */
    public JSONObject register(String nodeId, String endUserId, String psk, Integer timeout) throws Exception;

    /**
     * @param deviceList
     * @return 注册后的设备信息
     * @Description (批量注册设备)
     */
    public List<DeviceInfo> registerAll(List<DeviceInfo> deviceList) throws Exception;

    /**
     * Application发送DISCOVERY命令给网关下的设备。一般用于通过网关添加传感器，若没有网关，该接口请勿使用
     *
     * @param deviceId:必选                                                    String  网关设备唯一标识，1-64个字节
     * @param serviceId:必选                                                   String  取值"Discovery"
     * @param message:{header:{CommandNA2CloudHeader}-->必选,body:{json}}-->必选
     * @return
     * @throws Exception
     * @Description (注册 / 发现非直连设备)
     */
    public JSONObject register_gateway(String deviceId, String serviceId, CommandDTONA2Cloud message) throws Exception;

    /**
     * 1.2.2.3 查询设备激活状态
     *
     * @param deviceId:设备唯一标识，1-64个字节
     * @return 401  100002  Invalid access token.   错误的token信息
     * 400 100022  The input is invalid.   输入参数无效
     * 200 100203  The application is not existed.     应用不存在
     * 200 100217  The application hasn't been authorized.     应用未授权
     * 200 100418  The deviceData is not existed.  设备数据不存在
     * 500 100023  The data in dataBase is abnomal.    数据库中数据异常
     * 400 102203  CommandName is invalid.     命令名称无效
     * 200 100431  The serviceType is not exist.   服务类型不存在
     * 200 100428  The device is not online.   设备不在线
     * 200 100432  The device command is muted.    设备已被muted
     * @throws Exception
     * @Description (给定网关设备的设备唯一标识查询网关设备激活状态 。)
     */
    JSONObject findDeviceStatus(String deviceId) throws Exception;

    /**
     * 1.2.2.4 删除直连设备
     *
     * @param deviceId 必选   String  设备唯一标识，1-64个字节
     * @return
     * @throws Exception
     * @Description (用来删除直连设备)
     */
    boolean deleteDevice(String deviceId) throws Exception;

    /**
     * 1.2.2.5 删除非直连设备
     *
     * @param message:{header:{CommandNA2CloudHeader}-->必选,body:{json}}-->必选
     * @return
     * @throws Exception
     * @Description (Application发送REMOVE命令给网关下的设备 。 用于通过网关删除传感器 ， 若没有网关 ， 该接口请勿使用)
     */
    JSONObject deleteDevice_gateway(CommandDTONA2Cloud message) throws Exception;

    /**
     * @param deviceId:必选            String  设备唯一标识，1-64个字节
     * @param updateDeviceInfoReqDTO 要修改的设备信息
     * @return
     * @throws Exception
     * @Description (修改设备信息)
     */
    boolean modifyDeviceId(String deviceId, UpdateDeviceInfoReqDTO updateDeviceInfoReqDTO) throws Exception;

    /**
     * 1.2.2.7 刷新设备密钥
     *
     * @param deviceId:必选   String(256) path    设备Id
     * @param verifyCode:可选 String(256) body    验证码：null 平台生成验证码，并返回；非null 使用，且返回该验证码。（大小写不敏感）
     * @param nodeId:可选     String(256) body    null:nodeId不变；非null：更新；网关的唯一ID，平台用于判重
     * @param timeout:可选    Integer body    验证码超时时间，单位秒。null：默认值(180s)；0：永不过期；非0：指定时间
     * @return
     * @throws Exception
     * @Description (支持刷新注册到本应用的设备和授予权限的其它应用的设备, 密钥刷新后 ， deviceId不变 ， nodeId值发生变化 ， 设备需要重新绑定 。)
     */
    JSONObject freshDeviceAuth(String deviceId, String verifyCode, String nodeId, Integer timeout) throws Exception;

    /**
     * 1.2.2.8 设置加密
     *
     * @param deviceId:必选    String  path    设备id
     * @param serviceType:必选 String  path    设备服务类型，可选值carEncrypt，carInfo，vehicleProperty
     * @param jsonObject:可选  JSONObject body 服务数据：
     *                       serviceType取值carEncrypt时，data结构体参考1.3.10 ApplicationSetEncrtptDTO结构体说明；
     *                       serviceType取值carInfo、vehicleProperty时，data结构体参考1.3.11 PutCarInfoData结构体说明
     * @return
     * @throws Exception
     * @Description (将设备的加密信息发送给平台)
     */
    JSONObject setEncryption(String deviceId, String serviceType, JSONObject jsonObject) throws Exception;

    /**
     * 查询单个设备信息
     *
     * @param deviceIdIot
     * @return
     */
    public JSONObject findDeviceInfo(String deviceIdIot) throws Exception;


    /**
     * 修改设备信息
     *
     * @param deviceIdIot
     * @param imei
     * @return
     */
    public boolean modifyDeviceInfo(String deviceIdIot, String imei) throws Exception;


    /**
     * @param imei
     * @param deviceBrand 品牌信息 可没有
     * @param deviceBatch 批次信息 可没有
     * @return
     * @throws Exception
     */
    DeviceInfo register(String imei, String deviceBrand, String deviceBatch) throws Exception;


    /**
     * 设备删除
     *
     * @param deviceIdIot
     * @return
     */
    boolean deleteDeviceByIotId(String deviceIdIot) throws Exception;
}
