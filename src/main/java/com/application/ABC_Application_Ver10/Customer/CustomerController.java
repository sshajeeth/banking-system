package com.application.ABC_Application_Ver10.Customer;

import com.application.ABC_Application_Ver10.Others.PasswordManager;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer/")
public class CustomerController {
    @Autowired(required = true)
    private CustomerService customerService;

    PasswordManager passwordManager = new PasswordManager();

    public CustomerController() throws Exception {
    }


    // Signup
    @PostMapping(path = "/signup")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        System.out.println(customer.getCustomerName());
        if (!customer.getCustomerName().equals("") &&
                !customer.getAddress().equals("") &&
                !customer.getEmail().equals("") &&
                !customer.getPhone().equals("") &&
                !customer.getPassword().equals("")) {

            if(customerService.getCustomer(String.valueOf(customer.getEmail()))==null) {

                //encrypting the password
                String password = customer.getPassword();

                String encryptedPassword = passwordManager.encrypt(password);
                customer.setPassword(encryptedPassword);
                System.out.println(customer.getCustomerName());
                //creating the customer in the DB

                return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<Customer> loginCustomer(@RequestBody ObjectNode json){
        String email = json.get("email").asText();
        String password = json.get("password").asText();
        System.out.println(email);
        Customer customer = null;
        if(email != null && password != null) {
            customer = customerService.getCustomer(email);

            if (customer != null) {
                System.out.println(customer.getCustomerName());
                String decryptedPassword = passwordManager.decrypt(customer.getPassword());
                System.out.println(decryptedPassword);
                if (password.equals(decryptedPassword)) {
                    return new ResponseEntity<>(customer, HttpStatus.OK);
                    //return customer;
                }
            }
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable("id") int id){
        return customerService.getCustomerById(id);
    }


    // Update Account Details
    @PutMapping("/update/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer){
        return new ResponseEntity<>(customerService.updateCustomer(customer, id), HttpStatus.OK);

    }

    // Get Total Number of Customers
    @GetMapping("/totalcustomers")
    public ResponseEntity<Integer> getTotalCustomers(){
        return new ResponseEntity<>(customerService.getTotalCustomers(),HttpStatus.OK);
    }

    // Update Password
    @PutMapping("/changepassword/{id}")
    public ResponseEntity<String> change(@PathVariable("id") int id, @RequestBody ObjectNode json) throws Exception {
        String oldPassword = json.get("oldPassword").asText();
        String newPassword = json.get("newPassword").asText();
        customerService.updatePassword(id, oldPassword, newPassword);
        return new ResponseEntity<>("updated",HttpStatus.OK);
    }
}
