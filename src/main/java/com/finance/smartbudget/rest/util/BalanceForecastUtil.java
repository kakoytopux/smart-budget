package com.finance.smartbudget.rest.util;

import com.finance.smartbudget.rest.dto.BalancePointDto;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BalanceForecastUtil {
    public static BigDecimal predictBalanceForDate(List<BalancePointDto> balancePoints, LocalDate targetDate) {
        SimpleRegression regression = getSimpleRegression(balancePoints);
        double targetX = targetDate.toEpochDay();
        double predictedSum = regression.predict(targetX);
        return BigDecimal.valueOf(predictedSum);
    }

    private static SimpleRegression getSimpleRegression(List<BalancePointDto> balancePoints) {
        SimpleRegression regression = new SimpleRegression();
        for (BalancePointDto point : balancePoints) {
            double x = point.getDate().toLocalDate().toEpochDay();
            double y = point.getSum().doubleValue();
            regression.addData(x, y);
        }
        return regression;
    }

    public static List<BalancePointDto> predictBalanceForNextMonth(List<BalancePointDto> balancePoints) {
        if (balancePoints == null || balancePoints.isEmpty()) {
            throw new IllegalArgumentException("Balance points list cannot be null or empty");
        }
        balancePoints.sort(Comparator.comparing(BalancePointDto::getDate));
        LocalDateTime lastDate = balancePoints.get(balancePoints.size() - 1).getDate();
        SimpleRegression regression = getSimpleRegression(balancePoints);
        List<BalancePointDto> forecastedBalances = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            LocalDate targetDate = LocalDate.from(lastDate.plusDays(i));
            double targetX = targetDate.toEpochDay();
            double predictedSum = regression.predict(targetX);
            forecastedBalances.add(new BalancePointDto(BigDecimal.valueOf(predictedSum), targetDate.atStartOfDay()));
        }
        return forecastedBalances;
    }
}
