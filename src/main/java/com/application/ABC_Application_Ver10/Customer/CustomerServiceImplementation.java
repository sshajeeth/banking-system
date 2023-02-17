package com.application.ABC_Application_Ver10.Customer;

import com.application.ABC_Application_Ver10.Others.PasswordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImplementation implements CustomerService {
    @Autowired(required = true)
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> fetchAllcustomers() {
        return null;
    }

    @Override
    public Customer getCustomer(String email) {
        return customerRepository.findByEmail(email);
    }


    @Override
    public Customer updateCustomer(Customer customer, int customerId) {
        Optional<Customer> customer1 = customerRepository.findById(customerId);
        if (customer1.isPresent()) {
            Customer updatedCustomer = customer1.get();
            updatedCustomer.setCustomerName(customer.getCustomerName());
            updatedCustomer.setAddress(customer.getAddress());
            updatedCustomer.setPhone(customer.getPhone());
            updatedCustomer.setEmail(customer.getEmail());
            //updatedCustomer.set(customer.getEmail());
            //customerRepository.save(updatedCustomer);
            return customerRepository.save(updatedCustomer);
        }


        return customer;

    }

    @Override
    public void deleteCustomer(int customer) {

    }

    @Override
    public int getTotalCustomers() {
        return customerRepository.getTotalCustomers();
    }

    @Override
    public Customer getCustomerById(int id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer.orElse(null);
    }

    @Override
    public void updatePassword(int id, String oldPassword, String newPassword) throws Exception {
        PasswordManager passwordManager = new PasswordManager();
        if(customerRepository.findById(id).isPresent()) {
            Customer customer = customerRepository.findById(id).get();
            String currentPassword = passwordManager.decrypt(customer.getPassword());
            if (currentPassword.equals(oldPassword)){
                customer.setPassword(passwordManager.encrypt(newPassword));
            }
        }
    }
}
