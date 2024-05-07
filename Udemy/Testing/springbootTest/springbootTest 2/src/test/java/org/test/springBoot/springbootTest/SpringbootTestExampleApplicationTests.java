package org.test.springBoot.springbootTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.test.springBoot.springbootTest.Entities.Account;
import org.test.springBoot.springbootTest.Entities.Bank;
import org.test.springBoot.springbootTest.Exceptions.EnoughBalance;
import org.test.springBoot.springbootTest.Repositories.AccountRepository;
import org.test.springBoot.springbootTest.Repositories.BankRepository;
import org.test.springBoot.springbootTest.Services.AccountServiceImpl;

import java.math.BigDecimal;

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



	}

	@Test
	void contextLoads() {
		Mockito.when(accountRepository.findById(1l)).thenReturn(Data.createAccount001());
		Mockito.when(accountRepository.findById(2l)).thenReturn(Data.createAccount002());
		Mockito.when(bankRepository.findById(1L)).thenReturn(Data.createBank());

		BigDecimal balanceOrigin = accountService.findBalance(1L);
		BigDecimal balanceDestination = accountService.findBalance(2l);


		Assertions.assertEquals("1000", balanceOrigin.toPlainString());
		Assertions.assertEquals("2000", balanceDestination.toPlainString());

		accountService.transfer(1L, 2L, new BigDecimal("100"), 1L);

		balanceOrigin = accountService.findBalance(1L);
		balanceDestination = accountService.findBalance(2l);

		Assertions.assertEquals("900", balanceOrigin.toPlainString());
		Assertions.assertEquals("2100", balanceDestination.toPlainString());

		int total = accountService.totalTransfers(1L);
		Assertions.assertEquals(1, total);

		Mockito.verify(accountRepository, Mockito.times(3)).findById(1L);
		Mockito.verify(accountRepository, Mockito.times(3)).findById(2L);
		Mockito.verify(accountRepository, Mockito.times(2)).save(ArgumentMatchers.any(Account.class));

		Mockito.verify(bankRepository, Mockito.times(2)).findById(1L);
		Mockito.verify(bankRepository).save(ArgumentMatchers.any(Bank.class));
	}

	@Test
	void contextLoads2() {
		Mockito.when(accountRepository.findById(1l)).thenReturn(Data.createAccount001());
		Mockito.when(accountRepository.findById(2l)).thenReturn(Data.createAccount002());
		Mockito.when(bankRepository.findById(1L)).thenReturn(Data.createBank());

		BigDecimal balanceOrigin = accountService.findBalance(1L);
		BigDecimal balanceDestination = accountService.findBalance(2l);


		Assertions.assertEquals("1000", balanceOrigin.toPlainString());
		Assertions.assertEquals("2000", balanceDestination.toPlainString());

		Assertions.assertThrows(EnoughBalance.class, () -> {
			accountService.transfer(1L, 2L, new BigDecimal("1200"), 1L);
		});

		balanceOrigin = accountService.findBalance(1L);
		balanceDestination = accountService.findBalance(2l);

		Assertions.assertEquals("1000", balanceOrigin.toPlainString());
		Assertions.assertEquals("2000", balanceDestination.toPlainString());

		int total = accountService.totalTransfers(1L);
		Assertions.assertEquals(0, total);

		Mockito.verify(accountRepository, Mockito.times(3)).findById(1L);
		Mockito.verify(accountRepository, Mockito.times(2)).findById(2L);
		Mockito.verify(accountRepository, Mockito.never()).save(ArgumentMatchers.any(Account.class));

		Mockito.verify(bankRepository, Mockito.times(1)).findById(1L);
		Mockito.verify(bankRepository, Mockito.never()).save(ArgumentMatchers.any(Bank.class));

		Mockito.verify(accountRepository, Mockito.never()).findAll();
	}

	@Test
	void contextLoads3() {
		Mockito.when(accountRepository.findById(1L)).thenReturn(Data.createAccount001());

		Account account1 = accountService.findById(1L);
		Account account2 = accountService.findById(1L);


		Assertions.assertSame(account1, account2);

	}
}

