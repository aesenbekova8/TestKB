package com.example.testkb.repository;

import com.example.testkb.entity.Account;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByBankAndCurrency(Bank bank, Currency currency);
    List<Account> findAllByBank(Bank bank);
}
