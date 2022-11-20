package com.example.dsexhibit2022server.api;

import com.example.dsexhibit2022server.application.UserService;
import com.example.dsexhibit2022server.config.global.JsonResponse;
import com.example.dsexhibit2022server.dto.UserRequest;
import com.example.dsexhibit2022server.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/api/users/sign-up")
    public ResponseEntity<Object> addUser(@RequestBody UserRequest.SignUpRequest request) throws Exception {
        log.info("[API] user/addUser");

        Long userIdx = userService.addUser(request);
        return ResponseEntity.ok(new JsonResponse(201, "success sign up", userIdx));
    }

    @PostMapping("/api/users/log-in")
    public ResponseEntity<Object> login(@RequestBody UserRequest.LoginRequest request) throws Exception {
        log.info("[API] user/login");

        UserResponse.LoginResponse response = userService.login(request);
        return ResponseEntity.ok(new JsonResponse(201, "success login", response));
    }
}
