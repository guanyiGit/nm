package com.soholy.cb.config;

import com.soholy.cb.converters.GlobleDateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class ConvertConfig {
  @Bean
  public Converter converter() { return new GlobleDateConverter(); }
}
