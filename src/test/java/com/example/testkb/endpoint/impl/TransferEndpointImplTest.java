package com.example.testkb.endpoint.impl;

import com.example.testkb.dto.response.TransferResponse;
import com.example.testkb.entity.enums.Currency;
import com.example.testkb.helper.TestingHelper;
import com.example.testkb.mapper.TransferMapper;
import com.example.testkb.service.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class TransferEndpointImplTest {

    @Autowired
    private TransferEndpointImpl transferEndpoint;
    @Autowired
    private BankService bankService;
    @Autowired
    private TransferService transferService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserService userService;
    @Autowired
    private TransferMapper transferMapper;

    private TestingHelper helper;

    @Test
    void transfer() {

        TransferResponse result = transferEndpoint.transfer(
                helper.getTransferRequest("123213123", "23123143323", Currency.USD, new BigDecimal(1000), 1L),
                helper.getCurrentUser());


    }

    @Test
    void getTransfer() {
    }
}