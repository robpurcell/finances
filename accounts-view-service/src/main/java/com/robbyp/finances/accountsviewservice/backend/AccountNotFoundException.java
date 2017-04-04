package com.robbyp.finances.accountsviewservice.backend;

public class AccountNotFoundException extends RuntimeException {

  public AccountNotFoundException(String accountId) {
    super("Account not found " + accountId);
  }
}
