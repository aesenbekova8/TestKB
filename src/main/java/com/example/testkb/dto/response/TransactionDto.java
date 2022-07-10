package com.example.testkb.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDto {
    private BigDecimal startBalance;
    private BigDecimal withdrawal;
    private BigDecimal deposit;
    private BigDecimal finalBalance;
    private String comment;
    private LocalDateTime dateTime;
}
