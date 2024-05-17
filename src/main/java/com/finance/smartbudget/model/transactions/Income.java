package com.finance.smartbudget.model.transactions;

import com.finance.smartbudget.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public @Data class Income implements TransactionOperation {
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

    public Income(BigDecimal transactionSum, String category, String description, Long bankId, User user) {
        this.transactionSum = transactionSum;
        this.category = category;
        this.description = description;
        this.bankId = bankId;
        this.user = user;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @Override
    public BigDecimal getTransactionSum() {
        return transactionSum;
    }
}
