package atshunhengli.com.mapper;


import com.entities.DeviceInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TDeviceMapper {


    /**
     * 根据设备平台id查设备信息
     * @param deviceIdIot
     * @return
     */
    List<DeviceInfo> findDeviceInfoByDeviceidIot(String deviceIdIot);


    /**
     *
     * @Description (根据设备id查询设备信息)
     * @param deviceIdList
     * @return
     */
    List<DeviceInfo> findDeviceListByIds(@Param("deviceIdList") List<Long> deviceIdList);

    /**
     * 根据平台id删除设备
     * @param deviceIdIot
     * @return
     */
    int removeByDeviceIdIot(String deviceIdIot);

    /**
     * 保存
     * @param deviceInfo
     * @return
     */
    int save(@Param("_parameter") DeviceInfo deviceInfo);

    Map<String,Object> selectInfosByDeviceNo(String deviceNo);

    /**
     * 低电报警测试
     */

    List<DeviceInfo> testLowerPower();
//     <!--根据device_no 查询trace_id-->
    String selectTraceIdByDeviceNo(String deviceNo);

}
