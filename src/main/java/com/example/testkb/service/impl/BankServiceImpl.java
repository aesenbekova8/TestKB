package com.example.testkb.service.impl;

import com.example.testkb.entity.Bank;
import com.example.testkb.entity.Transaction;
import com.example.testkb.entity.enums.Currency;
import com.example.testkb.repository.BankRepository;
import com.example.testkb.service.AccountService;
import com.example.testkb.service.BankService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final AccountService accountService;

    public BankServiceImpl(BankRepository bankRepository,
                           AccountService accountService) {
        this.bankRepository = bankRepository;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public Bank transferMoney(@NonNull Bank bank,
                              @NonNull Currency currency,
                              @NonNull BigDecimal sum) {
        accountService.replenish(bank, currency, sum);
        return bank;
    }

    @Override
    @Transactional
    public void cashOutTransfer(Transaction transaction) {
        Bank bank = getById(transaction.getReceiverBank().getId());
        accountService.debit(bank, transaction.getCurrency(), transaction.getSum());
    }

    @Override
    @Transactional(readOnly = true)
    public Bank getById(@NonNull Long id) {
        return Optional.of(bankRepository.getReferenceById(id))
                .orElseThrow(() -> new EntityNotFoundException(format("Bank with id: %s ", id)));
    }
}
