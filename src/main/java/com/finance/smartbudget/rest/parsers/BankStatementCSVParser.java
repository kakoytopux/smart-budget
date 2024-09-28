package com.finance.smartbudget.rest.parsers;

import com.finance.smartbudget.rest.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {

    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private Transaction parseFromCSV(final String line) {
        final String[] columns = line.split(",");
        final LocalDateTime createdAt = LocalDateTime.parse(columns[0], DATE_PATTERN);
        final BigDecimal transactionSum = BigDecimal.valueOf(Double.parseDouble(columns[1]));
        final String description = columns[2];
        return new Transaction(createdAt, transactionSum, description);
    }

    @Override
    public List<Transaction> parseLines(final List<String> lines) {
        final List<Transaction> bankTransactions = new ArrayList<>();
        for (final String line : lines) {
            bankTransactions.add(parseFromCSV(line));
        }
        return bankTransactions;
    }
}
