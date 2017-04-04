package com.robbyp.finances.backend.common.transactions;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.robbyp.finances.banking.transactionsservice.backend.MoneyTransfer")
public abstract class MoneyTransferEvent implements Event {
}
