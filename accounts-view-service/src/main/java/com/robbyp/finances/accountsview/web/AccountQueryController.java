package com.robbyp.finances.accountsview.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.robbyp.finances.accountsview.service.AccountInfo;
import com.robbyp.finances.accountsview.service.AccountNotFoundException;
import com.robbyp.finances.accountsview.service.AccountQueryService;
import com.robbyp.finances.common.accounts.AccountHistoryEntry;
import com.robbyp.finances.common.accounts.AccountHistoryResponse;
import com.robbyp.finances.common.accounts.AccountOpenInfo;
import com.robbyp.finances.common.accounts.GetAccountResponse;

@RestController
@RequestMapping("/api")
public class AccountQueryController {

  private AccountQueryService accountInfoQueryService;

  @Autowired
  public AccountQueryController(AccountQueryService accountInfoQueryService) {
    this.accountInfoQueryService = accountInfoQueryService;
  }

  @RequestMapping(value = "/accounts/{accountId}", method = RequestMethod.GET)
  public ResponseEntity<GetAccountResponse> get(@PathVariable String accountId) {
    AccountInfo accountInfo = accountInfoQueryService.findByAccountId(accountId);
    return ResponseEntity.ok().body(
      new GetAccountResponse(accountInfo.getId(),
                             new BigDecimal(accountInfo.getBalance()),
                             accountInfo.getTitle(),
                             accountInfo.getDescription())
    );
  }

  @RequestMapping(value = "/accounts/{accountId}/history", method = RequestMethod.GET)
  public ResponseEntity<AccountHistoryResponse> getTransactionsHistory(@PathVariable String accountId) {
    AccountInfo accountInfo = accountInfoQueryService.findByAccountId(accountId);
    List<AccountHistoryEntry> historyEntries = new ArrayList<>();
    historyEntries.add(new AccountOpenInfo(accountInfo.getCreationDate(),
                                           AccountHistoryEntry.EntryType.account,
                                           accountInfo.getChanges().get(0).getAmount())
    );
    accountInfo.getTransactions().forEach(historyEntries::add);

    return ResponseEntity.ok().body(new AccountHistoryResponse(historyEntries));
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "account not found")
  @ExceptionHandler(AccountNotFoundException.class)
  public void accountNotFound() {
  }
}
