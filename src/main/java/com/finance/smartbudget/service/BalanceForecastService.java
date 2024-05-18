package com.finance.smartbudget.service;

import com.finance.smartbudget.dto.BalancePointDto;
import com.finance.smartbudget.model.Transaction;
import com.finance.smartbudget.repository.TransactionRepository;
import com.finance.smartbudget.service.security.MyUserDataStorageService;
import com.finance.smartbudget.util.BalanceForecastUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceForecastService {
    private final TransactionRepository transactionRepository;
    private final MyUserDataStorageService myUserData;

    public BigDecimal forecastBalanceAtDate(LocalDate expectedDate) {
        return BalanceForecastUtil.predictBalanceForDate(getBalancePointsOfMyUser(), expectedDate);
    }

    public List<BalancePointDto> forecastBalanceOnNextMonth() {
        return BalanceForecastUtil.predictBalanceForNextMonth(getBalancePointsOfMyUser());
    }

    private List<BalancePointDto> getBalancePointsOfMyUser() {
        List<Transaction> allTransactionsOfMyUser = transactionRepository.findAllByUser(myUserData.getMyUser());
        StatementAnalyzerService statementAnalyzer = new StatementAnalyzerService(allTransactionsOfMyUser);
        return statementAnalyzer.convertTransactionsToBalancePoints();
    }
}
