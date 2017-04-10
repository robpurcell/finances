package com.robbyp.finances.web;

import com.robbyp.finances.accountsview.web.AccountViewWebConfiguration;
import com.robbyp.finances.commonswagger.CommonSwaggerConfiguration;
import io.eventuate.javaclient.spring.jdbc.EmbeddedTestAggregateStoreConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Configuration
@Import({
  AccountViewWebConfiguration.class,
  EmbeddedTestAggregateStoreConfiguration.class,
  CommonSwaggerConfiguration.class
})
@EnableAutoConfiguration
@ComponentScan
public class AccountsQuerySideServiceTestConfiguration {

  @Bean
  public RestTemplate restTemplate(HttpMessageConverters converters) {
    RestTemplate restTemplate = new RestTemplate();
    HttpMessageConverter<?> httpMessageConverter = converters.getConverters().get(0);
    List<? extends HttpMessageConverter<?>> httpMessageConverters =
      Arrays.asList(new MappingJackson2HttpMessageConverter());
    restTemplate.setMessageConverters((List<HttpMessageConverter<?>>) httpMessageConverters);
    return restTemplate;
  }
}
