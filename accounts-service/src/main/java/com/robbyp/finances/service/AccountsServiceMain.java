package com.robbyp.finances.service;

import com.robbyp.finances.commonswagger.CommonSwaggerConfiguration;
import com.robbyp.finances.service.web.AccountsWebConfiguration;
import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AccountsWebConfiguration.class, EventuateDriverConfiguration.class, CommonSwaggerConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class AccountsServiceMain {

  public static void main(String[] args) {
    SpringApplication.run(AccountsServiceMain.class, args);
  }
}
