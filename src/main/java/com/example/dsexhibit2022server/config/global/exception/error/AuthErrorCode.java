package com.example.dsexhibit2022server.config.global.exception.error;

import com.example.dsexhibit2022server.config.global.ErrorResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "Need authorization."), //401
    GUEST_USER(HttpStatus.UNAUTHORIZED, "Request from guest user. Token is empty."), //401
    NOT_EXIST_USER(HttpStatus.UNAUTHORIZED, "User of this email is not exist. Please sign up."), //401
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Token is invalid."), //401
    FORBIDDEN(HttpStatus.FORBIDDEN, "Forbidden. Insufficient permissions."), //403
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
