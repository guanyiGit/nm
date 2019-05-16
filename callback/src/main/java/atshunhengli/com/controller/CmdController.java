package atshunhengli.com.controller;

import atshunhengli.com.entity.iot.CmdType;
import atshunhengli.com.service.DeviceCommandIotService;
import atshunhengli.com.utils.GlobalResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/command")
public class CmdController {
    private Logger logger = LoggerFactory.getLogger(CmdController.class);

    @Autowired
    private DeviceCommandIotService deviceCommandIotService;

    /**
     * @param cmdType:0模式设置，1间隔时间设置
     * @param cmdValue:命令值【cmdType为0时，-1正常模式，-2省电模式】
     * @return
     * @throws Exception
     * @Description (发送命令)
     */
    @RequestMapping(value = "/{deviceIdIot}")
    public String sendCmd(@PathVariable(value = "deviceIdIot") String deviceIdIot,
                          @RequestParam("cmdType") Integer cmdType,
                          @RequestParam("cmdValue") Integer cmdValue) throws Exception {

        logger.info("sen cmd deviceid:" + deviceIdIot);

        CmdType cmdTypeEu = CmdType.INTERVALTIME;
        if (1 == cmdType) {// 设置时间间隔
            cmdTypeEu = CmdType.INTERVALTIME;
        } else if (0 == cmdType) {// 设置工作模式
            cmdTypeEu = CmdType.WORKPATTERN;
        }
        int mid = (int) (Math.random() * 9999);

        return new GlobalResult().successJSON(deviceCommandIotService.sendCommand(cmdTypeEu, cmdValue, mid, deviceIdIot));
    }

}
