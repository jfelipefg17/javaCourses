package org.test.springBoot.springbootTest;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.test.springBoot.springbootTest.Entities.Account;
import org.test.springBoot.springbootTest.Repositories.AccountRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class IntegrationJpaTest {

  @Autowired
  AccountRepository accountRepository;

  @Test
  void testFindById() {
    Optional<Account> account = accountRepository.findById(1L);

    assertTrue(account.isPresent());
    assertEquals("Andres", account.orElseThrow().getPerson());

  }

  @Test
  void testFindByPerson() {
    Optional<Account> account = accountRepository.findByPerson("Andres");

    assertTrue(account.isPresent());
    assertEquals("Andres", account.orElseThrow().getPerson());

  }

}
