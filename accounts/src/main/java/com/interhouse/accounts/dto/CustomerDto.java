package com.interhouse.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "이름은 값이 null이거나 빈값일수 없습니다.")
    @Size(min = 5, max = 30, message = "고객 이름 길이는 5자에서 30자 사이여야 합니다.")
    private String name;

    @NotEmpty(message = "이메일 주소는 값이 null이거나 빈값일수 없습니다")
    @Email(message = "이메일 주소는 유효한 값이어야 합니다.")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "모바일 번호는 반드시 10자여야 합니다.")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
