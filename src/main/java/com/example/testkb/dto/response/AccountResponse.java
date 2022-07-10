package com.example.testkb.dto.response;

import com.example.testkb.entity.enums.Currency;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountResponse {
    private Currency currency;
    private BigDecimal balance;
}
