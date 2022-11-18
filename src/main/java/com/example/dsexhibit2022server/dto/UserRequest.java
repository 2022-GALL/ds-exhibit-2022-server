package com.example.dsexhibit2022server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserRequest {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class SignUpRequest {
        private String email;
        private String password;
        private String name;
        private String major;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class LoginRequest {
        private String email;
        private String password;
    }
}
