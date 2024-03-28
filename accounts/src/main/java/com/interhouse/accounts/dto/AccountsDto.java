package com.interhouse.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "계좌번호는 값이 null이거나 빈값일수 없습니다.")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "계좌번호는 반드시 10자여야 합니다.")
    private Long accountNumber;

    @NotEmpty(message = "계좌유형은 값이 null이거나 빈값일수 없습니다.")
    private String accountType;

    @NotEmpty(message = "은행 지점 주소는 값이 null이거나 빈값일수 없습니다.")
    private String branchAddress;
}
