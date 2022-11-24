package com.example.dsexhibit2022server.config.global.exception.error;

import com.example.dsexhibit2022server.config.global.ErrorResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum WorkErrorCode implements ErrorCode{

    NOT_EXIST_WORK(HttpStatus.NOT_FOUND, "This work does not exist."),

    // 작품 등록 시 필수값 NULL일 경우
    EMPTY_NAME(HttpStatus.FORBIDDEN, "Failed: author's 'name' is empty."),
    EMPTY_MAJOR(HttpStatus.FORBIDDEN, "Failed: major is empty."),
    EMPTY_PROFILE_IMG(HttpStatus.FORBIDDEN, "Failed: author's 'profile img' is empty."),
    EMPTY_YEAR(HttpStatus.FORBIDDEN, "Failed: 'year' is empty."),
    EMPTY_WORK_IMG(HttpStatus.FORBIDDEN, "Failed: 'work img' is empty."),
    EMPTY_TITLE(HttpStatus.FORBIDDEN, "Failed: 'title' is empty."),
    EMPTY_WORK_INFO(HttpStatus.FORBIDDEN, "Failed: 'work info' is empty."),
    EMPTY_WORK_DETAIL_IMG(HttpStatus.FORBIDDEN, "Failed: 'work detail img' is empty.");


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
