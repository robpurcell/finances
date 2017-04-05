package com.robbyp.finances.common.events.customers;

public class CustomerToAccountDeletedEvent extends CustomerEvent {

  private String accountId;

  public CustomerToAccountDeletedEvent() {
  }

  public CustomerToAccountDeletedEvent(String accountId) {
    this.accountId = accountId;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }
}
