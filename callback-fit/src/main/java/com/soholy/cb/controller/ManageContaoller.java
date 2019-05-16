package com.soholy.cb.controller;

import com.soholy.cb.service.TDeviceIotService;
import com.soholy.cb.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/device"})
public class ManageContaoller {
  @Autowired
  private TDeviceIotService tDeviceIotService;
  
  @RequestMapping(value = {"/register/{imei}"}, method = {RequestMethod.GET})
  public R register(@PathVariable("imei") String imei, String deviceBrand, String deviceBatch) throws Exception {
    if (StringUtils.isNotBlank(imei))
      return R.ok(this.tDeviceIotService.register(imei, deviceBrand, deviceBatch)); 
    return R.ok();
  }
  
  @RequestMapping({"/logoutDevice/{deviceIotId}"})
  public R logoutDevice(@PathVariable("deviceIotId") String deviceIotId) {
    try {
      this.tDeviceIotService.logout(deviceIotId);
      return R.ok();
    } catch (Exception e) {
      e.printStackTrace();
      return R.error(e.getMessage());
    } 
  }
}
