package com.banking_api.Banking_Api.Services;

import com.banking_api.Banking_Api.Entities.Customer;
import com.banking_api.Banking_Api.Repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Fetch all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Fetch a specific customer by ID
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));
    }
    // Add a new customer
    public Customer addCustomer(Customer customer) {
        // Check if the email is already in use
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists: " + customer.getEmail());
        }
        return customerRepository.save(customer);
    }
    // Delete a customer by ID
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with ID: " + id);
        }
        customerRepository.deleteById(id);
    }


}
