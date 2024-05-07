package org.test.springBoot.springbootTest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.springBoot.springbootTest.Entities.Account;

import java.util.List;


public interface AccountRepository extends JpaRepository<Account, Long> {

  //List<Account> findAll();
  //Account findById(Long id);
  //void update(Account account);
}
