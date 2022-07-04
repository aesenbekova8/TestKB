package com.example.testkb.dto.response;

import com.example.testkb.entity.enums.Currency;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransferResponse {
    private String senderINN;
    private String receiverINN;
    private Currency currency;
    private BigDecimal sum;
    private String code;
}
