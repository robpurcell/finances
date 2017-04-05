package com.robbyp.finances.common.events.transactions;


import com.robbyp.finances.common.transactions.TransferDetails;

public class FailedDebitRecordedEvent extends MoneyTransferEvent {
  private TransferDetails details;

  private FailedDebitRecordedEvent() {
  }

  public FailedDebitRecordedEvent(TransferDetails details) {
    this.details = details;
  }

  public TransferDetails getDetails() {
    return details;
  }
}
