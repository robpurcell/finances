package com.robbyp.finances.accounts.service;

import com.robbyp.finances.testutil.AbstractEntityEventTest;

public class AccountEventTest extends AbstractEntityEventTest {
  @Override
  protected Class<Account> entityClass() {
    return Account.class;
  }
}
