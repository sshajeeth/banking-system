package com.application.ABC_Application_Ver10.Account;

import com.application.ABC_Application_Ver10.Account.CurrentAccount.CurrentAccount;
import com.application.ABC_Application_Ver10.Account.SavingsAccount.SavingsAccount;
import com.application.ABC_Application_Ver10.Customer.Customer;
import com.application.ABC_Application_Ver10.Customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImplementation implements AccountService{

    @Autowired(required=true)
    private AccountRepository accountRepository;



    @Override
    public SavingsAccount createSavingsAccount(SavingsAccount account) {
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        account.setOpenedDate(date);


        return accountRepository.save(account);
    }

    @Override
    public CurrentAccount createCurrentAccount(CurrentAccount account) {
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        account.setOpenedDate(date);

        return accountRepository.save(account);
    }

    @Override
    public List<Account> fetchAllAccountsByCustomerId(Customer customer) {

        return accountRepository.fetchAccountsByCustomerId(customer);
    }

    @Override
    public List<Account> fetchAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteAccount(long accountId) {

    }

    @Override
    public void updateOverdraft(long id, double amount) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()){
            CurrentAccount acc = (CurrentAccount) account.get();
            acc.setOverDraftAmount(amount);
            accountRepository.save(acc);


        }
    }

    @Override
    public void updateInterest(long id, double interest) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            SavingsAccount acc = (SavingsAccount) account.get();
            acc.setInterest(interest);
            accountRepository.save(acc);
        }
    }


}
