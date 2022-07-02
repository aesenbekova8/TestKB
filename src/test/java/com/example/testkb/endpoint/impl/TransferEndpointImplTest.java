package com.example.testkb.endpoint.impl;

import com.example.testkb.config.security.UserPrincipal;
import com.example.testkb.dto.request.TransferGetRequest;
import com.example.testkb.dto.request.TransferMoneyRequest;
import com.example.testkb.entity.*;
import com.example.testkb.entity.enums.Currency;
import com.example.testkb.entity.enums.RoleName;
import com.example.testkb.helper.TestingHelper;
import com.example.testkb.mapper.TransferMapper;
import com.example.testkb.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
class TransferEndpointImplTest {

    @Autowired
    private TransferEndpointImpl transferEndpoint;
    @MockBean
    private BankService bankService;
    @MockBean
    private TransferService transferService;
    @MockBean
    private TransactionService transactionService;
    @MockBean
    private AccountService accountService;
    @MockBean
    private UserService userService;
    @MockBean
    private TransferMapper transferMapper;

    private TestingHelper helper;

    private Bank bank;
    private User user;
    private Account account;
    private Transfer transfer;
    private TransferMoneyRequest transferMoneyRequest;
    private TransferGetRequest transferGetRequest;

    @BeforeEach
    public void setup() {
        bank = new Bank(1L, "Some bank");
        Role role = new Role(1L, RoleName.ROLE_CASHIER);
        user = new User(
                2L,
                "Cashier1",
                "$2a$04$.z4Wzip1.NiUJdm0O9DrteVps7rHa04G7xnnKagVqXzsSplV8mbU2",
                Collections.singleton(role),
                bank);
        account = new Account(3L, new BigDecimal(0), Currency.USD, bank);
        transfer = new Transfer();

        transferMoneyRequest = new TransferMoneyRequest("1234566777", "23144567", Currency.USD, new BigDecimal("1000"), bank.getId());
        transferGetRequest = new TransferGetRequest("1234566777", "1231231231");
    }

    @Test
    void transfer() {
//        transferEndpoint.transfer(transferMoneyRequest, new UserPrincipal(2L));
//
//        Mockito.verify(bankService, Mockito.times(1)).getById(bank.getId());
//        Mockito.verify(userService, Mockito.times(1)).getById(user.getId());
//        Mockito.verify(transferService, Mockito.times(1)).create(transferMoneyRequest, user, bank);
//        Mockito.verify(accountService, Mockito.times(1)).getByBankAndCurrency(bank, transferMoneyRequest.getCurrency());
//        BDDMockito.given(account.getBalance()).willReturn(new BigDecimal(0));
//        Mockito.verify(accountService, Mockito.times(1)).replenish(bank, transferMoneyRequest.getCurrency(), transferMoneyRequest.getSum());
//
//        BigDecimal startAccountBalance = account.getBalance();
//        Mockito.verify(transactionService, Mockito.times(1)).transfer(account, startAccountBalance, transferMoneyRequest.getSum());
    }

    @Test
    void getTransfer() throws NoSuchMethodException {
//        transferEndpoint.getTransfer(transferGetRequest, new UserPrincipal(2L));
//        Mockito.verify(transferService, Mockito.times(1)).getActiveByCode(transferGetRequest.getTransferCode());
//        BDDMockito.given(transfer.getReceiverBank()).willReturn(bank);
//        Mockito.verify(accountService, Mockito.times(1)).getByBankAndCurrency(bank, Currency.USD);

    }
}