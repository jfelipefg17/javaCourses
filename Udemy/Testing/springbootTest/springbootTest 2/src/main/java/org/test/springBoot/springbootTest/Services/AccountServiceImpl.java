package org.test.springBoot.springbootTest.Services;

import org.springframework.stereotype.Service;
import org.test.springBoot.springbootTest.Entities.Account;
import org.test.springBoot.springbootTest.Entities.Bank;
import org.test.springBoot.springbootTest.Repositories.AccountRepository;
import org.test.springBoot.springbootTest.Repositories.BankRepository;

import java.math.BigDecimal;

//@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  private final BankRepository bankRepository;

  public AccountServiceImpl(AccountRepository accountRepository, BankRepository bankRepository) {
    this.accountRepository = accountRepository;
    this.bankRepository = bankRepository;
  }

  @Override
  public Account findById(Long id) {
    return accountRepository.findById(id).orElseThrow();
  }

  @Override
  public int totalTransfers(Long bankId) {
    Bank bank = bankRepository.findById((bankId)).orElseThrow();
    return bank.getNumberOfTransfers();
  }

  @Override
  public BigDecimal findBalance(Long accountId) {
    Account account = accountRepository.findById(accountId).orElseThrow();
    return account.getBalance();
  }

  @Override
  public void transfer(Long numberSender, Long numberDestination, BigDecimal amount, Long bankId) {


    Account accountSender = accountRepository.findById(numberSender).orElseThrow();
    accountSender.debit(amount);
    accountRepository.save(accountSender);

    Account accountDestination = accountRepository.findById(numberDestination).orElseThrow();
    accountDestination.credit(amount);
    accountRepository.save(accountDestination);

    Bank bank = bankRepository.findById(bankId).orElseThrow();
    int totalTransfers = bank.getNumberOfTransfers();
    bank.setNumberOfTransfers(++totalTransfers);
    bankRepository.save(bank);
  }
}