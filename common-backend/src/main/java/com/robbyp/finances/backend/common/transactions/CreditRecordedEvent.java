package com.robbyp.finances.backend.common.transactions;


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
