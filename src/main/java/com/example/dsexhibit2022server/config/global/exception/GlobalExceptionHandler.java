package com.example.dsexhibit2022server.config.global.exception;

import com.example.dsexhibit2022server.config.global.ErrorResponse;
import com.example.dsexhibit2022server.config.global.exception.error.CommonErrorCode;
import com.example.dsexhibit2022server.config.global.exception.error.ErrorCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    //일단 내가 만든 RestApiException, CustomException, 그리고 Hibernate Bean Validator 가 발생시키는 에러 3개를 잡도록 함

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handleRestApiException(RestApiException e) {
        return handleExceptionInternal(e.getErrorCode());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException e) {
        return handleExceptionInternal(e.getHttpStatus(), e.getMessage());
    }

    //@Valid 어노테이션으로 발생하는 에러 처리
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorCode errorCode = CommonErrorCode.INVALID_PARAMETER;
        HttpStatus httpStatus = errorCode.getHttpStatus();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

        //에러 메세지 생성
        StringBuilder builder = new StringBuilder();
        errorList.forEach(error -> {
            String field = ( (FieldError) error).getField(); //필드명 가져오기
            String msg = error.getDefaultMessage(); //기본 에러메세지 가져오기
            builder.append(field).append(" : ").append(msg).append(". ");
        });
        String message = builder.toString();
        builder.setLength(0);

        return handleExceptionInternal(httpStatus, message);
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
