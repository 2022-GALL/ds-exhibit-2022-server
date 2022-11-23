package com.example.dsexhibit2022server.dto;

import com.example.dsexhibit2022server.domain.Author;
import com.example.dsexhibit2022server.domain.Work;
import com.example.dsexhibit2022server.domain.WorkImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class WorkResponse {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class WorkSimpleResponse {
        private String workImg;
        private String title;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class WorkThumbnailResponse {
        private String workImg;
        private String title;
        private String authorName;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class WorkDetailResponse {

        // 작가 관련 정보
        private String name;
        private String profileImg;
        private String memberName;
        private String email;

        // 작품 관련 정보
        private String major;
        private String title;
        private String workInfo;
        private List<String> workDetailImg;
        private int year;
        private LocalDate startDate;
        private LocalDate endDate;
        private String link;

    }

}
