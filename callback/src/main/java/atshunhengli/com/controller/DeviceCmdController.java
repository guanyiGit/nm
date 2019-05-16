package atshunhengli.com.controller;

import atshunhengli.com.entity.iot.CmdType;
import atshunhengli.com.service.DeviceCommandService;
import atshunhengli.com.utils.GlobalResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 设备命令相关
 */
@RestController
@RequestMapping(value = "/biz/deviceCmd/")
public class DeviceCmdController {

    @Autowired
    private DeviceCommandService deviceCommandService;

    private static final Logger logger = LoggerFactory.getLogger(DeviceCmdController.class);


    /**
     * @param cmdType:0模式设置，1间隔时间设置
     * @param cmdValue:命令值【cmdType为0时，-1正常模式，-2省电模式】
     * @param operatorId:用户id
     * @return
     * @throws Exception
     * @Description (新建命令)
     */
    @RequestMapping(value = "/{deviceId}", method = RequestMethod.POST)
    public String add(@PathVariable(value = "deviceId") Long deviceId,
                      @RequestParam("cmdType") Integer cmdType,
                      @RequestParam("cmdValue") Integer cmdValue,
                      @RequestParam("operatorId") Long operatorId) throws Exception {

        CmdType cmdTypeEu = CmdType.INTERVALTIME;
        if (1 == cmdType) {// 设置时间间隔
            cmdTypeEu = CmdType.INTERVALTIME;
        } else if (0 == cmdType) {// 设置工作模式
            cmdTypeEu = CmdType.WORKPATTERN;
        }

        //正常模式1  省电模式0
        if (cmdValue == -1) {
            cmdValue = 1;
        } else if (cmdValue == -2) {
            cmdValue = 0;
        }

//        boolean bool = deviceCommandService.saveCommand(deviceId, cmdTypeEu, cmdValue, operatorId);
        return new GlobalResult().boolResultJSON(false);
    }


}
