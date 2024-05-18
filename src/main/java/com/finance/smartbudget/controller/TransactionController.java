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
    @PostMapping("/add-my-transaction")
    public void addMyTransaction(@RequestBody TransactionDto transactionDto) {
        transactionsService.addMyTransactionAndUpdateBalance(transactionDto);
    }
    @GetMapping ("/get-all-my-transactions")
    public List<TransactionDto> getAllMyTransactions() {
        return transactionsService.getAllMyTransactions();
    }

}
