package com.example.testkb.dto.request;

import com.example.testkb.entity.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransferMoneyRequest {
    @NotBlank
    private String senderINN;
    @NotBlank
    private String receiverINN;
    @NotBlank
    private Currency currency;
    @NotBlank
    private BigDecimal sum;
    @NotBlank
    private Long receiverBankId;
}
