package com.finance.smartbudget.parsers;

import com.finance.smartbudget.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {

    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Transaction parseFromCSV(final String line) {
        final String[] columns = line.split(",");
        final LocalDate createdAt = LocalDate.parse(columns[0], DATE_PATTERN);
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
