package com.example.dsexhibit2022server.api;

import com.example.dsexhibit2022server.config.global.JsonResponse;
import com.example.dsexhibit2022server.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TestController {

    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("api/test/hello")
    public ResponseEntity<Object> hello() {
        return ResponseEntity.ok(new JsonResponse(200, "success hello", "hello"));
    }

    @GetMapping("/api/test/token-info")
    public ResponseEntity<Object> checkTokenInfo(HttpServletRequest httpServletRequest) {
        log.info("[API] test/checkTokenInfo");

        //토큰으로부터 email 가져오기
        String email = jwtTokenProvider.getUserPKByServlet(httpServletRequest);
        return ResponseEntity.ok(new JsonResponse(200, "checkTokenInfo called", email));
    }
}
