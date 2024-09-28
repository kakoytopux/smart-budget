package com.finance.smartbudget.rest.model;

import com.finance.smartbudget.security.security_module.models.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "transactions")
public @Data class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal transactionSum;
    private String category;
    private String description;
    private Long bankId;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @ManyToOne
    private User user;
    private BigDecimal cashBackSum;
    public Transaction(BigDecimal transactionSum, String category, String description, Long bankId, LocalDateTime createdAt, User user, BigDecimal cashBackSum) {
        this.transactionSum = transactionSum;
        this.category = category;
        this.description = description;
        this.bankId = bankId;
        this.createdAt = createdAt;
        this.user = user;
        this.cashBackSum = cashBackSum;
    }

    public Transaction(LocalDateTime createdAt, BigDecimal transactionSum, String description) {
        this.transactionSum = transactionSum;
        this.createdAt = createdAt;
        this.description = description;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
