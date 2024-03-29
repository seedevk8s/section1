package com.interhouse.accounts.service.impl;

import com.interhouse.accounts.constants.AccountsConstants;
import com.interhouse.accounts.dto.AccountsDto;
import com.interhouse.accounts.dto.CustomerDto;
import com.interhouse.accounts.entity.Accounts;
import com.interhouse.accounts.entity.Customer;
import com.interhouse.accounts.exception.CustomerAlreadyExistException;
import com.interhouse.accounts.exception.ResourceNotFoundException;
import com.interhouse.accounts.mapper.AccountsMapper;
import com.interhouse.accounts.mapper.CustomerMapper;
import com.interhouse.accounts.repository.AccountsRepository;
import com.interhouse.accounts.repository.CustomerRepository;
import com.interhouse.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     *
     * @param customer - 고객
     * @return  새로운 (은행)계좌
     */
    private Accounts createNewAccount(Customer customer) {

        Accounts newAccount = new Accounts();               //(은행)계좌
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);  //Acc Number(Account Number, 계좌번호)

        newAccount.setAccountNumber(randomAccNumber);               //계좌번호
        newAccount.setAccountType(AccountsConstants.SAVINGS);       //저축
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);     //계좌발행 은행주소
        //newAccount.setCreatedAt(LocalDateTime.now());
        //newAccount.setCreatedBy("익명의");
        return newAccount;

    }

    /**
     * @param customerDto - CustomerDto customerDto
     * 엔터티에 고객 및 계좌 저장하기
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());      //고객

        // 이미 존재하는 고객인 경우 예외발생 (다시 고객 생성 안되게 함)
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistException("해당 mobileNumber로 이미 등록된 고객입니다. "
                                                        + customerDto.getMobileNumber());
        }

        //customer.setCreatedAt(LocalDateTime.now());
        //customer.setCreatedBy("익명의");

        Customer savedCustomer = customerRepository.save(customer);     // 엔터티에 고객 저장
        accountsRepository.save(createNewAccount(savedCustomer));       // 엔터티에 앞서 고객의 계좌 저장
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDto;
    }

    /**
     * @param customerDto
     * @return
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber()
                            .toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);

            isUpdated = true;
        }

        return isUpdated;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
         Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }
}




















