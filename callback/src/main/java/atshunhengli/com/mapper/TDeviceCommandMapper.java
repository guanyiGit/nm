package atshunhengli.com.mapper;

import com.entities.TDeviceCommand;
import com.entities.TDeviceCommandExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface TDeviceCommandMapper {

    int countByExample(TDeviceCommandExample example);

    int deleteByExample(TDeviceCommandExample example);

    int deleteByPrimaryKey(Long deviceCommandId);

    int insert(TDeviceCommand record);

    int insertSelective(TDeviceCommand record);

    List<TDeviceCommand> selectByExample(TDeviceCommandExample example);

    TDeviceCommand selectByPrimaryKey(Long deviceCommandId);

    int updateByExampleSelective(@Param("record") TDeviceCommand record, @Param("example") TDeviceCommandExample example);

    int updateByExample(@Param("record") TDeviceCommand record, @Param("example") TDeviceCommandExample example);

    int updateByPrimaryKeySelective(TDeviceCommand record);

    int updateByPrimaryKey(TDeviceCommand record);

    /**
     * @param mid
     * @param resultCode
     * @param imei
     * @param rspTime
     * @return
     * @Description (根据设备imei更新设备命令结果信息 [0失败 1成功])
     */
    int modifyDeviceCmdByMidAndDeviceImei(@Param("mid") Integer mid, @Param("resultCode") Integer resultCode,
                                          @Param("imei") String imei, @Param("rspTime") Date rspTime);

    /**
     * @param deviceNum
     * @param status
     * @return
     * @Description (根据设备编码查询设备命令列表)
     */
    List<TDeviceCommand> findCommandByDeviceNum(@Param("deviceNum") String deviceNum, @Param("status") int status);


    /**
     * @param deviceIotId
     * @param status
     * @return
     * @Description (根据设备iot平台编码查询设备命令列表)
     */
    List<TDeviceCommand> findCommandByDeviceIotId(@Param("deviceIotId") String deviceIotId, @Param("status") int status);

    /**
     * @param tDeviceCommand
     * @return
     * @Description (插入命令记录)
     */
    int saveCommand(TDeviceCommand tDeviceCommand);


    /**
     * 修改命令状态
     *
     * @param command
     * @param deviceNo
     * @param cmdStatus
     * @return
     */
    int updateStatusByNo(@Param("command") TDeviceCommand command, @Param("deviceNo") String deviceNo, @Param("cmdStatus") Object cmdStatus);


    /**
     * 根据命令id修改命令状态
     *
     * @param tdevCmd
     */
    @Update("UPDATE t_device_command d SET d.cmd_issued_time = #{_parameter.cmdIssuedTime}, d.cmd_status = #{_parameter.cmdStatus}, d.iot_command_id =  #{_parameter.iotCommandId} WHERE d.device_command_id = #{_parameter.deviceCommandId}")
    void updateStatusById(@Param("_parameter") TDeviceCommand tdevCmd);
}
