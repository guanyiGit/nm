package com.soholy.cb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.soholy.cb.config.IotPropertiesConfig;
import com.soholy.cb.service.CBService;
import com.soholy.cb.service.activemq.AmqProducer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CBServiceImpl implements CBService {
  private static final Logger log = Logger.getLogger(CBServiceImpl.class.getName());
  
  private static ExecutorService THREADPOOL = Executors.newFixedThreadPool((Runtime.getRuntime().availableProcessors() <= 4) ? Runtime.getRuntime().availableProcessors() : 4);
  
  @Autowired
  private IotPropertiesConfig conf;
  
  public boolean deviceDatasChanged(JSONObject json) {
    THREADPOOL.execute(new AmqProducer(json, this.conf.getCodecParamName()));
    return true;
  }
}
