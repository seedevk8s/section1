package com.interhouse.accounts.service;

import com.interhouse.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto - CustomerDto customerDto
     */
    void createAccount(CustomerDto customerDto);
}