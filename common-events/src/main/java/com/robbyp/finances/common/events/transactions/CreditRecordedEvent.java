package com.robbyp.finances.common.events.transactions;


import com.robbyp.finances.common.transactions.TransferDetails;

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
