package com.soholy.cb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.soholy.cb.enums.CallbackType;
import com.soholy.cb.service.CBService;
import com.soholy.cb.service.log.LogService;
import com.soholy.cb.utils.R;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/callback"})
public class CBController {
  private static final Logger log = Logger.getLogger(CBController.class.getName());
  
  @Autowired
  private CBService cbService;
  
  @Autowired
  private LogService logService;
  
  @RequestMapping({"/test"})
  R test() { return R.ok(); }
  
  @RequestMapping({"/deviceDataChanged"})
  public void deviceDataChanged(@RequestBody String body) { log.info("deviceDataChanged:" + body); }
  
  @RequestMapping({"/deviceDatasChanged"})
  public void deviceDatasChanged(@RequestBody String body) {
    log.info("deviceDatasChanged:" + body);
    try {
      JSONObject json = JSON.parseObject(body);
      String notifyType = json.getString("notifyType");
      if (CallbackType.deviceDatasChanged.toString().equals(notifyType))
        this.cbService.deviceDatasChanged(json); 
    } catch (Exception e) {
      e.printStackTrace();
      log.warning("电信凭条参数错误！");
    } 
  }

  /**
   * http://192.168.0.69:18082/callback/datas
   * https://119.147.209.163:8082/callback/datas
   * @param imei
   * @param pageNo
   * @param pageSize
   * @return
   */
  @RequestMapping({"/datas"})
  public R deviceDatasChanged(@RequestParam(value = "imei", required = false) String imei, @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", required = false, defaultValue = "50") int pageSize) {
    try {
      return R.ok(this.logService.findLog(imei, pageNo, pageSize));
    } catch (Exception e) {
      e.printStackTrace();
      return R.error();
    } 
  }
  
  @RequestMapping({"/bindDevice"})
  public void bindDevice(@RequestBody String body) { log.info("bindDevice:" + body); }
  
  @RequestMapping({"/deviceAdded"})
  public void deviceAdded(@RequestBody String body) { log.info("deviceAdded:" + body); }
  
  @RequestMapping({"/deviceInfoChanged"})
  public void deviceInfoChanged(@RequestBody String body) { log.info("deviceInfoChanged:" + body); }
  
  @RequestMapping({"/deviceDeleted"})
  public void deviceDeleted(@RequestBody String body) { log.info("deviceDeleted:" + body); }
  
  @RequestMapping({"/serviceInfoChanged"})
  public void serviceInfoChanged(@RequestBody String body) { log.info("serviceInfoChanged:" + body); }
  
  @RequestMapping({"/ruleEvent"})
  public void ruleEvent(@RequestBody String body) { log.info("ruleEvent:" + body); }
  
  @RequestMapping({"/deviceModelAdded"})
  public void deviceModelAdded(@RequestBody String body) { log.info("deviceModelAdded:" + body); }
  
  @RequestMapping({"/deviceModelDeleted"})
  public void deviceModelDeleted(@RequestBody String body) { log.info("deviceModelDeleted:" + body); }
}
