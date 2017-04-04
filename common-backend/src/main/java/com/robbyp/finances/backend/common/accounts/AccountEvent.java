package com.robbyp.finances.backend.common.accounts;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.robbyp.finances.service.backend.Account")
public abstract class AccountEvent implements Event{
}
