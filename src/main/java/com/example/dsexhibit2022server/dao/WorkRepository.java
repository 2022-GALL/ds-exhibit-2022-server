package com.example.dsexhibit2022server.dao;

import com.example.dsexhibit2022server.domain.User;
import com.example.dsexhibit2022server.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findWorkByUser(User user);
}
