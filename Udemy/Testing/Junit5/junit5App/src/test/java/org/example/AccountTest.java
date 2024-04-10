package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {


  @Test
  void testGetPerson(){
    Account account = new Account();
    account.setPerson("Andres");

    Assertions.assertEquals("Andres",account.getPerson());
    //Assertions.assertTrue(account.getPerson().equals("Andres"));
  }

}