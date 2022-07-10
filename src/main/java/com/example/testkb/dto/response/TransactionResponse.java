package com.example.testkb.dto.response;

import com.example.testkb.entity.enums.Currency;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionResponse {
    private Currency accountCurrency;
    private List<TransactionDto> transactions;
}
