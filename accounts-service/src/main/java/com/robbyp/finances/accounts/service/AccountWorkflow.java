package com.robbyp.finances.accounts.service;

import io.eventuate.EntityWithIdAndVersion;
import io.eventuate.EventHandlerContext;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;

import com.robbyp.finances.accounts.commands.CreditAccountCommand;
import com.robbyp.finances.accounts.commands.DebitAccountCommand;
import com.robbyp.finances.common.events.transactions.DebitRecordedEvent;
import com.robbyp.finances.common.events.transactions.MoneyTransferCreatedEvent;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@EventSubscriber(id = "accountEventHandlers")
public class AccountWorkflow {

  @EventHandlerMethod
  public CompletableFuture<?> debitAccount(EventHandlerContext<MoneyTransferCreatedEvent> ctx) {
    MoneyTransferCreatedEvent event = ctx.getEvent();

    BigDecimal amount = event.getDetails().getAmount();
    String transactionId = ctx.getEntityId();
    String fromAccountId = event.getDetails().getFromAccountId();

    return ctx.update(Account.class, fromAccountId, new DebitAccountCommand(amount, transactionId));
  }

  @EventHandlerMethod
  public CompletableFuture<EntityWithIdAndVersion<Account>> creditAccount(EventHandlerContext<DebitRecordedEvent> ctx) {
    DebitRecordedEvent event = ctx.getEvent();
    BigDecimal amount = event.getDetails().getAmount();
    String fromAccountId = event.getDetails().getToAccountId();
    String transactionId = ctx.getEntityId();

    return ctx.update(Account.class, fromAccountId, new CreditAccountCommand(amount, transactionId));
  }
}
