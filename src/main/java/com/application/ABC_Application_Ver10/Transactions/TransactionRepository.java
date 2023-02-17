package com.application.ABC_Application_Ver10.Transactions;

import com.application.ABC_Application_Ver10.Account.Account;
import com.application.ABC_Application_Ver10.Customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t FROM Transaction t WHERE t.account = :account")
    List<Transaction> fetchTransactionsByAccount(@Param("account") Account account);
}
