package org.example;

import org.example.exception.NoMoney;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {


  @Test
  void testGetPerson() {
    Account account = new Account();
    account.setPerson("Andres");

    Assertions.assertEquals("Andres", account.getPerson());
    //Assertions.assertTrue(account.getPerson().equals("Andres"));
  }

  @Test
  void testGetBalance() {
    Account account = new Account("Andres", new BigDecimal("100.232"));

    Assertions.assertEquals(100.232, account.getBalance().doubleValue());
  }

  @Test
  void testAccountReference() {
    Account account1 = new Account("Andres", new BigDecimal("100.232"));
    Account account2 = new Account("Andres", new BigDecimal("100.232"));
    //assertNotEquals(account2, account1);
    assertEquals(account2, account1);
  }

  @Test
  void testDebitAccount() {
    Account account = new Account("Andres", new BigDecimal("1000.234"));

    account.debit(new BigDecimal("100"));

    assertNotNull(account.getBalance());
    assertEquals(900.234, account.getBalance().doubleValue());
  }

  @Test
  void testCreditAccount() {
    Account account = new Account("Andres", new BigDecimal("1000.234"));

    account.credit(new BigDecimal("100"));

    assertNotNull(account.getBalance());
    assertEquals(1100.234, account.getBalance().doubleValue());
  }

  @Test
  void testThrows(){
    Account account = new Account("Andres", new BigDecimal("1000.234"));

    Exception exception = assertThrows(NoMoney.class, () -> {
      account.debit(new BigDecimal(1500));
    });
    String actual = exception.getMessage();
    String expected = "No Balance";

    assertEquals(expected,actual);
  }

  // assert all , if we have a lot of assert of the same type, with this it will tell you where and what is the problem, no matter how many mistakes
  //assertEquals(expected,actual, () -> "message"); if works not show message, if dont work shows message.
  //@displayName, to put what will do the test
  //@Disable, to ignore a test
  //@Tag, to tag test and then run just the test with that tag

}