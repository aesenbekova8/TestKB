package com.example.testkb.helper;

import com.example.testkb.config.security.UserPrincipal;
import com.example.testkb.dto.request.TransferMoneyRequest;
import com.example.testkb.entity.enums.Currency;

import java.math.BigDecimal;

public class TestingHelper {

    public TransferMoneyRequest getTransferRequest(String senderINN,
                                                   String receiverINN,
                                                   Currency currency,
                                                   BigDecimal sum,
                                                   Long receiverBankId) {
        return new TransferMoneyRequest(
                senderINN,
                receiverINN,
                currency,
                sum,
                receiverBankId);
    }

    public UserPrincipal getCurrentUser() {
        return new UserPrincipal(1L);
    }
}
