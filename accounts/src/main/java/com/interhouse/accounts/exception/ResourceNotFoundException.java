package com.interhouse.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s 주어진 입력 데이터에 대해 결과를 찾지 못함 %s: '%s'", resourceName, fieldName, fieldValue));
    }
}
