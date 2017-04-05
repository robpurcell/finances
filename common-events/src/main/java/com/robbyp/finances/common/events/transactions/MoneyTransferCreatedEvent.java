package com.robbyp.finances.common.events.transactions;


public class MoneyTransferCreatedEvent extends MoneyTransferEvent {
  private TransferDetails details;

  private MoneyTransferCreatedEvent() {
  }

  public MoneyTransferCreatedEvent(TransferDetails details) {
    this.details = details;
  }

  public TransferDetails getDetails() {
    return details;
  }
}

