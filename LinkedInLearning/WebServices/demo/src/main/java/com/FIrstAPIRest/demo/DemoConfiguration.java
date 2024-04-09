package com.FIrstAPIRest.demo;

import com.FIrstAPIRest.model.Liga;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfiguration {

  @Bean
  public Liga liga(){
    return new Liga();
  }
}
