package com.robbyp.finances.backend;

import io.eventuate.Int128;
import io.eventuate.javaclient.spring.jdbc.EventuateJdbcEventStoreConfiguration;
import io.eventuate.javaclient.spring.jdbc.IdGenerator;
import io.eventuate.javaclient.spring.jdbc.IdGeneratorImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import com.robbyp.finances.accountsview.service.AccountInfo;
import com.robbyp.finances.accountsview.service.AccountInfoUpdateService;
import com.robbyp.finances.accountsview.service.AccountQueryService;
import com.robbyp.finances.accountsview.service.AccountViewBackendConfiguration;
import com.robbyp.finances.common.events.accounts.AccountCreditedEvent;
import com.robbyp.finances.common.accounts.AccountChangeInfo;
import com.robbyp.finances.common.accounts.AccountTransactionInfo;
import com.robbyp.finances.common.transactions.TransferState;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountInfoUpdateServiceTest {

  @Configuration
  @EnableAutoConfiguration
  @Import({AccountViewBackendConfiguration.class, EventuateJdbcEventStoreConfiguration.class})
  public static class AccountInfoUpdateServiceTestConfiguration {

  }

  @Autowired
  private AccountInfoUpdateService accountInfoUpdateService;

  @Autowired
  private AccountQueryService accountQueryService;

  @Test
  public void shouldSaveAccountInfo() throws ExecutionException, InterruptedException {
    IdGenerator x = new IdGeneratorImpl();
    String accountId = x.genId().asString();
    String customerId = x.genId().asString();
    Int128 version = x.genId();

    String title = "Checking account";
    BigDecimal initialBalance = new BigDecimal("1345");
    String description = "Some account";

    accountInfoUpdateService.create(accountId, customerId, title, initialBalance, description, version);

    AccountInfo accountInfo = accountQueryService.findByAccountId(accountId);

    assertEquals(accountId, accountInfo.getId());
    assertEquals(customerId, accountInfo.getCustomerId());
    assertEquals(title, accountInfo.getTitle());
    assertEquals(description, accountInfo.getDescription());
    assertEquals(initialBalance.longValue() * 100, accountInfo.getBalance());
    assertEquals(1, accountInfo.getChanges().size());
    assertTrue(accountInfo.getTransactions().isEmpty());
    assertEquals(version.asString(), accountInfo.getVersion());


    String changeId = x.genId().asString();

    String transactionId = x.genId().asString();

    AccountChangeInfo change = new AccountChangeInfo(changeId, transactionId,
                                                     AccountCreditedEvent.class.getSimpleName(),
                                                     500, +1);

    accountInfoUpdateService.updateBalance(accountId, changeId, 500,
                                           change);

    accountInfo = accountQueryService.findByAccountId(accountId);
    assertEquals(initialBalance.add(new BigDecimal(5)).longValue() * 100, accountInfo.getBalance());
    assertFalse(accountInfo.getChanges().isEmpty());

    assertEquals(change, accountInfo.getChanges().get(1));

    String eventId = x.genId().asString();

    AccountTransactionInfo ti = new AccountTransactionInfo(transactionId, accountId, accountId, 5, new Date(),
                                                           "A transfer");

    accountInfoUpdateService.addTransaction(accountId, ti);

    accountInfo = accountQueryService.findByAccountId(accountId);
    assertFalse(accountInfo.getTransactions().isEmpty());

    assertEquals(ti, accountInfo.getTransactions().get(0));
  }

  @Test
  public void shouldHandleDuplicateSaveAccountInfo() throws ExecutionException, InterruptedException {
    IdGenerator x = new IdGeneratorImpl();
    String accountId = x.genId().asString();
    String customerId = x.genId().asString();
    Int128 version = x.genId();

    String title = "Checking account";
    BigDecimal initialBalance = new BigDecimal("1345");
    String description = "Some account";

    accountInfoUpdateService.create(accountId, customerId, title, initialBalance, description, version);
    accountInfoUpdateService.create(accountId, customerId, title, initialBalance, description, version);
  }

  @Test
  public void shouldUpdateTransactionStatus() {
    IdGenerator x = new IdGeneratorImpl();
    String accountId = x.genId().asString();
    String customerId = x.genId().asString();
    Int128 version = x.genId();

    String title = "Checking account";
    BigDecimal initialBalance = new BigDecimal("1345");
    String description = "Some account";

    accountInfoUpdateService.create(accountId, customerId, title, initialBalance, description, version);

    String transactionId = x.genId().asString();

    AccountTransactionInfo transactionInfo = new AccountTransactionInfo();
    transactionInfo.setTransactionId(transactionId);
    transactionInfo.setStatus(TransferState.INITIAL);

    accountInfoUpdateService.addTransaction(accountId, transactionInfo);

    AccountInfo accountInfo = accountQueryService.findByAccountId(accountId);
    assertEquals(accountId, accountInfo.getId());
    assertFalse(accountInfo.getTransactions().isEmpty());
    assertEquals(1, accountInfo.getTransactions().size());

    assertEquals(TransferState.INITIAL, accountInfo.getTransactions().get(0).getStatus());

    accountInfoUpdateService.updateTransactionStatus(accountId, transactionId, TransferState.COMPLETED);

    accountInfo = accountQueryService.findByAccountId(accountId);
    assertEquals(accountId, accountInfo.getId());
    assertFalse(accountInfo.getTransactions().isEmpty());
    assertEquals(1, accountInfo.getTransactions().size());

    assertEquals(TransferState.COMPLETED, accountInfo.getTransactions().get(0).getStatus());
  }
}
