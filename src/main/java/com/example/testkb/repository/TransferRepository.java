package com.example.testkb.repository;

import com.example.testkb.entity.Bank;
import com.example.testkb.entity.Transfer;
import com.example.testkb.entity.enums.Currency;
import com.example.testkb.entity.enums.TransferStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
    Transfer findByStatusAndCodeAndReceiverINN(TransferStatus status, String code, String senderInn);

    @Query(value = "select sum(tr.commission) as sum from Transfer as tr where tr.currency = :currency and tr.receiverBank = :bank")
    BigDecimal getTotalSumOfCommissionsByCurrencyAndBank(Currency currency, Bank bank);

    @Query(value = "select sum(tr.sum) as sum from Transfer as tr where tr.currency = :currency and tr.receiverBank = :bank")
    BigDecimal getTotalSumOfTransfersByCurrencyAndBank(Currency currency, Bank bank);
}
