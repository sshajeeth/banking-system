package com.application.ABC_Application_Ver10.Transactions;

import com.application.ABC_Application_Ver10.Account.Account;
import com.application.ABC_Application_Ver10.Transactions.Deposit.Deposit;
import com.application.ABC_Application_Ver10.Transactions.ExternalTransactions.ExternalTransaction;
import com.application.ABC_Application_Ver10.Transactions.Withdrawals.Withdrawal;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TransactionService {
    // create deposit
    Transaction createDeposit(Deposit deposit);

    // create withdrawal
    Transaction createWithdrawal(Withdrawal withdrawal);

    // create a external transaction
    Transaction createExternalTransaction(ExternalTransaction externalTransaction);

    // fetch all transactions
    List<Transaction> fetchAllTransaction(Account account);
}
