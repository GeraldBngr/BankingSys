package com.banking_api.Banking_Api.Services;

import com.banking_api.Banking_Api.Dtos.AccountSummaryDTO;
import com.banking_api.Banking_Api.Dtos.CustomerDTO;
import com.banking_api.Banking_Api.Entities.Customer;
import com.banking_api.Banking_Api.Repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customer -> new CustomerDTO(
                        customer.getId(),
                        customer.getName(),
                        customer.getEmail(),
                        customer.getAccounts().stream()
                                .map(account -> new AccountSummaryDTO(account.getId(), account.getBalance()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + id));

        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getAccounts().stream()
                        .map(account -> new AccountSummaryDTO(account.getId(), account.getBalance()))
                        .collect(Collectors.toList())
        );
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
