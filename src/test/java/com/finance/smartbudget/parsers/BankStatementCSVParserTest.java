package com.finance.smartbudget.parsers;

import com.finance.smartbudget.model.Transaction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BankStatementCSVParserTest {

    private static final String RESOURCES = "src/test/resources/teststatement.csv";
    private BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

    @Test
    void parseLinesFromCSV() throws IOException {
        List<Transaction> bankTransactions = bankStatementCSVParser.parseLines(getFileContent());
        assertThat(bankTransactions).isNotEmpty();
        assertThat(bankTransactions).extracting(b -> b.getTransactionSum()).isNotEmpty();
        assertThat(bankTransactions).extracting(b -> b.getCreatedAt()).isNotEmpty();
        assertThat(bankTransactions).extracting(b -> b.getDescription()).isNotEmpty();
    }

    public List<String> getFileContent() throws IOException {
        final Path path = Paths.get(RESOURCES);
        return Files.readAllLines(path);
    }
}