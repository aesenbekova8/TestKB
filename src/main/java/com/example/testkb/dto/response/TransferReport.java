package com.example.testkb.dto.response;

import com.example.testkb.entity.enums.Currency;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class TransferReport {
    Map<Currency, BigDecimal> profit;
    Map<Currency, BigDecimal> transfer;
}
