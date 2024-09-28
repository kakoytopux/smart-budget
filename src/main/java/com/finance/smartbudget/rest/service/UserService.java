package com.finance.smartbudget.rest.service;

import com.finance.smartbudget.rest.repository.TransactionRepository;
import com.finance.smartbudget.security.security_module.service.user_details.MyUserDataService;
import com.finance.smartbudget.security.security_module.service.user_details.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Month;

@Service
@RequiredArgsConstructor
public class UserService {
    private final TransactionRepository transactionRepository;
    private final MyUserDataService myUserDataService;

    @Transactional
    public BigDecimal calculateMyBalance() {
        StatementAnalyzerService statementAnalyzerService = getStatementAnalyzerByAllMyTransactions();

        return statementAnalyzerService.calculateTotalAmount();
    }

    @Transactional
    public BigDecimal calculateMyBalanceByMonth(Month month) {
        StatementAnalyzerService statementAnalyzerService = getStatementAnalyzerByAllMyTransactions();

        return statementAnalyzerService.calculateTotalInMonth(month);
    }

    @Transactional
    public BigDecimal calculateMyBalanceByCategory(String category) {
        StatementAnalyzerService statementAnalyzerService = getStatementAnalyzerByAllMyTransactions();

        return statementAnalyzerService.calculateTotalForCategory(category);
    }

    public StatementAnalyzerService getStatementAnalyzerByAllMyTransactions() {
        return new StatementAnalyzerService(
                transactionRepository.findAllByUser(myUserDataService.getCurrentUser())
        );
    }

}
