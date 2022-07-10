package com.example.testkb.mapper;

import com.example.testkb.dto.response.AccountResponse;
import com.example.testkb.entity.Account;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public AccountResponse toAccountResponse(@NonNull Account account) {
        return AccountResponse.builder()
                .currency(account.getCurrency())
                .balance(account.getBalance())
                .build();
    }
}
