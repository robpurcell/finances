package com.robbyp.finances.common.events.accounts;

import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="com.robbyp.finances.accounts.backend.Account")
public abstract class AccountEvent implements Event{
}
