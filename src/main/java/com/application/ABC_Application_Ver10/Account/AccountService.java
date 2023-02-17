package com.application.ABC_Application_Ver10.Account;

import com.application.ABC_Application_Ver10.Account.CurrentAccount.CurrentAccount;
import com.application.ABC_Application_Ver10.Account.SavingsAccount.SavingsAccount;
import com.application.ABC_Application_Ver10.Customer.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    // create an account
    SavingsAccount createSavingsAccount(SavingsAccount account);

    CurrentAccount createCurrentAccount(CurrentAccount account);

    // get all accounts by customer id
    List<Account> fetchAllAccountsByCustomerId(Customer customer);

    // get all accounts
    List<Account> fetchAllAccounts();

    // delete account
    void deleteAccount(long accountId);

    void updateOverdraft(long id, double amount);

    void updateInterest(long id, double interest);
}
