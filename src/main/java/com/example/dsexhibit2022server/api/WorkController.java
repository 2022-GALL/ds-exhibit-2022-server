package com.example.dsexhibit2022server.api;


import com.example.dsexhibit2022server.application.*;
import com.example.dsexhibit2022server.config.global.JsonResponse;
import com.example.dsexhibit2022server.config.security.jwt.JwtTokenProvider;
import com.example.dsexhibit2022server.domain.*;
import com.example.dsexhibit2022server.dto.WorkRequest;
import com.example.dsexhibit2022server.dto.WorkResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("api/works")
@RequiredArgsConstructor
public class WorkController {
    private final JwtTokenProvider jwtTokenProvider;
    private final WorkService workService;
    private final WorkImgService workImgService;
    private final DepartmentService departmentService;
    private final MajorService majorService;

    @PostMapping("")
    private ResponseEntity<Object> createWork(@RequestBody WorkRequest.CreateWorkRequest request, HttpServletRequest httpServletRequest) throws Exception {
        log.info("[API] work/createWork");

        String email = jwtTokenProvider.getUserPKByServlet(httpServletRequest);
        Long workIdx = workService.createWork(request, email);

        return ResponseEntity.ok(new JsonResponse(300, "success create work", workIdx));
    }

    @GetMapping("/{workIdx}")
    private ResponseEntity<Object> getWork(@PathVariable("workIdx") Long workIdx){
        log.info("[API] work/getWork");

        //Work work = workService.findWork(workIdx);
        //String majorName = workService.getMajorByIdx(workIdx).getName();
        //String majorName = work.getMajor().getName();
        //Author author = authorService.getAuthorByIdx(workIdx);
        //Author author = work.getAuthor();
        List<String> imgPathList = workImgService.getImgPathListByIdx(workIdx);

        WorkResponse.WorkDetailResponse response = workService.getWork(workIdx,imgPathList);

        return ResponseEntity.ok(new JsonResponse(301, "success get detail info of work", response));
    }


    @DeleteMapping("/{workIdx}")
    private ResponseEntity<Object> deleteWork(@PathVariable("workIdx") Long workIdx, HttpServletRequest httpServletRequest) throws Exception {
        log.info("[API] work/deleteWork");

        workService.deleteWork(workIdx);

        return ResponseEntity.ok(new JsonResponse(303, "success delete work", null));
    }

    @GetMapping("")
    public ResponseEntity<Object> getWorkList(@RequestParam String department,
                                              @RequestParam String major,
                                              @RequestParam int year,
                                              @RequestParam int page) {
        log.info("[API] work/getWorkList");

        Department departmentEntity = departmentService.getDepartmentByCode(department);
        Major majorEntity = majorService.getMajorByCode(major);

        PageRequest pageRequest = PageRequest.of(page, 20);

        List<WorkResponse.WorkThumbnailResponse> response = workService.getWorkList(departmentEntity, majorEntity, year, pageRequest);
        return ResponseEntity.ok(new JsonResponse(200, "success get work list", response));
    }
}
