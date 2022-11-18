package com.example.dsexhibit2022server.api;

import com.example.dsexhibit2022server.application.UserService;
import com.example.dsexhibit2022server.config.global.JsonResponse;
import com.example.dsexhibit2022server.dto.UserRequest;
import com.example.dsexhibit2022server.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/api/users/sign-up")
    public ResponseEntity<Object> addUser(HttpServletRequest servletRequest,
                                          @RequestBody UserRequest.SignUpRequest request) throws Exception {
        log.info("[API] user/addUser");

        Long userIdx = userService.addUser(servletRequest, request);
        return ResponseEntity.ok(new JsonResponse(201, "success sign up", userIdx));
    }

    @GetMapping("/api/users/log-in")
    public ResponseEntity<Object> login(HttpServletRequest servletRequest,
                                          @RequestBody UserRequest.LoginRequest request) throws Exception {
        log.info("[API] user/login");

        UserResponse.LoginResponse response = userService.login(request);
        return ResponseEntity.ok(new JsonResponse(201, "success login", response));
    }
}
