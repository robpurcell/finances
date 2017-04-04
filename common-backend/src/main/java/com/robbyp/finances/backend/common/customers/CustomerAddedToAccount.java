package com.robbyp.finances.backend.common.customers;

import com.robbyp.finances.common.customers.ToAccountInfo;

public class CustomerAddedToAccount extends CustomerEvent {

  private ToAccountInfo toAccountInfo;

  public CustomerAddedToAccount() {
  }

  public CustomerAddedToAccount(ToAccountInfo toAccountInfo) {
    this.toAccountInfo = toAccountInfo;
  }

  public ToAccountInfo getToAccountInfo() {
    return toAccountInfo;
  }

  public void setToAccountInfo(ToAccountInfo toAccountInfo) {
    this.toAccountInfo = toAccountInfo;
  }
}
