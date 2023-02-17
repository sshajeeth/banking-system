package com.application.ABC_Application_Ver10.Customer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    // create a customer
    Customer createCustomer(Customer customer);

    // get all customers
    List<Customer> fetchAllcustomers();

    // find customer by email
    Customer getCustomer(String email);

    // find customer by ID
   /* Object getCustomerByID(int id);*/
    // update customer details
    Customer updateCustomer(Customer customer, int customerId);

    // delete customer
    void deleteCustomer(int customer);

    // get total customers count
    int getTotalCustomers();

    Customer getCustomerById(int id);

    void updatePassword(int id, String oldPassword, String newPassword) throws Exception;
}
