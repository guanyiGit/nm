package atshunhengli.com.controller;

import atshunhengli.com.entity.cdoec.CallBackData;
import atshunhengli.com.service.DeviceManagerService;
import atshunhengli.com.service.IotCallbackService;
import atshunhengli.com.utils.GlobalResult;
import com.entities.DeviceInfo;
import com.entities.DeviceRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/device")
public class IotDeviceContaoller {

    @Autowired
    private DeviceManagerService deviceManagerService;
    @Autowired
    private IotCallbackService iotCallbackService;

    /**
     * http://localhost:9000/device/register/13385285115?deviceBrand=nm_test&deviceBatch=test     * @param imei 设备imei号
     *
     * @imei
     * @param deviceBrand 品牌信息 可没有
     * @param deviceBatch 批次信息 可没有
     * @return
     * @throws Exception
     * @Description (设备注册)
     */
    @RequestMapping(value = "/register/{imei}", method = RequestMethod.GET)
    public String register(@PathVariable(value = "imei") String imei,
                           String deviceBrand,
                           String deviceBatch
    ) throws Exception {

        if (StringUtils.isNotBlank(imei)) {
            return new GlobalResult().successJSON(deviceManagerService.register(imei, deviceBrand, deviceBatch));
        }
        return new GlobalResult().failureJSON();
    }


    /**
     * 设备删除 平台和数据库同事删除
     *
     * @param deviceIdIot
     * @return
     */
    @RequestMapping(value = "/logoutDevice/{deviceIdIot}")
    public String logoutDevice(@PathVariable("deviceIdIot") String deviceIdIot) {
        try {
            return new GlobalResult().successJSON(deviceManagerService.deleteDeviceByIotId(deviceIdIot));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new GlobalResult().failureJSON();
    }
    /**
     * testController
     */




    @ResponseBody
    @GetMapping("/test")
    public void  testController(){
        List<DeviceInfo> deviceInfos = iotCallbackService.testLowerPower();
        deviceInfos.stream().filter(inf ->{return "362531810121158".equals(inf.getDeviceNo());}).forEach(deviceInfo ->{
            List<DeviceRecord> records = deviceInfo.getRecords();
            List<CallBackData> callBackDatas = change(records);
            try {
                iotCallbackService.iotDataHandle(deviceInfo,callBackDatas);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    public static List<CallBackData> change(List<DeviceRecord> list){
        ArrayList<CallBackData> lis = new ArrayList<>();

        list.stream().forEach(record ->{
            CallBackData cbd = new CallBackData();
            cbd.setId(UUID.randomUUID().toString());
            cbd.setLat(record.getLat());
            cbd.setLng(record.getLng());
            cbd.setCreateDate(record.getCreateDate());
            cbd.setDataTime(record.getDataTime());
            cbd.setDataType(record.getDataType());
            cbd.setDeviceNo(record.getDeviceNo());
            cbd.setQuantity(record.getQuantity());
            lis.add(cbd);
        });
        return lis;
    }

}
