package com.example.testkb.entity;

import com.example.testkb.entity.enums.Currency;
import com.example.testkb.entity.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "start_balance")
    private BigDecimal startBalance = BigDecimal.ZERO;

    @Column(name = "withdrawal")
    private BigDecimal withdrawal = BigDecimal.ZERO;

    @Column(name = "deposit")
    private BigDecimal deposit = BigDecimal.ZERO;

    @NotBlank
    @Column(name = "final_balance")
    private BigDecimal finalBalance = BigDecimal.ZERO;

    @NotBlank
    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
