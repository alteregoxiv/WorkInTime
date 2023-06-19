package com.example.RegisterLogin.repository;

import com.example.RegisterLogin.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Long> {

}
