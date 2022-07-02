package com.example.testkb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "shifts")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cashier_id")
    private User cashier;

    @ManyToOne
    @JoinColumn(name = "cash_desk_id")
    private CashDesk cashDesk;

    @JoinColumn(name = "start_time")
    private LocalDateTime startTime;

    @JoinColumn(name = "end_time")
    private LocalDateTime endTime;
}
