package com.example.dsexhibit2022server.dao;

import com.example.dsexhibit2022server.domain.Department;
import com.example.dsexhibit2022server.domain.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByCode(String code);
}
