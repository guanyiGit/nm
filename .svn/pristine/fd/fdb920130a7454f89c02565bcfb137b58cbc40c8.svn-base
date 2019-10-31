package com.soholy.cb.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MybatisPlusConfig {
  @Bean
  @Profile({"dev"})
  public PerformanceInterceptor performanceInterceptor() {
    PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
    performanceInterceptor.setMaxTime(100000L);
    performanceInterceptor.setFormat(true);
    return performanceInterceptor;
  }
  
  @Bean
  public PaginationInterceptor paginationInterceptor() { return new PaginationInterceptor(); }
}
