package com.example.testkb.helper;

import com.example.testkb.entity.enums.Currency;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class CurrencyExchangeHelper {
    public static final BigDecimal SOM_TO_RUB = new BigDecimal("1.44");
    public static final BigDecimal SOM_TO_EUR = new BigDecimal("82.96");
    public static final BigDecimal SOM_TO_USD = new BigDecimal("79.50");

    public BigDecimal convertToSOM(@NonNull Map<Currency, BigDecimal> profits) {
        BigDecimal result = BigDecimal.ZERO;
        for (Map.Entry<Currency, BigDecimal> entry: profits.entrySet()) {
            result = result.add(converter(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    public BigDecimal converter(@NonNull Currency currency,
                                @NonNull BigDecimal sum) {
        switch (currency) {
            case SOM: return sum;
            case RUB: return sum.multiply(SOM_TO_RUB);
            case EUR: return sum.multiply(SOM_TO_EUR);
            case USD: return sum.multiply(SOM_TO_USD);
            default: break;
        }
        return new BigDecimal(0);
    }
}
