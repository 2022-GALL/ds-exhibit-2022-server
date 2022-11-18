package com.example.dsexhibit2022server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserResponse {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class LoginResponse {
        private String name;
        private String token;
    }

}
