package com.finance.smartbudget.rest.repository;

import com.finance.smartbudget.rest.model.Transaction;
import com.finance.smartbudget.security.security_module.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllByUser(User user);
}
