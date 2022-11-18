package com.example.dsexhibit2022server.api;

import com.example.dsexhibit2022server.config.global.JsonResponse;
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

    @GetMapping("api/test/hello")
    public ResponseEntity<Object> hello() {
        return ResponseEntity.ok(new JsonResponse(200, "success hello", "hello"));
    }

//    @GetMapping("/api/test/tokenInfo")
//    public ResponseEntity<Object> checkTokenInfo(HttpServletRequest httpServletRequest) {
//        log.info("[API] test/checkTokenInfo");
//
//        String info = userService.checkTokenInfo(httpServletRequest);
//        return ResponseEntity.ok(new JsonResponse(200, "checkTokenInfo called", info));
//    }
}
