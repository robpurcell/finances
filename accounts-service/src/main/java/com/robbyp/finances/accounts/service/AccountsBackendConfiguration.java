package com.robbyp.finances.accounts.service;

import io.eventuate.AggregateRepository;
import io.eventuate.EventuateAggregateStore;
import io.eventuate.javaclient.spring.EnableEventHandlers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.robbyp.finances.accounts.commands.AccountCommand;

@Configuration
@EnableEventHandlers
public class AccountsBackendConfiguration {

  @Bean
  public AccountWorkflow accountWorkflow() {
    return new AccountWorkflow();
  }


  @Bean
  public AccountService accountService(AggregateRepository<Account, AccountCommand> accountRepository) {
    return new AccountService(accountRepository);
  }

  @Bean
  public AggregateRepository<Account, AccountCommand> accountRepository(EventuateAggregateStore eventStore) {
    return new AggregateRepository<Account, AccountCommand>(Account.class, eventStore);
  }

}


