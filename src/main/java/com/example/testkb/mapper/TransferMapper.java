package com.example.testkb.mapper;

import com.example.testkb.dto.response.TransferResponse;
import com.example.testkb.entity.Transfer;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class TransferMapper {
    public TransferResponse toTransferResponse(@NonNull Transfer transfer) {
        return TransferResponse.builder()
                .senderINN(transfer.getSenderINN())
                .receiverINN(transfer.getReceiverINN())
                .currency(transfer.getCurrency())
                .sum(transfer.getSum())
                .commission(transfer.getCommission())
                .code(transfer.getCode())
                .build();
    }
}
