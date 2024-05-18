package com.finance.smartbudget.service;

import com.finance.smartbudget.dto.BalancePointDto;
import com.finance.smartbudget.model.Transaction;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class StatementAnalyzerService {
    private final List<Transaction> bankTransactions;

    public BigDecimal calculateTotalAmount() {
        BigDecimal total = BigDecimal.ZERO;
        for (final Transaction bankTransaction : bankTransactions) {
            total = total.add(subtractCashBaskFromSum(bankTransaction.getTransactionSum(),bankTransaction.getCashBackSum()));
        }
        return total;
    }

    public BigDecimal calculateTotalInMonth(final Month month) {
        BigDecimal total = BigDecimal.ZERO;
        for (final Transaction bankTransaction : bankTransactions) {
            if (bankTransaction.getCreatedAt().getMonth() == month) {
                total = total.add(subtractCashBaskFromSum(bankTransaction.getTransactionSum(),bankTransaction.getCashBackSum()));
            }
        }
        return total;
    }

    public BigDecimal calculateTotalForCategory(final String category) {
        BigDecimal total = BigDecimal.ZERO;
        for (final Transaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDescription().equals(category)) {
                total = total.add(subtractCashBaskFromSum(bankTransaction.getTransactionSum(),bankTransaction.getCashBackSum()));
            }
        }
        return total;
    }
    public BigDecimal subtractCashBaskFromSum(BigDecimal transactionSum,BigDecimal cashBackSum) {
        return transactionSum.subtract(cashBackSum);
    }
    public List<BalancePointDto> convertTransactionsToBalancePoints() {
        List<BalancePointDto> balancePoints = new ArrayList<>();
        BigDecimal prevUserBalance = BigDecimal.ZERO;
        for (Transaction transaction : bankTransactions) {
            prevUserBalance = prevUserBalance.add(subtractCashBaskFromSum(transaction.getTransactionSum(),transaction.getCashBackSum()));
            balancePoints.add(
                new BalancePointDto(
                    prevUserBalance,
                    transaction.getCreatedAt())
            );
        }
        return balancePoints;
    }
}
