package com.example.testkb.service;

import com.example.testkb.entity.Account;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.enums.Currency;

import java.math.BigDecimal;

public interface AccountService {
    Account create();
    void replenish(Bank bank, Currency currency, BigDecimal sum);
    void debit(Bank bank, Currency currency, BigDecimal sum);
}
