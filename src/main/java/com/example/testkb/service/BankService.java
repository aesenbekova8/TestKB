package com.example.testkb.service;

import com.example.testkb.dto.request.TransferGetRequest;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.Transaction;
import com.example.testkb.entity.enums.Currency;

import java.math.BigDecimal;

public interface BankService {
    Bank transferMoney(Bank bank, Currency currency, BigDecimal sum);
    void cashOutTransfer(Transaction transaction);
    Bank getById(Long id);
}
