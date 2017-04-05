package com.robbyp.finances.common.events.transactions;

import com.robbyp.finances.common.transactions.TransferDetails;

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
