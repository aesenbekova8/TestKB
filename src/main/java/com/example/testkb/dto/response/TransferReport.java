package com.example.testkb.dto.response;

import com.example.testkb.entity.enums.Currency;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class TransferReport {
    private Map<Currency, BigDecimal> profits;
    private BigDecimal totalProfitInSOM;
    private Map<Currency, BigDecimal> transfers;
}
