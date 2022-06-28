package com.example.testkb.service.impl;

import com.example.testkb.entity.Account;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.enums.Currency;
import com.example.testkb.repository.AccountRepository;
import com.example.testkb.service.AccountService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account create() {
        return null;
    }

    @Override
    @Transactional
    public void replenish(@NonNull Bank bank,
                          @NonNull Currency currency,
                          @NonNull BigDecimal sum) {
        Account account = accountRepository.findAccountByBankAndCurrency(bank, currency);
        account.replenish(sum);
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void debit(@NonNull Bank bank,
                      @NonNull Currency currency,
                      @NonNull BigDecimal sum) {
        Account account = accountRepository.findAccountByBankAndCurrency(bank, currency);
        account.debit(sum);
        accountRepository.save(account);
    }
}
