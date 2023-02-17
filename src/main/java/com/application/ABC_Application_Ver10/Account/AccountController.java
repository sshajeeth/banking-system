package com.application.ABC_Application_Ver10.Account;

import com.application.ABC_Application_Ver10.Account.CurrentAccount.CurrentAccount;
import com.application.ABC_Application_Ver10.Account.SavingsAccount.SavingsAccount;
import com.application.ABC_Application_Ver10.Customer.Customer;
import com.application.ABC_Application_Ver10.Customer.CustomerRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired(required=true)
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;


    @PostMapping("/createsavingsaccount")
    public ResponseEntity<Account> createSavingsAccount(@RequestBody ObjectNode json){
        int customerId = Integer.parseInt(json.get("customerId").asText());
        double currentBalance = Double.parseDouble(json.get("currentBalance").asText());

        SavingsAccount account = new SavingsAccount();
        account.setCurrentBalance(currentBalance);

        if(customerRepository.findById(customerId).isPresent()){
            account.setCustomer(customerRepository.findById(customerId).get());
            return new ResponseEntity<>(accountService.createSavingsAccount(account), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/createcurrentaccount")
    public ResponseEntity<Account> createCurrentAccount(@RequestBody ObjectNode json){
        int customerId = Integer.parseInt(json.get("customerId").asText());
        double currentBalance = Double.parseDouble(json.get("currentBalance").asText());
        double overdraftAmount = Double.parseDouble(json.get("overDraftAmount").asText());

        CurrentAccount account = new CurrentAccount();
        account.setCurrentBalance(currentBalance);
        account.setOverDraftAmount(overdraftAmount);
        if(customerRepository.findById(customerId).isPresent()){
            account.setCustomer(customerRepository.findById(customerId).get());
            return new ResponseEntity<>(accountService.createCurrentAccount(account), HttpStatus.OK);

        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/fetchAccountsByCustomer/{id}")
    public ResponseEntity<List<Account>> fetchAccountsByCustomer(@PathVariable("id") int id){
        Customer customer = new Customer();
        if(customerRepository.findById(id).isPresent()){
            customer = customerRepository.findById(id).get();
            return new ResponseEntity<>(accountService.fetchAllAccountsByCustomerId(customer),HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);

    }

    // this function for is for bank staff
    @GetMapping("/fetchAllAccounts")
    public ResponseEntity<List<Account>> fetchAllAccounts(){

        return new ResponseEntity<>(accountService.fetchAllAccounts(),HttpStatus.OK);

    }

    @PutMapping("/updateOverdraft")
    public ResponseEntity<String> updateOverdraft(@RequestBody ObjectNode json){
        long accId = Long.parseLong(json.get("accId").asText());
        double amount = Double.parseDouble(json.get("amount").asText());

        accountService.updateOverdraft(accId, amount);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    @PutMapping("/updateInterest")
    public ResponseEntity<String> updateInterest(@RequestBody ObjectNode json){
        long accId = Long.parseLong(json.get("accId").asText());
        double interest = Double.parseDouble(json.get("interest").asText());

        accountService.updateInterest(accId, interest);
        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }


    // bank staff
    @PostMapping("/deleteAccount/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") long id){
        accountService.deleteAccount(id);
        return new ResponseEntity<>("The Account is Closed.", HttpStatus.OK);
    }



}
