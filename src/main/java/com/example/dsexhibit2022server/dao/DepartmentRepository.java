package com.example.dsexhibit2022server.dao;

import com.example.dsexhibit2022server.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByCode(String code);
}
