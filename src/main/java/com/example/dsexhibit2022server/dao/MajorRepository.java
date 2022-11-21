package com.example.dsexhibit2022server.dao;

import com.example.dsexhibit2022server.domain.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {
    Major findByCode(String majorCode);

    Optional<Major> findOptionalByCode(String majorCode);
}
