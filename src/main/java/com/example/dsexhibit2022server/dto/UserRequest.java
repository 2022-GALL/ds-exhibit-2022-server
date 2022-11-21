package com.example.dsexhibit2022server.dto;

import com.example.dsexhibit2022server.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public class UserRequest {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class SignUpRequest {
        @NotBlank
        private String email;
        @NotBlank
        private String password;
        @NotBlank
        private String name;
        @NotBlank
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
        @NotBlank
        private String email;
        @NotBlank
        private String password;
    }
}
