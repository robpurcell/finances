package com.robbyp.finances.backend.common.accounts;

import java.math.BigDecimal;

public class AccountDebitedEvent extends AccountChangedEvent {

  private AccountDebitedEvent() {
  }

  public AccountDebitedEvent(BigDecimal amount, String transactionId) {
    super(amount, transactionId);
  }

}
