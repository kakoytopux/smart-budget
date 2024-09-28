package com.finance.smartbudget.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String username;
    private String password;
    private BigDecimal accountBalance = new BigDecimal(0);

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
