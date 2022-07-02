package com.example.testkb.entity;

import com.example.testkb.entity.enums.Currency;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private Set<Transaction> transactions = new HashSet<>();

    public Account(Long id, BigDecimal balance, Currency currency, Bank bank) {
        this.id = id;
        this.balance = balance;
        this.currency = currency;
        this.bank = bank;
    }

    public void replenish(BigDecimal sum) {
        setBalance(this.balance.add(sum));
    }

    public void debit(BigDecimal sum) {
        setBalance(this.balance.subtract(sum));
    }
}
