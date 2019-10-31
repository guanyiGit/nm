package com.soholy.cb.service.impl;

import com.soholy.cb.dao.TDeviceDataWifiDb;
import com.soholy.cb.entity.TDeviceDataWifiEntity;
import com.soholy.cb.service.TDeviceDataWifiService;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TDeviceDataWifiServiceImpl implements TDeviceDataWifiService {
  private static final Logger log = Logger.getLogger(TDeviceDataWifiServiceImpl.class.getName());
  
  @Autowired
  private TDeviceDataWifiDb tDeviceDataWifiMapper;
  
  public boolean saves(List<TDeviceDataWifiEntity> tDeviceDataWifis) {
    if (tDeviceDataWifis != null && tDeviceDataWifis.size() > 0)
      return (tDeviceDataWifis.size() == this.tDeviceDataWifiMapper.saves(tDeviceDataWifis)); 
    return false;
  }
}
