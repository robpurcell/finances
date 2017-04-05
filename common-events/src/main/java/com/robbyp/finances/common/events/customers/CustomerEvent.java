package com.robbyp.finances.common.events.customers;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity = "com.robbyp.finances.banking.customersservice.backend.Customer")
public abstract class CustomerEvent implements Event {
}
