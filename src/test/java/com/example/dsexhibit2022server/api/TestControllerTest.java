package com.example.dsexhibit2022server.api;

import com.example.dsexhibit2022server.config.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestController.class)
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JwtTokenProvider jwtTokenProvider;

    @Test
    void hello() throws Exception {
//        given()

        mockMvc.perform(MockMvcRequestBuilders.get("/api/test/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("hello"))
                .andDo(print());

//        verify()
    }

//
//    @Test
//    void checkTokenInfo() {
//    }
}