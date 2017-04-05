package com.robbyp.finances.common.events.transactions;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.robbyp.finances.banking.transactionsservice.backend.MoneyTransfer")
public abstract class MoneyTransferEvent implements Event {
}
