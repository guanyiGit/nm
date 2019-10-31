package com.soholy.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
  private static ApplicationContext ctx;
  
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException { ctx = applicationContext; }
  
  public static <T> T getBean(Class<T> aClass) {
    return (T)ctx.getBean(aClass);
  }
  
  public static Object getBean(String beanName) { return ctx.getBean(beanName); }
}
