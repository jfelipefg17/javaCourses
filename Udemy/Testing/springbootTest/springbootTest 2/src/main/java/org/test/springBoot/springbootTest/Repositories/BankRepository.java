package org.test.springBoot.springbootTest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.springBoot.springbootTest.Entities.Bank;

import java.util.List;


public interface BankRepository extends JpaRepository<Bank, Long> {
//  List<Bank> findAll();
//
//  Bank findById(Long id);
//
//  void update(Bank bank);
}
