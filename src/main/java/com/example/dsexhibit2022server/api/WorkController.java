package com.example.dsexhibit2022server.api;


import com.example.dsexhibit2022server.application.WorkService;
import com.example.dsexhibit2022server.config.global.JsonResponse;
import com.example.dsexhibit2022server.config.security.jwt.JwtTokenProvider;
import com.example.dsexhibit2022server.dto.WorkRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping("api/works")
@RequiredArgsConstructor
public class WorkController {
    private final JwtTokenProvider jwtTokenProvider;
    private final WorkService workService;

    @PostMapping("")
    private ResponseEntity<Object> createWork(@RequestBody WorkRequest.CreateWorkRequest request, HttpServletRequest httpServletRequest) throws Exception {
        log.info("[API] work/createWork");

        String email = jwtTokenProvider.getUserPKByServlet(httpServletRequest);
        Long workIdx = workService.createWork(request, email);

        return ResponseEntity.ok(new JsonResponse(300, "success create work", workIdx));
    }

    @DeleteMapping("/{workIdx}")
    private ResponseEntity<Object> deleteWork(@PathVariable("workIdx") Long workIdx, HttpServletRequest httpServletRequest) throws Exception {
        log.info("[API] work/deleteWork");

        workService.deleteWork(workIdx);

        return ResponseEntity.ok(new JsonResponse(303, "success delete work", null));
    }

}
