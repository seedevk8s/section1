package com.interhouse.accounts.service;

import com.interhouse.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto - CustomerDto customerDto
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber
     * @return
     */
    CustomerDto fetchAccount(String mobileNumber);

    /**
     *
     * @param customerDto
     * @return
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber
     * @return
     */
    boolean deleteAccount(String mobileNumber);

}
