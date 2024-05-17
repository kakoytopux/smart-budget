package com.finance.smartbudget.controller;

import com.finance.smartbudget.model.Transaction;
import com.finance.smartbudget.parsers.BankStatementCSVParser;
import com.finance.smartbudget.parsers.BankStatementParser;
import com.finance.smartbudget.service.StatementAnalizierService;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Month;
import java.util.List;

@RestController
@Component
public class BankStatementAnalyserController {

    private static final BankStatementParser bankStatementParser = new BankStatementCSVParser();

    private static void collectSummary(final StatementAnalizierService statementAnalizierService) {
        System.out.println("The total for all transactions is "
                + statementAnalizierService.calculateTotalAmount());

        System.out.println("The total for transactions in January is "
                + statementAnalizierService.calculateTotalInMonth(Month.JANUARY));

        System.out.println("The total for transactions in February is "
                + statementAnalizierService.calculateTotalInMonth(Month.FEBRUARY));

        System.out.println("The total salary received is "
                + statementAnalizierService.calculateTotalForCategory("Salary"));
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST,
            headers = "Accept=application/json, application/xml, multipart/form-data")
    public ResponseEntity<?> upload(
            @ApiParam(name = "file", value = "Select the file to Upload", required = true)
            @RequestPart("file") MultipartFile file) {
        try {
            File testFile = new File("test");
            FileUtils.writeByteArrayToFile(testFile, file.getBytes());
            List<String> lines = FileUtils.readLines(testFile);
            lines.forEach(line -> System.out.println(line));
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Done", HttpStatus.OK);
    }

    public void processFile(List<String> lines) {
        final List<Transaction> bankTransactions = bankStatementParser.parseLines(lines);
        final StatementAnalizierService statementAnalizierService = new StatementAnalizierService(bankTransactions);

        collectSummary(statementAnalizierService);
    }
}
