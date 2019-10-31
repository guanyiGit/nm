package com.soholy;

import com.soholy.config.DruidConfig;
import lombok.extern.java.Log;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Import({DruidConfig.class})
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.soholy.mapper*")
@Log
@PropertySource(value = {"classpath:config/conf.properties"})
public class OtherdbApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtherdbApplication.class, args);
        log.info("===============================  启动完成  ===============================");
    }

}
