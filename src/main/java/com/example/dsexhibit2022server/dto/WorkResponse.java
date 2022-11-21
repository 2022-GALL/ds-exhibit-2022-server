package com.example.dsexhibit2022server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
