package com.robbyp.finances.common.events.accounts;

public class AccountDebitFailedDueToInsufficientFundsEvent extends AccountEvent {
  private String transactionId;

  private AccountDebitFailedDueToInsufficientFundsEvent() {
  }

  public AccountDebitFailedDueToInsufficientFundsEvent(String transactionId) {
    this.transactionId = transactionId;
  }

  public String getTransactionId() {
    return transactionId;
  }
}
