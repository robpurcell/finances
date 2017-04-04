package com.robbyp.finances.backend.common.transactions;

public class DebitRecordedEvent extends MoneyTransferEvent {
  private TransferDetails details;

  private DebitRecordedEvent() {
  }

  public DebitRecordedEvent(TransferDetails details) {
    this.details = details;
  }

  public TransferDetails getDetails() {
    return details;
  }
}
