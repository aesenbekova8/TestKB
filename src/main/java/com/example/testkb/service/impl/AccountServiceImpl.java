package com.example.testkb.service.impl;

import com.example.testkb.entity.Account;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.enums.Currency;
import com.example.testkb.exception.LogicException;
import com.example.testkb.repository.AccountRepository;
import com.example.testkb.service.AccountService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
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
        Account account = getByBankAndCurrency(bank, currency);
        account.replenish(sum);
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void debit(@NonNull Bank bank,
                      @NonNull Currency currency,
                      @NonNull BigDecimal sum) {
        Account account = getByBankAndCurrency(bank, currency);

        if (account.getBalance().subtract(sum).signum() < 0) {
            throw new LogicException("There are not enough funds on the Account with id: " + account.getId());
        } else {
            account.debit(sum);
            accountRepository.save(account);
        }
    }

    @Override
    public Account getByBankAndCurrency(@NonNull Bank bank,
                                        @NonNull Currency currency) {
        return Optional.of(accountRepository.findAccountByBankAndCurrency(bank, currency))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Account with bankId: %s and currency: %s not found", bank, currency)));
    }
}
