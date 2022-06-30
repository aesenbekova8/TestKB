package com.example.testkb.mapper;

import com.example.testkb.dto.response.TransferResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class TransferMapper {
    public TransferResponse toTransferResponse(@NonNull String code) {
        return TransferResponse.builder()
                .code(code)
                .build();
    }
}
