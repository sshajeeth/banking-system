package com.application.ABC_Application_Ver10.Transactions;

import com.application.ABC_Application_Ver10.Account.Account;
import com.application.ABC_Application_Ver10.Account.AccountRepository;
import com.application.ABC_Application_Ver10.Transactions.Deposit.Deposit;
import com.application.ABC_Application_Ver10.Transactions.ExternalTransactions.ExternalTransaction;
import com.application.ABC_Application_Ver10.Transactions.Withdrawals.Withdrawal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImplementation implements TransactionService{
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionServiceImplementation(TransactionRepository transactionRepository,
                                            AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction createDeposit(Deposit deposit) {
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        deposit.setTransactionDate(date);
        deposit.setDescription("Deposit");
        Account account = deposit.getAccount();
        account.setCurrentBalance(account.getCurrentBalance()+deposit.getAmount());

        return transactionRepository.save(deposit);
    }

    @Override
    public Transaction createWithdrawal(Withdrawal withdrawal) {
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        withdrawal.setTransactionDate(date);
        withdrawal.setDescription("Withdrawal");
        Account account = withdrawal.getAccount();
        account.setCurrentBalance(account.getCurrentBalance()-withdrawal.getAmount());

        return transactionRepository.save(withdrawal);
    }

    @Override
    public Transaction createExternalTransaction(ExternalTransaction externalTransaction) {
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        externalTransaction.setTransactionDate(date);
        externalTransaction.setDescription("External Transfer");
        Account account = externalTransaction.getAccount();
        account.setCurrentBalance(account.getCurrentBalance()-externalTransaction.getAmount());

        return transactionRepository.save(externalTransaction);
    }



    @Override
    public List<Transaction> fetchAllTransaction(Account account) {
        return transactionRepository.fetchTransactionsByAccount(account);

    }
}
