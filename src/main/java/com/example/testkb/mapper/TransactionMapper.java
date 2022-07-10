package com.example.testkb.mapper;

import com.example.testkb.dto.response.TransactionDto;
import com.example.testkb.dto.response.TransactionResponse;
import com.example.testkb.entity.Transaction;
import com.example.testkb.entity.enums.Currency;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionMapper {

    public TransactionDto toTransactionDto(@NonNull Transaction transaction) {
        return TransactionDto.builder()
                .startBalance(transaction.getStartBalance())
                .withdrawal(transaction.getWithdrawal())
                .deposit(transaction.getDeposit())
                .finalBalance(transaction.getFinalBalance())
                .comment(transaction.getComment())
                .dateTime(transaction.getCreatedDateTime())
                .build();
    }

    public TransactionResponse toTransactionResponse(@NonNull Currency currency,
                                                     List<TransactionDto> transactions) {
        return TransactionResponse.builder()
                .accountCurrency(currency)
                .transactions(transactions)
                .build();
    }
}
