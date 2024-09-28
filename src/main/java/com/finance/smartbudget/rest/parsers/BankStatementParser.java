package com.finance.smartbudget.rest.parsers;

import com.finance.smartbudget.rest.model.Transaction;

import java.util.List;

public interface BankStatementParser {
    List<Transaction> parseLines(List<String> lines);
}
