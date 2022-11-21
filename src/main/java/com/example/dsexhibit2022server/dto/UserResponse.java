package com.example.dsexhibit2022server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class UserResponse {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class LoginResponse {
        private String name;
        private String token;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class MyInfoResponse {
        private String email;
        private String name;
        private String major;
        private List<WorkResponse.WorkSimpleResponse> workList;
    }

}
