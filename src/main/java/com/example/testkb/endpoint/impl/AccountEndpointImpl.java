package com.example.testkb.endpoint.impl;

import com.example.testkb.dto.response.AccountResponse;
import com.example.testkb.endpoint.AccountEndpoint;
import com.example.testkb.entity.Bank;
import com.example.testkb.mapper.AccountMapper;
import com.example.testkb.service.AccountService;
import com.example.testkb.service.BankService;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountEndpointImpl implements AccountEndpoint {

    private final AccountService accountService;
    private final BankService bankService;
    private final AccountMapper accountMapper;

    public AccountEndpointImpl(AccountService accountService,
                               BankService bankService,
                               AccountMapper accountMapper) {
        this.accountService = accountService;
        this.bankService = bankService;
        this.accountMapper = accountMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountResponse> getAllByBank(@NonNull Long bankId) {
        Bank bank = bankService.getById(bankId);
        return accountService.getAllByBank(bank).stream()
                .map(accountMapper::toAccountResponse)
                .collect(Collectors.toList());
    }
}
