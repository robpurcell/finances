package com.robbyp.finances.common.events.customers;

import com.robbyp.finances.common.customers.CustomerInfo;

public class CustomerCreatedEvent extends CustomerEvent {

  private CustomerInfo customerInfo;

  public CustomerCreatedEvent() {
  }

  public CustomerCreatedEvent(CustomerInfo customerInfo) {
    this.customerInfo = customerInfo;
  }

  public CustomerInfo getCustomerInfo() {
    return customerInfo;
  }

  public void setCustomerInfo(CustomerInfo customerInfo) {
    this.customerInfo = customerInfo;
  }
}
