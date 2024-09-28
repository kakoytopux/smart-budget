package com.finance.smartbudget.parsers;

import com.finance.smartbudget.rest.model.Transaction;
import com.finance.smartbudget.rest.parsers.BankStatementCSVParser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BankStatementCSVParserTest {

    private static final String RESOURCES = "src/test/resources/teststatement.csv";
    private final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

    @Test
    void parseLinesFromCSV() throws IOException {
        List<Transaction> bankTransactions = bankStatementCSVParser.parseLines(getFileContent());
        assertThat(bankTransactions).isNotEmpty();
        assertThat(bankTransactions).extracting(Transaction::getTransactionSum).isNotEmpty();
        assertThat(bankTransactions).extracting(Transaction::getCreatedAt).isNotEmpty();
        assertThat(bankTransactions).extracting(Transaction::getDescription).isNotEmpty();
    }

    public List<String> getFileContent() throws IOException {
        final Path path = Paths.get(RESOURCES);
        return Files.readAllLines(path);
    }
}