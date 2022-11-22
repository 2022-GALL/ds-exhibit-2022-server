package com.example.dsexhibit2022server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class WorkRequest {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class CreateWorkRequest{
        // 작가 관련 정보
        private String name;
        private String profileImg;
        private String memberName;

        // 작품 관련 정보
        private int year;
        private String title;
        private String workInfo;
        private String workImg;
        private List<String> workDetailImg;
        private LocalDate startDate;
        private LocalDate endDate;
        private String link;

        private String major;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class GetWorkRequest{
        // 작가 관련 정보
        private String name;
        private String profileImg;
        private String memberName;

        // 작품 관련 정보
        private int year;
        private String title;
        private String workInfo;
        private String workImg;
        private List<String> workDetailImg;
        private LocalDate startDate;
        private LocalDate endDate;
        private String link;

        private String major;

    }
}
