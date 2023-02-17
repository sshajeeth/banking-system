package com.application.ABC_Application_Ver10.Transactions;

import com.application.ABC_Application_Ver10.Account.Account;
import com.application.ABC_Application_Ver10.Account.AccountRepository;
import com.application.ABC_Application_Ver10.Account.SavingsAccount.SavingsAccount;
import com.application.ABC_Application_Ver10.Customer.Customer;
import com.application.ABC_Application_Ver10.Transactions.Deposit.Deposit;
import com.application.ABC_Application_Ver10.Transactions.ExternalTransactions.ExternalTransaction;
import com.application.ABC_Application_Ver10.Transactions.Withdrawals.Withdrawal;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction/")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping(path = "/deposit/{id}")
    public ResponseEntity<Transaction> deposit(@PathVariable("id") long id, @RequestBody ObjectNode json){
        double amount = Double.parseDouble(json.get("amount").asText());

        if(accountRepository.findById(id).isPresent()){
            Deposit deposit = new Deposit();
            deposit.setAmount(amount);
            deposit.setAccount(accountRepository.findById(id).get());
            return new ResponseEntity<>(transactionService.createDeposit(deposit), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/withdrawal/{id}")
    public ResponseEntity<Transaction> withdrawal(@PathVariable("id") long id, @RequestBody ObjectNode json){
        double amount = Double.parseDouble(json.get("amount").asText());

        if(accountRepository.findById(id).isPresent()){
            Withdrawal withdrawal = new Withdrawal();
            withdrawal.setAmount(amount);
            withdrawal.setAccount(accountRepository.findById(id).get());
            return new ResponseEntity<>(transactionService.createWithdrawal(withdrawal), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/externaltransaction/{id}")
    public ResponseEntity<Transaction> externalTransaction(@PathVariable("id") long id, @RequestBody ObjectNode json){
        double amount = Double.parseDouble(json.get("amount").asText());
        String branchName = json.get("branchName").asText();
        String branchCode = json.get("branchCode").asText();
        String branchAddress = json.get("branchAddress").asText();
        String postCode = json.get("postCode").asText();

        if(accountRepository.findById(id).isPresent()){
            ExternalTransaction externalTransaction = new ExternalTransaction();
            externalTransaction.setAmount(amount);
            externalTransaction.setAccount(accountRepository.findById(id).get());
            externalTransaction.setBranchName(branchName);
            externalTransaction.setBranchCode(branchCode);
            externalTransaction.setBranchAddress(branchAddress);
            externalTransaction.setPostCode(postCode);
            return new ResponseEntity<>(transactionService.createExternalTransaction(externalTransaction), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/alltransactions/{id}")
    public ResponseEntity<List<Transaction>> fetchTransactions(@PathVariable("id") long id){
        Account account = new Account();
        if(accountRepository.findById(id).isPresent()){
            account = accountRepository.findById(id).get();
            return new ResponseEntity<>(transactionService.fetchAllTransaction(account),HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
