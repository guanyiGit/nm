package com.soholy.cb.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.soholy.cb.common.DynamicDataSource;
import com.soholy.cb.common.DynamicDataSourceHolder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

@Configuration
public class DruidConfig {
  @Value("${spring.wifiDataSource.username}")
  private String wifiDbUName;
  
  @Value("${spring.wifiDataSource.password}")
  private String wifiDbPwd;
  
  @Value("${spring.wifiDataSource.url}")
  private String wifiDbUrl;
  
  @ConfigurationProperties(prefix = "spring.datasource")
  @Bean
  public DataSource defaultDataSource() { return new DruidDataSource(); }
  
  @Bean
  public DataSource wifiDataSource(@Qualifier("defaultDataSource") DataSource defaultDataSource) {
    DruidDataSource dataSource = new DruidDataSource();
    BeanUtils.copyProperties(defaultDataSource, dataSource);
    dataSource.setUsername(this.wifiDbUName);
    dataSource.setPassword(this.wifiDbPwd);
    dataSource.setUrl(this.wifiDbUrl);
    return dataSource;
  }
  
  @Bean
  @Primary
  public DynamicDataSource dynamicDataSource(@Qualifier("defaultDataSource") @Lazy DataSource defaultDataSource, @Qualifier("wifiDataSource") @Lazy DataSource wifiDataSource) {
    Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
    targetDataSources.put(DynamicDataSourceHolder.DbType.DEFAUALT.getDbType(), defaultDataSource);
    targetDataSources.put(DynamicDataSourceHolder.DbType.WIFI.getDbType(), wifiDataSource);
    DynamicDataSource dataSource = new DynamicDataSource();
    dataSource.setTargetDataSources(targetDataSources);
    dataSource.setDefaultTargetDataSource(defaultDataSource);
    return dataSource;
  }
  
  @Bean
  public ServletRegistrationBean druidServlet() {
    ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), new String[] { "/druid/*" });
    Map<String, String> map = new HashMap<String, String>();
    map.put("loginUsername", "admin");
    map.put("loginPassword", "admin");
    map.put("allow", "localhost");
    bean.setInitParameters(map);
    return bean;
  }
  
  @Bean
  public FilterRegistrationBean webStatFilter() {
    FilterRegistrationBean bean = new FilterRegistrationBean();
    bean.setFilter(new WebStatFilter());
    Map<String, String> map = new HashMap<String, String>();
    map.put("exclusions", "*.js,*.css,/durid/*");
    bean.setInitParameters(map);
    bean.setUrlPatterns(Arrays.asList(new String[] { "/*" }));
    return bean;
  }
}
