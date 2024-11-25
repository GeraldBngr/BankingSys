package com.banking_api.Banking_Api.Dtos;

import java.util.List;

public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private List<AccountSummaryDTO> accounts;

    public CustomerDTO(Long id, String name, String email, List<AccountSummaryDTO> accounts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<AccountSummaryDTO> getAccounts() {
        return accounts;
    }
}
