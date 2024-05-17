package com.finance.smartbudget.controller;

import com.finance.smartbudget.dto.TransactionDto;
import com.finance.smartbudget.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionsService transactionsService;
    @PostMapping("/add-transaction")
    public void addTransaction(@RequestBody TransactionDto transactionDto) {
        transactionsService.addNewTransaction(transactionDto);
    }
    @GetMapping ("/get-all-my-transactions")
    public List<TransactionDto> getAllTransactions() {
        return transactionsService.getAllMyUserTransactions();
    }
}
