package com.finance.smartbudget.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Timestamp;
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
    @JsonIgnore
    @Timestamp
    private LocalDateTime createdAt;
    @NonNull
    private String username;
    private BigDecimal cashBackSum;
}
