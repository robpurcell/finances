package com.robbyp.finances.common.events.customers;

import com.robbyp.finances.common.customers.ToAccountInfo;

public class CustomerAddedToAccountEvent extends CustomerEvent {

  private ToAccountInfo toAccountInfo;

  public CustomerAddedToAccountEvent() {
  }

  public CustomerAddedToAccountEvent(ToAccountInfo toAccountInfo) {
    this.toAccountInfo = toAccountInfo;
  }

  public ToAccountInfo getToAccountInfo() {
    return toAccountInfo;
  }

  public void setToAccountInfo(ToAccountInfo toAccountInfo) {
    this.toAccountInfo = toAccountInfo;
  }
}
