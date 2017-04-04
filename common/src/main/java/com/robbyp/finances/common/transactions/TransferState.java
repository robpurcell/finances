package com.robbyp.finances.common.transactions;

public enum TransferState {
  NEW, INITIAL, DEBITED, COMPLETED, FAILED_DUE_TO_INSUFFICIENT_FUNDS
}
