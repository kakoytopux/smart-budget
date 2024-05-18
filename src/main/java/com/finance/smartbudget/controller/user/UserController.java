package com.finance.smartbudget.controller.user;

import com.finance.smartbudget.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get-my-balance")
    public Map<String, BigDecimal> getMyBalance() {
        return Map.of("my_balance", userService.calculateMyBalance());
    }

    @GetMapping("/get-my-balance-in-month/{month}")
    public Map<String, BigDecimal> getMyBalanceInMonth(@PathVariable Month month) {
        return Map.of("my_balance_in_month", userService.calculateMyBalanceByMonth(month));
    }

    @GetMapping("/get-my-balance-in-month/{category}")
    public Map<String, BigDecimal> getMyBalanceForCategory(@PathVariable String category) {
        return Map.of("my_balance_in_month", userService.calculateMyBalanceByCategory(category));
    }
}
