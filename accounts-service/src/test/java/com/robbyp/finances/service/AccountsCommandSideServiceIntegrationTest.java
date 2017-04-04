package com.robbyp.finances.service;

import com.robbyp.finances.common.accounts.CreateAccountRequest;
import com.robbyp.finances.common.accounts.CreateAccountResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
  classes = AccountsCommandSideServiceTestConfiguration.class,
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class AccountsCommandSideServiceIntegrationTest {

  @Value("${local.server.port}")
  private int port = 0;

  private String baseUrl(String path) {
    return "http://localhost:" + port + "/api" + path;
  }

  @Autowired
  RestTemplate restTemplate;

  @Test
  public void shouldCreateAccounts() {
    BigDecimal initialFromAccountBalance = new BigDecimal(500);
    BigDecimal initialToAccountBalance = new BigDecimal(100);
    String customerId = "00000000-00000000";
    String title = "My Account";

    final CreateAccountResponse fromAccount =
      restTemplate.postForEntity(
        baseUrl("/accounts"),
        new CreateAccountRequest(customerId, title, "", initialFromAccountBalance),
        CreateAccountResponse.class
      ).getBody();
    final String fromAccountId = fromAccount.getAccountId();

    CreateAccountResponse toAccount = restTemplate
      .postForEntity(baseUrl("/accounts"), new CreateAccountRequest(customerId, title, "", initialToAccountBalance),
                     CreateAccountResponse.class).getBody();
    String toAccountId = toAccount.getAccountId();

    Assert.assertNotNull(fromAccountId);
    Assert.assertNotNull(toAccountId);
  }

}
