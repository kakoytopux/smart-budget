package com.finance.smartbudget.rest.controller.user;

import com.finance.smartbudget.rest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class UserController {
    private final UserService userService;

    /**
     Получение баланса текущего авторизованного пользователя
     */
    @GetMapping("/get-my-balance")
    public Map<String, BigDecimal> getMyBalance() {
        return Map.of("my_balance", userService.calculateMyBalance());
    }

    /**
     Получение баланса за определенных месяц
     */
    @GetMapping("/get-my-balance-in-month/{month}")
    public Map<String, BigDecimal> getMyBalanceInMonth(@PathVariable Month month) {
        return Map.of("my_balance_in_month", userService.calculateMyBalanceByMonth(month));
    }

    /**
     Получение товара только определенной категории
     */
    @GetMapping("/get-my-balance-by-category/{category}")
    public Map<String, BigDecimal> getMyBalanceForCategory(@PathVariable String category) {
        return Map.of("my_balance_by_category", userService.calculateMyBalanceByCategory(category));
    }
}
