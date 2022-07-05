package com.example.testkb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter

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

    @Column(name = "created_date_time")
    private LocalDateTime createdDateTime;
}
