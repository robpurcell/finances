package com.robbyp.finances.accountsview;

import io.eventuate.javaclient.driver.EventuateDriverConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.robbyp.finances.accountsview.web.AccountViewWebConfiguration;
import com.robbyp.finances.commonswagger.CommonSwaggerConfiguration;

@Configuration
@Import({AccountViewWebConfiguration.class, EventuateDriverConfiguration.class, CommonSwaggerConfiguration.class})
@EnableAutoConfiguration
@ComponentScan
public class AccountsViewMain {

  public static void main(String[] args) {
    SpringApplication.run(AccountsViewMain.class, args);
  }
}
