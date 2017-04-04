package com.robbyp.finances.accountsviewservice;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.robbyp.finances.accountsviewservice.web.AccountViewWebConfiguration;
import com.robbyp.finances.commonswagger.CommonSwaggerConfiguration;

@Configuration
@Import({AccountViewWebConfiguration.class, EventuateDriverConfiguration.class, CommonSwaggerConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class AccountsViewServiceMain {

  public static void main(String[] args) {
    SpringApplication.run(AccountsViewServiceMain.class, args);
  }
}
