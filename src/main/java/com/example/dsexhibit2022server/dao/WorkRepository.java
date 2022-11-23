package com.example.dsexhibit2022server.dao;

import com.example.dsexhibit2022server.domain.Department;
import com.example.dsexhibit2022server.domain.Major;
import com.example.dsexhibit2022server.domain.User;
import com.example.dsexhibit2022server.domain.Work;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findWorkByUser(User user);

    List<Work> findWorkByDepartmentAndMajorAndYear(Department department, Major major, int year, Pageable pageable);

}
