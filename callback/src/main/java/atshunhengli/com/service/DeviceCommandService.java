package atshunhengli.com.service;

import atshunhengli.com.entity.callback.cmd.CmdmandRes;
import atshunhengli.com.entity.iot.CmdType;
import com.entities.TDeviceCommand;

import java.util.Date;

public interface DeviceCommandService {

    /**
     * @param mid
     * @return
     * @Description (根据设备mid查询设备命令)
     */
    public abstract TDeviceCommand findDeviceCmdDataByMid(int mid);

    /**
     * @param mid
     * @param resultCode [0失败 1成功]
     * @param imei
     * @param rspTime    响应时间
     * @return
     * @Description (根据设备imei更新设备命令结果信息)
     */
    public abstract boolean modifyDeviceCmdByMidAndDeviceImei(int mid, int resultCode, String imei, Date rspTime);

    /**
     * @param deviceId   设备no
     * @param cmdType    命令类型
     * @param cmdValue   命令值
     * @param operatorId 用户id
     * @return
     * @throws Exception
     * @Description (保存设备命令)
     */
    boolean saveCommand(Long deviceNo, CmdType cmdType, int cmdValue, long operatorId) throws Exception;

    /**
     * @param cmdmandRes
     * @Description (修改命令状态 - 根据命令iotid)
     */
    public abstract boolean modefyCommandStatusByIotCmdId(CmdmandRes cmdmandRes);
}
