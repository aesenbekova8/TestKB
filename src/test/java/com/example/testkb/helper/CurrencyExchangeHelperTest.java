package com.example.testkb.helper;

import com.example.testkb.entity.enums.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class CurrencyExchangeHelperTest {

    private CurrencyExchangeHelper currencyExchangeHelper;

    private BigDecimal SOM_TO_RUB;
    private BigDecimal SOM_TO_EUR;
    private BigDecimal SOM_TO_USD;

    @BeforeEach
    public void setup() {
        currencyExchangeHelper = new CurrencyExchangeHelper();
        SOM_TO_RUB = CurrencyExchangeHelper.SOM_TO_RUB;
        SOM_TO_EUR = CurrencyExchangeHelper.SOM_TO_EUR;
        SOM_TO_USD = CurrencyExchangeHelper.SOM_TO_USD;
    }

    @Test
    void convertRUBToSOM() {
        //arrange
        Currency somCurrency = Currency.RUB;
        BigDecimal sum = new BigDecimal("1000");
        BigDecimal expect = sum.multiply(SOM_TO_RUB);

        //act
        BigDecimal actual = currencyExchangeHelper.converter(somCurrency, sum);

        //assert
        Assertions.assertEquals(expect, actual);
    }

    @Test
    void converterEURToSOM() {
        //arrange
        Currency somCurrency = Currency.EUR;
        BigDecimal sum = new BigDecimal("1000");
        BigDecimal expect = sum.multiply(SOM_TO_EUR);

        //act
        BigDecimal actual = currencyExchangeHelper.converter(somCurrency, sum);

        //assert
        Assertions.assertEquals(expect, actual);
    }

    @Test
    void converterUSDToSOM() {
        //arrange
        Currency somCurrency = Currency.USD;
        BigDecimal sum = new BigDecimal("1000");
        BigDecimal expect = sum.multiply(SOM_TO_USD);

        //act
        BigDecimal actual = currencyExchangeHelper.converter(somCurrency, sum);

        //assert
        Assertions.assertEquals(expect, actual);
    }
}