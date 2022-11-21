package com.example.dsexhibit2022server.config.global.exception.error;

import com.example.dsexhibit2022server.config.global.ErrorResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    //name()이 INVALID_PARAMETER, 그리고 httpStatus와 message가 그 네임 안에 내용으로 들어가고 get~()으로 가져올 수 있음
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included. Or some parameter is missing"), //400
    RESOURCE_NOT_FOUND_URL(HttpStatus.NOT_FOUND, "Resource not exists. Please check url"), //404
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists."), //404
    SERVICE_CONFLICT(HttpStatus.CONFLICT, "Conflict in service logic"), //409
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"), //500
    ;

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public ErrorResponse toResponse() {
        return ErrorResponse.builder()
                .status(getHttpStatus().value())
                .errorCode(name())
                .message(getMessage())
                .build();
    }
}
