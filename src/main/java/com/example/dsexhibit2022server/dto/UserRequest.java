package com.example.dsexhibit2022server.dto;

import com.example.dsexhibit2022server.domain.User;
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

        public User toEntity() {
            return User.builder()
                    .email(this.getEmail())
                    .password(this.getPassword())
                    .name(this.getName())
                    .major(this.getMajor())
                    .build();
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class LoginRequest {
        private String email;
        private String password;
    }
}
