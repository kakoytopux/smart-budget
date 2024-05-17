package com.finance.smartbudget.repository;

import com.finance.smartbudget.model.transactions.Income;
import com.finance.smartbudget.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long> {
    List<Income> findAllByUser(User user);
}
