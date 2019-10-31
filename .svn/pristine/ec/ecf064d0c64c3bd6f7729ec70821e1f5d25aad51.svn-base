package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableAutoConfiguration
//(exclude = {SecurityAutoConfiguration.class})//(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.dao")
@EnableCaching
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BootdoApplication  {
    public static void main(String[] args) {
        SpringApplication.run(BootdoApplication.class, args);
    }
}
