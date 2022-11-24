package com.example.dsexhibit2022server.application;

import com.example.dsexhibit2022server.config.global.exception.CustomException;
import com.example.dsexhibit2022server.config.global.exception.RestApiException;
import com.example.dsexhibit2022server.dao.MajorRepository;
import com.example.dsexhibit2022server.domain.Major;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class MajorService {

    public final MajorRepository majorRepository;

    public Major getMajorByCode(String code) {
        return majorRepository.findOptionalByCode(code)
                .orElseThrow(() -> new CustomException(HttpStatus.CONFLICT, "This major code is not exist"));
    }
}
