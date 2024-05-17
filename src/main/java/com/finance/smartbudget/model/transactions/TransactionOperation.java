package com.finance.smartbudget.model.transactions;

import java.math.BigDecimal;

@FunctionalInterface
public interface TransactionOperation {
    BigDecimal getTransactionSum();
}
