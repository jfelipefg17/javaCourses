package org.test.springBoot.springbootTest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.test.springBoot.springbootTest.Entities.Account;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  @Query("select c from Account c where c.person=?1")
  Optional<Account> findByPerson(String person);


  //List<Account> findAll();
  //Account findById(Long id);
  //void update(Account account);
}
