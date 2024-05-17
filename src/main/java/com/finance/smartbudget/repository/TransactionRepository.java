package com.finance.smartbudget.repository;

import com.finance.smartbudget.model.Transaction;
import com.finance.smartbudget.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllByUser(User user);
}
