package com.example.dsexhibit2022server.dao;


import com.example.dsexhibit2022server.domain.WorkImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkImgRepository extends JpaRepository<WorkImg, Long> {
    @Query(value = "select w.work_img_idx from work_img w where w.work_idx=:work_idx", nativeQuery = true)
    List<Long> findWorkImg(@Param("work_idx") Long workIdx);

    // 해당 작품의 상세 이미지 url list 반환
    @Query(value = "select wi.img_path from work_img wi where wi.work_idx=:work_idx", nativeQuery = true)
    List<String> findImgPath(@Param("work_idx") Long workIdx);
}
