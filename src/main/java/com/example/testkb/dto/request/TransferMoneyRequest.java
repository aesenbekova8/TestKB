package com.example.testkb.dto.request;

import com.example.testkb.entity.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransferMoneyRequest {
    private String senderINN;
    private String receiverINN;
    private Currency currency;
    private BigDecimal sum;
    private Long receiverBankId;
}
