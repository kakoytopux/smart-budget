package com.finance.smartbudget.rest.service;

import com.finance.smartbudget.rest.dto.BalancePointDto;
import com.finance.smartbudget.rest.model.Transaction;
import com.finance.smartbudget.rest.repository.TransactionRepository;
import com.finance.smartbudget.rest.util.BalanceForecastUtil;
import com.finance.smartbudget.security.security_module.service.user_details.MyUserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceForecastService {
    private final TransactionRepository transactionRepository;
    private final MyUserDataService myUserData;

    public BigDecimal forecastBalanceAtDate(LocalDate expectedDate) {
        return BalanceForecastUtil.predictBalanceForDate(getBalancePointsOfMyUser(), expectedDate);
    }

    public List<BalancePointDto> forecastBalanceOnNextMonth() {
        return BalanceForecastUtil.predictBalanceForNextMonth(getBalancePointsOfMyUser());
    }

    private List<BalancePointDto> getBalancePointsOfMyUser() {
        List<Transaction> allTransactionsOfMyUser = transactionRepository.findAllByUser(myUserData.getCurrentUser());
        StatementAnalyzerService statementAnalyzer = new StatementAnalyzerService(allTransactionsOfMyUser);
        return statementAnalyzer.convertTransactionsToBalancePoints();
    }
}
