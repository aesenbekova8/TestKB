package com.example.testkb.service;

import com.example.testkb.entity.Account;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.enums.Currency;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account create();
    void replenish(Bank bank, Currency currency, BigDecimal sum);
    void debit(Bank bank, Currency currency, BigDecimal sum);
    Account getByBankAndCurrency(Bank bank, Currency currency);
    List<Account> getAllByBank(Bank bank);
}
