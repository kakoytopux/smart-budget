package com.finance.smartbudget.repository;

import com.finance.smartbudget.model.transactions.Expense;
import com.finance.smartbudget.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    List<Expense> findAllByUser(User user);
}
