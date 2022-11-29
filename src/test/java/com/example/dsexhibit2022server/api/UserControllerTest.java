/*
package com.example.dsexhibit2022server.api;

import com.example.dsexhibit2022server.application.UserService;
import com.example.dsexhibit2022server.config.security.jwt.JwtTokenProvider;
import com.example.dsexhibit2022server.dto.UserRequest;
import com.example.dsexhibit2022server.dto.UserResponse;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    JwtTokenProvider jwtTokenProvider; //이건 WebSecurityConfig 에서 쓰인다고 필요하다는 에러 뱉음.. 이 파일이 항상 실행되나 봄

    @MockBean
    UserService userService;

    Gson gson = new Gson();

    @Test
    void addUser() throws Exception {

        //post로 보낼 requestBody생성 (요청dto)
        UserRequest.SignUpRequest request = new UserRequest.SignUpRequest("jh@email", "0000", "박지혜", "컴공");
        String requestBody = gson.toJson(request);

        // Mock 객체인 서비스가 할 가상 행동 정의
        given(userService.addUser(request))
                .willReturn(1L);

        mockMvc.perform(
                post("/api/users/sign-up")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(1L))
                .andDo(print());

        verify(userService).addUser(request);
    }

    @Test
    void login() throws Exception {

        //요청dto
        UserRequest.LoginRequest request = new UserRequest.LoginRequest("jh@email", "0000");
        String requestBody = gson.toJson(request);

        //응답dto
        String jwtToken = "eyJhbGciOiJIU어쩌구임시토큰값.CI6MTY2OTQwMDA5NX0.7Rk629fsOjEhUb3fB7MrNg";
        UserResponse.LoginResponse response = new UserResponse.LoginResponse("박지혜", jwtToken);

        given(userService.login(request))
                .willReturn(response);

        mockMvc.perform(
                post("/api/users/log-in")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.name").value("박지혜"))
                .andExpect(jsonPath("$.data.token").value(jwtToken))
                .andDo(print());

        verify(userService).login(request);
    }
}*/
