package com.interhouse.accounts.controller;

import com.interhouse.accounts.constants.AccountsConstants;
import com.interhouse.accounts.dto.CustomerDto;
import com.interhouse.accounts.dto.ResponseDto;
import com.interhouse.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {

    private IAccountsService iAccountsService;

    //@GetMapping("sayHi")
    public String sayHello() {
        return "Hello KOSTA";
    }

    /**
     *
     * @param customerDto
     * @return 고객 생성 하기 (고객 및 계좌 생성하기)
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }
}




