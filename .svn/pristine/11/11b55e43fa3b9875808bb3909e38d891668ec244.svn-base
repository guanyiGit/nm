package com.soholy.cb.controller;

import com.soholy.cb.enums.CmdType;
import com.soholy.cb.enums.CodecVersion;
import com.soholy.cb.service.app.CmdService;
import com.soholy.cb.utils.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/command"})
public class CmdController {
    private Logger logger = LoggerFactory.getLogger(CmdController.class);

    @Autowired
    private CmdService cmdService;


  /*






  cmdType:0模式设置，1间隔时间设置
  cmdValue:
      cmdType=0  -1正常模式，-2省电模式
      cmdType=1  为实际值（如300秒）

  例子：设置间隔时间5分钟(80000064569852364)
  https://119.147.209.163:8082/command/3f618136-043c-4051-abe2-b41c4f9fbabb?cmdType=1&cmdValue=300






   */

    /**
     * @param cmdType:0模式设置，1间隔时间设置
     * @param cmdValue:命令值【cmdType为0时，-1正常模式，-2省电模式】
     * @return
     * @throws Exception
     * @Description (新建命令)
     */
    @RequestMapping({"/{deviceIdIot}"})
    public R sendCmd(@PathVariable("deviceIdIot") String deviceIdIot,
                     @RequestParam("cmdType") Integer cmdType,
                     @RequestParam("cmdValue") Integer cmdValue) throws Exception {
        this.logger.info("sen cmd deviceid:" + deviceIdIot);
        CmdType cmdTypeEu = CmdType.INTERVALTIME;
        if (1 == cmdType.intValue()) {
            cmdTypeEu = CmdType.INTERVALTIME;
        } else if (0 == cmdType.intValue()) {
            cmdTypeEu = CmdType.WORKPATTERN;
        }
        int mid = (int) (Math.random() * 9999.0D);
        return R.ok(this.cmdService.sendCommand(cmdTypeEu, cmdValue, Integer.valueOf(mid), deviceIdIot, CodecVersion.CODEC_VERSION));
    }
}
