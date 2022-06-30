package com.example.testkb.service.impl;

import com.example.testkb.entity.Account;
import com.example.testkb.entity.Transaction;
import com.example.testkb.repository.TransactionRepository;
import com.example.testkb.service.TransactionService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    @Transactional
    public Transaction transfer(@NonNull Account account,
                                @NonNull BigDecimal startBalance,
                                @NonNull BigDecimal debit) {
        Transaction transaction = new Transaction();
        transaction.setStartBalance(startBalance);
        transaction.setDeposit(debit);
        transaction.setFinalBalance(account.getBalance());
        transaction.setComment(String.format("A transfer was made in the amount of %s %s", debit, account.getCurrency()));
        transaction.setAccount(account);

        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public Transaction cashOutTransfer(@NonNull Account account,
                                       @NonNull BigDecimal startBalance,
                                       @NonNull BigDecimal withdrawal) {
        Transaction transaction = new Transaction();
        transaction.setStartBalance(startBalance);
        transaction.setWithdrawal(withdrawal);
        transaction.setFinalBalance(account.getBalance());
        transaction.setComment(String.format("A transfer was withdrawn of %s %s", withdrawal, account.getCurrency()));
        transaction.setAccount(account);

        return transactionRepository.save(transaction);
    }
}
