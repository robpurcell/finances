package com.robbyp.finances.accounts.service;


import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import com.robbyp.finances.accounts.commands.AccountCommand;
import com.robbyp.finances.accounts.commands.DeleteAccountCommand;
import com.robbyp.finances.accounts.commands.OpenAccountCommand;

public class AccountService  {

  private final AggregateRepository<Account, AccountCommand> accountRepository;

  public AccountService(AggregateRepository<Account, AccountCommand> accountRepository) {
    this.accountRepository = accountRepository;
  }

  public CompletableFuture<EntityWithIdAndVersion<Account>> openAccount(String customerId, String title, BigDecimal initialBalance, String description) {
    return accountRepository.save(new OpenAccountCommand(customerId, title, initialBalance, description));
  }

  public CompletableFuture<EntityWithIdAndVersion<Account>> deleteAccount(String accountId) {
    return accountRepository.update(accountId, new DeleteAccountCommand());
  }
}
