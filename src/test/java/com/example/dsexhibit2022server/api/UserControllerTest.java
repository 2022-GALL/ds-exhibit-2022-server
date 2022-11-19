package com.example.dsexhibit2022server.api;

import com.example.dsexhibit2022server.application.UserService;
import com.example.dsexhibit2022server.dto.UserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
//
//@WebMvcTest(UserController.class)
//class UserControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    UserService userService;
//
//    @Test
//    void addUser() throws Exception {
//        // Mock 객체인 서비스가 할 가상 행동 정의
//        given(userService.addUser(new UserRequest.SignUpRequest("jh@email", "0000", "박지혜", "컴공")))
//                .willReturn(1L);
//
//        mockMvc.perform(
//                post()
//        )
//    }
//
////    @Test
////    void login() {
////    }
//}