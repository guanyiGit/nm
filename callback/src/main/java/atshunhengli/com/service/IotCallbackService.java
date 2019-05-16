package atshunhengli.com.service;


import atshunhengli.com.entity.cdoec.CallBackData;
import com.alibaba.fastjson.JSONObject;
import com.entities.DeviceInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IotCallbackService {

    /**
     * @param json
     * @throws Exception
     * @Description (设备注册通知)
     */
    boolean deviceAddedMeg(JSONObject json) throws Exception;

    /**
     * @param req
     * @Description (设备信息编发通知)
     */
    void deviceInfoChangedMsg(JSONObject req);

    /**
     * @param json
     * @Description (设备删除)
     */
    void deviceDeletedMsg(JSONObject json);

    /**
     * @param json
     * @Description (命令下发成功通知 - - 至网关)
     */
    void messageConfirmMsg(JSONObject json);

    /**
     * @param json
     * @Description (设备响应命令)
     */
    void commandRspMsg(JSONObject json);

    /**
     * @param json
     * @Description (设备事件通知)
     */
    void deviceEventMsg(JSONObject json);

    /**
     * @param json
     * @Description (绑定设备通知)
     */
    void bindDevicetMsg(JSONObject json);

    /**
     * @param json
     * @return
     * @throws Exception
     * @Description (设备批量数据上报 - - 解析)
     */
    boolean deviceDatasChangedMsg(JSONObject json);

    /**
     * @param req
     * @Description (涉笔数据变化)
     */
    void deviceDataChangedMeg(JSONObject req);


    boolean iotDataHandle(DeviceInfo tDevice, List<CallBackData> datas) throws Exception;

    List<DeviceInfo> testLowerPower();

    Map<String, Object> getMap();

    void setMap(HashMap<String, Object> maps);

}
