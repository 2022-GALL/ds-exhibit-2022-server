package com.example.dsexhibit2022server.dao;


import com.example.dsexhibit2022server.domain.WorkImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkImgRepository extends JpaRepository<WorkImg, Long> {
    @Query(value = "select w.work_img_idx from work_img w where w.work_idx=:work_idx", nativeQuery = true)
    List<Long> findWork(@Param("work_idx") Long workIdx);
}
