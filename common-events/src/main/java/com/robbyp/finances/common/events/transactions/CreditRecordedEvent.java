package com.robbyp.finances.common.events.transactions;


public class CreditRecordedEvent extends MoneyTransferEvent {
  private TransferDetails details;

  private CreditRecordedEvent() {
  }

  public CreditRecordedEvent(TransferDetails details) {
    this.details = details;
  }

  public TransferDetails getDetails() {
    return details;
  }
}
