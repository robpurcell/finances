package com.robbyp.finances.accounts.service;

import io.eventuate.Event;

import com.robbyp.finances.accounts.commands.OpenAccountCommand;
import com.robbyp.finances.common.events.accounts.AccountOpenedEvent;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class AccountTest {

  @Test
  public void testSomething() {
    Account account = new Account();
    String title = "My Account";
    String customerId = "00000000-00000000";
    BigDecimal initialBalance = new BigDecimal(512);

    List<Event> events = account.process(new OpenAccountCommand(customerId, title, initialBalance, ""));

    Assert.assertEquals(1, events.size());
    Assert.assertEquals(AccountOpenedEvent.class, events.get(0).getClass());

    account.applyEvent(events.get(0));
    Assert.assertEquals(initialBalance, account.getBalance());
  }
}
