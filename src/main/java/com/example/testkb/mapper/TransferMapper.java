package com.example.testkb.mapper;

import com.example.testkb.dto.response.TransferResponse;
import com.example.testkb.entity.Transaction;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class TransferMapper {
    public TransferResponse toTransferResponse(@NonNull Transaction transaction) {
        return TransferResponse.builder()
                .code(transaction.getCode())
                .build();
    }
}
