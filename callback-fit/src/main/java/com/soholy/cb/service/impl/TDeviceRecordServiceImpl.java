package com.soholy.cb.service.impl;

import com.soholy.cb.dao.TDeviceRecordDb;
import com.soholy.cb.entity.TDeviceRecordEntity;
import com.soholy.cb.service.TDeviceRecordService;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TDeviceRecordServiceImpl implements TDeviceRecordService {
  private static final Logger log = Logger.getLogger(TDeviceRecordServiceImpl.class.getName());
  
  @Autowired
  private TDeviceRecordDb tDeviceRecordMapper;
  
  public boolean save(TDeviceRecordEntity data) {
    if (data != null)
      return (1 == this.tDeviceRecordMapper.insert(data)); 
    return false;
  }
}
