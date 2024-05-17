package com.finance.smartbudget.repository;

import com.finance.smartbudget.model.Transaction;
import com.finance.smartbudget.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllByUser(User user);
}
