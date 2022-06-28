package com.example.testkb.entity;

import com.example.testkb.entity.enums.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    public void replenish(BigDecimal sum) {
        setBalance(this.balance.add(sum));
    }

    public void debit(BigDecimal sum) {
        setBalance(this.balance.subtract(sum));
    }
}
