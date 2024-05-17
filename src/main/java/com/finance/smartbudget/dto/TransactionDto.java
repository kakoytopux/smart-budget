package com.finance.smartbudget.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
public class TransactionDto {
    @NonNull
    private BigDecimal transactionSum;
    @NonNull
    private String category;
    private String description;
    @NonNull
    private Long bankId;
    private LocalDateTime createdAt;
    @NonNull
    private String username;
    private BigDecimal cashBackSum;
}
