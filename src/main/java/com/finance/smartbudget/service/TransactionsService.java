package com.finance.smartbudget.service;

import com.finance.smartbudget.dto.TransactionDto;
import com.finance.smartbudget.mapping.TransactionsMapper;
import com.finance.smartbudget.model.Transaction;
import com.finance.smartbudget.repository.TransactionRepository;
import com.finance.smartbudget.service.security.MyUserDataStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsService {
    private final MyUserDataStorageService myUserData;
    private final TransactionRepository transactionRepository;
    private final TransactionsMapper transactionsMapper;

    @Transactional
    public List<TransactionDto> getAllMyTransactions() {
        return transactionRepository.
            findAllByUser(myUserData.getMyUser())
            .stream()
            .map(transactionsMapper::convertEntity2Dto)
            .toList();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void addMyTransactionAndUpdateBalance(TransactionDto transactionDto) {
        transactionRepository.save(
            new Transaction(transactionDto.getTransactionSum(),
            transactionDto.getCategory(),
            transactionDto.getDescription(),
            transactionDto.getBankId(),
            transactionDto.getCreatedAt(),
            myUserData.getMyUser(),
            transactionDto.getCashBackSum())
        );

        myUserData.getMyUser()
            .setAccountBalance(myUserData
                .getMyUser()
                .getAccountBalance()
                .add(transactionDto.getTransactionSum())
        );
    }
}
