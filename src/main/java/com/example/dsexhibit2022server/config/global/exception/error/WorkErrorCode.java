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
    EMPTY_NAME(HttpStatus.BAD_REQUEST, "Failed: author's 'name' is empty."),
    EMPTY_MAJOR(HttpStatus.BAD_REQUEST, "Failed: major is empty."),
    EMPTY_PROFILE_IMG(HttpStatus.BAD_REQUEST, "Failed: author's 'profile img' is empty."),
    EMPTY_YEAR(HttpStatus.BAD_REQUEST, "Failed: 'year' is empty."),
    EMPTY_WORK_IMG(HttpStatus.BAD_REQUEST, "Failed: 'work img' is empty."),
    EMPTY_TITLE(HttpStatus.BAD_REQUEST, "Failed: 'title' is empty."),
    EMPTY_WORK_INFO(HttpStatus.BAD_REQUEST, "Failed: 'work info' is empty."),
    EMPTY_WORK_DETAIL_IMG(HttpStatus.BAD_REQUEST, "Failed: 'work detail img' is empty."),
    INVALID_PROJECT_DURATION(HttpStatus.BAD_REQUEST, "Failed: Both 'startDate' and 'endDate' must be filled or empty."),
    INVALID_DATE_FORMAT(HttpStatus.BAD_REQUEST, "Failed: The date format is invalid."),
    INVALID_DURATION_FORMAT(HttpStatus.BAD_REQUEST, "Failed: 'startDate' must be before 'endDate'");

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
