package com.robbyp.finances.web;

import com.robbyp.finances.common.accounts.GetAccountResponse;
import com.robbyp.finances.testutil.Producer;
import com.robbyp.finances.testutil.Verifier;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import static com.robbyp.finances.testutil.TestUtil.eventually;

@RunWith(SpringRunner.class)
@SpringBootTest(
  classes = AccountsQuerySideServiceTestConfiguration.class,
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class AccountsQuerySideServiceIntegrationTest {

  @Value("${local.server.port}")
  private int port;

  private String baseUrl(String path) {
    return "http://localhost:" + port + "/api" + path;
  }

  @Autowired
  RestTemplate restTemplate;


  @Test
  public void shouldCreateAccountsAndTransferMoney() {
    // TBD
  }

  private BigDecimal toCents(BigDecimal dollarAmount) {
    return dollarAmount.multiply(new BigDecimal(100));
  }

  private void assertAccountBalance(final String fromAccountId, final BigDecimal expectedBalanceInDollars) {
    final BigDecimal inCents = toCents(expectedBalanceInDollars);
    eventually(
      new Producer<GetAccountResponse>() {
        @Override
        public CompletableFuture<GetAccountResponse> produce() {
          return CompletableFuture.completedFuture(
            restTemplate.getForEntity(baseUrl("/accounts/" + fromAccountId), GetAccountResponse.class).getBody());
        }
      },
      new Verifier<GetAccountResponse>() {
        @Override
        public void verify(GetAccountResponse accountInfo) {
          Assert.assertEquals(fromAccountId, accountInfo.getAccountId());
          Assert.assertEquals(inCents, accountInfo.getBalance());
        }
      });
  }

}
