package com.robbyp.finances.accountsview.service;

public class AccountNotFoundException extends RuntimeException {

  public AccountNotFoundException(String accountId) {
    super("Account not found " + accountId);
  }
}
