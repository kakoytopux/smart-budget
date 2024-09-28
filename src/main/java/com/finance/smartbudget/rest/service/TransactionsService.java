package com.finance.smartbudget.rest.service;

import com.finance.smartbudget.rest.dto.TransactionDto;
import com.finance.smartbudget.rest.mapping.TransactionMapper;
import com.finance.smartbudget.rest.model.Transaction;
import com.finance.smartbudget.rest.repository.TransactionRepository;
import com.finance.smartbudget.security.security_module.service.user_details.MyUserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsService {

    private final MyUserDataService myUserData;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionsMapper;

    @Transactional
    public List<TransactionDto> getAllMyTransactions() {
        return transactionRepository.
                findAllByUser(myUserData.getCurrentUser())
                .stream()
                .map(transactionsMapper::toDto)
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
                        myUserData.getCurrentUser(),
                        transactionDto.getCashBackSum())
        );

        myUserData.getCurrentUser()
                .setAccountBalance(myUserData
                        .getCurrentUser()
                        .getAccountBalance()
                        .add(transactionDto.getTransactionSum())
                );
    }
}
