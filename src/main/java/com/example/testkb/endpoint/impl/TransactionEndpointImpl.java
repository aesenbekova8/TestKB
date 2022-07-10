package com.example.testkb.endpoint.impl;

import com.example.testkb.dto.response.TransactionResponse;
import com.example.testkb.endpoint.TransactionEndpoint;
import com.example.testkb.entity.Account;
import com.example.testkb.entity.Bank;
import com.example.testkb.entity.Transaction;
import com.example.testkb.mapper.TransactionMapper;
import com.example.testkb.service.AccountService;
import com.example.testkb.service.BankService;
import com.example.testkb.service.TransactionService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionEndpointImpl implements TransactionEndpoint {

    private final TransactionService transactionService;
    private final BankService bankService;
    private final AccountService accountService;
    private final TransactionMapper transactionMapper;

    public TransactionEndpointImpl(TransactionService transactionService,
                                   BankService bankService,
                                   AccountService accountService,
                                   TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.bankService = bankService;
        this.accountService = accountService;
        this.transactionMapper = transactionMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionResponse> getAllByBankId(@NonNull Long bankId) {
        Bank bank = bankService.getById(bankId);
        List<Account> accounts = accountService.getAllByBank(bank);
        List<TransactionResponse> responseList = new ArrayList<>();

        for (Account account : accounts) {
            List<Transaction> transactions = transactionService.getAllByAccount(account);
            TransactionResponse response = new TransactionResponse();
            response.setAccountCurrency(account.getCurrency());
            response.setTransactions(transactions.stream().map(transactionMapper::toTransactionDto).collect(Collectors.toList()));
            responseList.add(response);
        }

        return responseList;
    }
}
