package com.finance.smartbudget.rest.controller;

import com.finance.smartbudget.rest.dto.BalancePointDto;
import com.finance.smartbudget.rest.service.BalanceForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class ForecastController {
    private final BalanceForecastService balanceForecastService;

    /**
     Предсказать возможный баланс текущего авторизованного пользователя в определенную дату (формат dd.MM.yyyy)
     */

    @PostMapping("/forecast-balance-at-date")
    public Map<String, BigDecimal> forecastBalanceAtDate(@RequestParam("date") String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date;
        try {
            date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date format. Please use 'dd.MM.yyyy' format.");
        }
        BigDecimal expectedSum = balanceForecastService.forecastBalanceAtDate(date);
        return Map.of("expected_sum_on_date", expectedSum);
    }

    /**
     Предугадать возможный баланс пользователя (текущего авторизованного) за следующие 30 дней относительно последней транзакции
     */
    @PostMapping("/forecast-balance-on-month")
    public Map<String, List<BalancePointDto>> forecastBalanceOnMonth() {
        return Map.of("expected_sums_on_next_month",balanceForecastService.forecastBalanceOnNextMonth());
    }
}
