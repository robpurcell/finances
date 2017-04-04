package com.robbyp.finances.service.backend;

import com.robbyp.finances.testutil.AbstractEntityEventTest;

public class AccountEventTest extends AbstractEntityEventTest {
  @Override
  protected Class<Account> entityClass() {
    return Account.class;
  }
}
