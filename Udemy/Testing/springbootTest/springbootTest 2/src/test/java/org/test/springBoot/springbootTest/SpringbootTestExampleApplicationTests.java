package org.test.springBoot.springbootTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.test.springBoot.springbootTest.Entities.Account;
import org.test.springBoot.springbootTest.Entities.Bank;
import org.test.springBoot.springbootTest.Exceptions.EnoughBalance;
import org.test.springBoot.springbootTest.Repositories.AccountRepository;
import org.test.springBoot.springbootTest.Repositories.BankRepository;
import org.test.springBoot.springbootTest.Services.AccountService;
import org.test.springBoot.springbootTest.Services.AccountServiceImpl;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SpringbootTestExampleApplicationTests {

	@Mock
	AccountRepository accountRepository;
	@Mock
	BankRepository bankRepository;
	@InjectMocks
	AccountServiceImpl accountService;

////this is the way to do it using spring
//	@MockBean
//	AccountRepository accountRepository;
//	@MockBean
//	BankRepository bankRepository;
//	@Autowired
//	AccountServiceImpl accountService;

	@BeforeEach
	void setUp() {
		//this is with put @mock or using spring
//		accountRepository = mock(AccountRepository.class);
//		bankRepository = mock(BankRepository.class);
//		accountService = new AccountServiceImpl(accountRepository, bankRepository);



		Data.ACCOUNT_001.setBalance(new BigDecimal("1000"));
		Data.ACCOUNT_002.setBalance(new BigDecimal("2000"));

		Data.BANK.setNumberOfTransfers(0);
	}

	@Test
	void contextLoads() {
		when(accountRepository.findById(1l)).thenReturn(Data.ACCOUNT_001);
		when(accountRepository.findById(2l)).thenReturn(Data.ACCOUNT_002);
		when(bankRepository.findById(1L)).thenReturn(Data.BANK);

		BigDecimal balanceOrigin = accountService.findBalance(1L);
		BigDecimal balanceDestination = accountService.findBalance(2l);


		assertEquals("1000", balanceOrigin.toPlainString());
		assertEquals("2000", balanceDestination.toPlainString());

		accountService.transfer(1L, 2L, new BigDecimal("100"), 1L);

		balanceOrigin = accountService.findBalance(1L);
		balanceDestination = accountService.findBalance(2l);

		assertEquals("900", balanceOrigin.toPlainString());
		assertEquals("2100", balanceDestination.toPlainString());

		int total = accountService.totalTransfers(1L);
		assertEquals(1, total);

		verify(accountRepository, times(3)).findById(1L);
		verify(accountRepository, times(3)).findById(2L);
		verify(accountRepository, times(2)).update(any(Account.class));

		verify(bankRepository, times(2)).findById(1L);
		verify(bankRepository).update(any(Bank.class));
	}

	@Test
	void contextLoads2() {
		when(accountRepository.findById(1l)).thenReturn(Data.ACCOUNT_001);
		when(accountRepository.findById(2l)).thenReturn(Data.ACCOUNT_002);
		when(bankRepository.findById(1L)).thenReturn(Data.BANK);

		BigDecimal balanceOrigin = accountService.findBalance(1L);
		BigDecimal balanceDestination = accountService.findBalance(2l);


		assertEquals("1000", balanceOrigin.toPlainString());
		assertEquals("2000", balanceDestination.toPlainString());

		assertThrows(EnoughBalance.class, () -> {
			accountService.transfer(1L, 2L, new BigDecimal("1200"), 1L);
		});

		balanceOrigin = accountService.findBalance(1L);
		balanceDestination = accountService.findBalance(2l);

		assertEquals("1000", balanceOrigin.toPlainString());
		assertEquals("2000", balanceDestination.toPlainString());

		int total = accountService.totalTransfers(1L);
		assertEquals(0, total);

		verify(accountRepository, times(3)).findById(1L);
		verify(accountRepository, times(2)).findById(2L);
		verify(accountRepository, never()).update(any(Account.class));

		verify(bankRepository, times(1)).findById(1L);
		verify(bankRepository, never()).update(any(Bank.class));

		verify(accountRepository, never()).findAll();
	}

	@Test
	void contextLoads3() {
		when(accountRepository.findById(1L)).thenReturn(Data.ACCOUNT_001);

		Account account1 = accountService.findById(1L);
		Account account2 = accountService.findById(1L);


		assertSame(account1, account2);

	}
}

