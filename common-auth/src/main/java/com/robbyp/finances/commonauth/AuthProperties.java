package com.robbyp.finances.commonauth;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth")
public class AuthProperties {
  private String serverSecret = "the_cake_is_a_lie";
  private Integer serverInteger = 1;

  public String getServerSecret() {
    return serverSecret;
  }

  public void setServerSecret(String serverSecret) {
    this.serverSecret = serverSecret;
  }

  public Integer getServerInteger() {
    return serverInteger;
  }

  public void setServerInteger(Integer serverInteger) {
    this.serverInteger = serverInteger;
  }
}
