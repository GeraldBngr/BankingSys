package com.banking_api.Banking_Api.Repositories;

import com.banking_api.Banking_Api.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByCustomerId(Long customerId);
}
