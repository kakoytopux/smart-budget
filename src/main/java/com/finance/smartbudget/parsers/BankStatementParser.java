package com.finance.smartbudget.parsers;

import com.finance.smartbudget.model.Transaction;

import java.util.List;

public interface BankStatementParser {
    List<Transaction> parseLines(List<String> lines);
}
