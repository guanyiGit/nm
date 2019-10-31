package com.soholy;

import com.soholy.config.DruidConfig;
import lombok.extern.java.Log;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@Import({DruidConfig.class})
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.soholy.mapper*")
@Log
@EnableJms
@PropertySource(value = {"classpath:config/aep.properties"})
public class CallbackLwm2mApplication {

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(30 * 1000);
        httpRequestFactory.setConnectTimeout(30 * 3000);
        httpRequestFactory.setReadTimeout(30 * 3000);
        return new RestTemplate(httpRequestFactory);
    }


    public static void main(String[] args) {
        SpringApplication.run(CallbackLwm2mApplication.class, args);
        log.info("===============================  启动完成  ===============================");
    }

}
