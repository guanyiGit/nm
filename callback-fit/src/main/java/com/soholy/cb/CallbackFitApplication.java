package com.soholy.cb;

import java.util.logging.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import({com.soholy.cb.config.DruidConfig.class})
@SpringBootApplication
@EnableTransactionManagement
@MapperScan({"com.soholy.cb.dao*"})
@EnableJms
public class CallbackFitApplication {
  private static final Logger log = Logger.getLogger(CallbackFitApplication.class.getName());
  
  public static void main(String[] args) {
    SpringApplication.run(CallbackFitApplication.class, args);
    log.info("===============================  启动完成  ===============================");
  }
}
