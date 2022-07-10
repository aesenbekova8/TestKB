package com.example.testkb.service;

import com.example.testkb.entity.Account;
import com.example.testkb.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    Transaction transfer(Account account, BigDecimal startBalance, BigDecimal debit);
    Transaction cashOutTransfer(Account account, BigDecimal startBalance, BigDecimal withdrawal);
    List<Transaction> getAllByAccount(Account account);
}
