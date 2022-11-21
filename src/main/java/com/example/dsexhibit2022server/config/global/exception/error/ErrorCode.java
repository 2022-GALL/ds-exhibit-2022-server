package com.example.dsexhibit2022server.config.global.exception.error;

import com.example.dsexhibit2022server.config.global.ErrorResponse;
import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String name();
    HttpStatus getHttpStatus();
    String getMessage();

    //ErrorCode 를 ErrorResponse 로 만드는 메서드
    ErrorResponse toResponse();
}
