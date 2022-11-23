package com.example.dsexhibit2022server.config.global.exception.error;

import com.example.dsexhibit2022server.config.global.ErrorResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum WorkErrorCode implements ErrorCode{

    NOT_EXISTS_WORK(HttpStatus.NOT_FOUND, "This work does not exist."),

    // 작품 등록 시 필수값 NULL일 경우
    POST_WORK_EMPTY_NAME(HttpStatus.FORBIDDEN, "Failed to create work: author's 'name' is empty."),
    POST_WORK_EMPTY_MAJOR(HttpStatus.FORBIDDEN, "Failed to create work: major is empty."),
    POST_WORK_EMPTY_PROFILE_IMG(HttpStatus.FORBIDDEN, "Failed to create work: author's 'profile img' is empty."),
    POST_WORK_EMPTY_YEAR(HttpStatus.FORBIDDEN, "Failed to create work: 'year' is empty."),
    POST_WORK_EMPTY_WORK_IMG(HttpStatus.FORBIDDEN, "Failed to create work: 'work img' is empty."),
    POST_WORK_EMPTY_TITLE(HttpStatus.FORBIDDEN, "Failed to create work: 'title' is empty."),
    POST_WORK_EMPTY_WORK_INFO(HttpStatus.FORBIDDEN, "Failed to create work: 'work info' is empty."),
    POST_WORK_EMPTY_WORK_DETAIL_IMG(HttpStatus.FORBIDDEN, "Failed to create work: 'work detail img' is empty.");




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
