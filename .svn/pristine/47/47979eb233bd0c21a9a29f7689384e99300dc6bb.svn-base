package com.soholy.cb.controller;

import com.soholy.cb.enums.CmdType;
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
  
  @RequestMapping({"/{deviceIdIot}"})
  public R sendCmd(@PathVariable("deviceIdIot") String deviceIdIot, @RequestParam("cmdType") Integer cmdType, @RequestParam("cmdValue") Integer cmdValue) throws Exception {
    this.logger.info("sen cmd deviceid:" + deviceIdIot);
    CmdType cmdTypeEu = CmdType.INTERVALTIME;
    if (1 == cmdType.intValue()) {
      cmdTypeEu = CmdType.INTERVALTIME;
    } else if (0 == cmdType.intValue()) {
      cmdTypeEu = CmdType.WORKPATTERN;
    } 
    int mid = (int)(Math.random() * 9999.0D);
    return R.ok(this.cmdService.sendCommand(cmdTypeEu, cmdValue, Integer.valueOf(mid), deviceIdIot));
  }
}
