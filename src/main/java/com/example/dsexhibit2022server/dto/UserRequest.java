package com.example.dsexhibit2022server.dto;

import com.example.dsexhibit2022server.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequest {

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class SignUpRequest {
        @NotBlank
        @Email
        private String email;

        @NotBlank
        @Size(min = 6, max = 30)
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

        //// -- method -- ////
        public boolean checkEmail(){
            int domainIndex = this.email.indexOf("@");
            String domain = this.email.substring(domainIndex + 1);
            return domain.equals("duksung.ac.kr");
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class LoginRequest {
        @NotBlank
        @Email
        private String email;

        @NotBlank
        @Size(min = 6, max = 30)
        private String password;
    }
}
