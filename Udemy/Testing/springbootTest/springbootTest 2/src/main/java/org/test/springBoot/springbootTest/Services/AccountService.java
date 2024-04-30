package org.test.springBoot.springbootTest.Services;

import org.test.springBoot.springbootTest.Entities.Account;

import java.math.BigDecimal;

public interface AccountService {

  Account findById(Long id);

  int totalTransfers(Long bankId);

  BigDecimal findBalance(Long accountId);

  void transfer(Long numberSender, Long numberDestination, BigDecimal amount, Long bandId);
}
