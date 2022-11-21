package com.example.dsexhibit2022server.config.global.exception;

import com.example.dsexhibit2022server.config.global.ErrorResponse;
import com.example.dsexhibit2022server.config.global.exception.error.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    //일단 내가 만든 RestApiException, CustomException 만 잡도록 함

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handleRestApiException(RestApiException e) {
        return handleExceptionInternal(e.getErrorCode());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException e) {
        return handleExceptionInternal(e.getHttpStatus(), e.getMessage());
    }


    /////////// overloading handleExceptionInternal method ////////////

    protected ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorCode.toResponse());
    }

    protected ResponseEntity<Object> handleExceptionInternal(HttpStatus httpStatus, String message) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(httpStatus.value())
                .errorCode(httpStatus.getReasonPhrase()) //status 코드 빼고 설명만 가져옴
                .message(message)
                .build();

        return ResponseEntity
                .status(httpStatus)
                .body(errorResponse);
    }
}
