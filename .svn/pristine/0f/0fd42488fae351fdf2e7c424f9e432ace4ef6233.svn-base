package com.soholy.service.command;


import com.alibaba.fastjson.JSONObject;
import com.soholy.pojo.aep.em.CmdType_lwM2M;

public interface DeviceCommandIotService {


    /**
     * mode:指令类型,非空，int32
     * 0:开机回复
     * 1:模式切换
     * 2:设备当前模式的上传间隔调整
     * mVal:指令值,非空,int32
     * 当type = 0时，模式切换指令，val可选值为：
     * 1:成功激活
     * 2:未激活
     * 当type = 1时，模式切换指令，val可选值为：
     * 1:普通模式
     * 2:省电模式
     * 当type = 2时，间隔设置指令，val为普通数值，单位秒
     *
     * @param cmdType     命令类型
     * @param cmdValue    命令值
     * @param mid
     * @param imei
     * @return returnJson.put(" commandId ", resultCmd.getTaskId ());
     * returnJson.put("result", resultCmd.getCommand());
     * @throws Exception
     * @Description (给设备发送命令)
     */
    JSONObject sendCommand_v2_lwM2M(CmdType_lwM2M cmdType, String cmdValue, Long mid, String imei);

    /**
     * 检查命令并下发
     *
     * @param iemi
     * @param deviceIdIot
     */
    boolean depositoryCommandCheckAndSend(String iemi, String deviceIdIot);

    /**
     * 生成命令的 mid
     *
     * @return
     */
    long generateMid();
}
