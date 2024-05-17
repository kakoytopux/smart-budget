package com.finance.smartbudget.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Data
public class UserDto {
    private final String username;
    private final String password;
    private BigDecimal accountBalance = new BigDecimal(0);
}
