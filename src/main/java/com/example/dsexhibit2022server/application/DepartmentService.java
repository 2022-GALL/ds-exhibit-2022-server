package com.example.dsexhibit2022server.application;

import com.example.dsexhibit2022server.config.global.exception.CustomException;
import com.example.dsexhibit2022server.dao.DepartmentRepository;
import com.example.dsexhibit2022server.domain.Department;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentService {

    public final DepartmentRepository departmentRepository;

    public Department getDepartmentByCode(String code) {
        return departmentRepository.findByCode(code)
                .orElseThrow(() -> new CustomException(HttpStatus.CONFLICT, "This department code is not exist"));
    }
}
