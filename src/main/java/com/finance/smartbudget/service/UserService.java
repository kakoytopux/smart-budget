package com.finance.smartbudget.service;

import com.finance.smartbudget.dto.UserDto;
import com.finance.smartbudget.model.user.User;
import com.finance.smartbudget.repository.TransactionRepository;
import com.finance.smartbudget.repository.UserRepository;
import com.finance.smartbudget.service.security.MyUserDataStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Month;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final MyUserDataStorageService myUserData;

    @Transactional
    public void encryptPasswordAndSaveNewUser(UserDto userDto) {
        userRepository.save(new User(userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()), new BigDecimal(0)));
    }

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

    private StatementAnalyzerService getStatementAnalyzerByAllMyTransactions() {
        return new StatementAnalyzerService(
                transactionRepository.findAllByUser(myUserData.getMyUser()));
    }
}
