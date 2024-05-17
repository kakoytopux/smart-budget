package com.finance.smartbudget.service;

import com.finance.smartbudget.model.Transaction;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

public class StatementAnalizierService {
    private final List<Transaction> bankTransactions;

    public StatementAnalizierService(final List<Transaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public BigDecimal calculateTotalAmount() {
        BigDecimal total = BigDecimal.ZERO;
        for(final Transaction bankTransaction: bankTransactions) {
            total = total.add(bankTransaction.getTransactionSum());
        }
        return total;
    }

    public BigDecimal calculateTotalInMonth(final Month month) {
        BigDecimal total = BigDecimal.ZERO;
        for(final Transaction bankTransaction: bankTransactions) {
            if(bankTransaction.getCreatedAt().getMonth() == month) {
                total = total.add(bankTransaction.getTransactionSum());
            }
        }
        return total;
    }

    public BigDecimal calculateTotalForCategory(final String category) {
        BigDecimal total = BigDecimal.ZERO;
        for(final Transaction bankTransaction: bankTransactions) {
            if(bankTransaction.getDescription().equals(category)) {
                total =  total.add(bankTransaction.getTransactionSum());
            }
        }
        return total;
    }
}
