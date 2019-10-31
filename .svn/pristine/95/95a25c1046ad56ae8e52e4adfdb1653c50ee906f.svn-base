package atshunhengli.com.service;


import atshunhengli.com.entity.iot.CmdType;
import com.alibaba.fastjson.JSONObject;

public interface DeviceCommandIotService {

    /**
     * 
     * @Description (查询设备命令)
     * @param deviceIdIot 设备id iot  为空查询所有设备
     * @return
     */
    String findCommandsByDeviceIotId(String deviceIdIot) throws Exception;

    /**
     * 
     * @Description (给设备发送命令)
     * @param cmdType 命令类型
     * @param cmdValue 命令值
     * @param mid
     * @param deviceIotId 设备编号
     * @return iot_command_id
     *          resultObj.put("commandId", object.getString("commandId"));
                resultObj.put("output", input);
     * @throws Exception
     */
    JSONObject sendCommand(CmdType cmdType, Integer cmdValue, Integer mid, String deviceIotId) throws Exception;

}
