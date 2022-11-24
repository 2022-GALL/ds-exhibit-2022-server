package com.example.dsexhibit2022server.api;


import com.example.dsexhibit2022server.application.*;
import com.example.dsexhibit2022server.config.global.JsonResponse;
import com.example.dsexhibit2022server.config.global.exception.RestApiException;
import com.example.dsexhibit2022server.domain.*;
import com.example.dsexhibit2022server.dto.WorkRequest;
import com.example.dsexhibit2022server.dto.WorkResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.dsexhibit2022server.config.global.exception.error.WorkErrorCode.*;

@Slf4j
@RestController
@RequestMapping("api/works")
@RequiredArgsConstructor
public class WorkController {
    private final WorkService workService;
    private final WorkImgService workImgService;
    private final DepartmentService departmentService;
    private final MajorService majorService;
    private final UserService userService;
    private final AuthorService authorService;

    @PostMapping("")
    public ResponseEntity<Object> createWork(@RequestBody WorkRequest.BasicWorkRequest req,
                                             HttpServletRequest httpServletRequest) throws Exception {
        log.info("[API] work/createWork");

        // 작품 등록 전 체크
        userService.checkTokenValidation(httpServletRequest);
        checkValue(req);

        User user = userService.getUserByServlet(httpServletRequest);
        Author newAuthor = authorService.createAuthor(req);
        Major major = majorService.getMajorByCode(req.getMajor());
        Work newWork = workService.createWork(req, user, newAuthor, major);
        workImgService.createWorkImg(req, newWork);

        return ResponseEntity.ok(new JsonResponse(200, "success create work", newWork.getWorkIdx()));
    }

    @GetMapping("/{workIdx}")
    public ResponseEntity<Object> getWork(@PathVariable("workIdx") Long workIdx){
        log.info("[API] work/getWork");

        Work findWork = workService.findWork(workIdx);
        List<String> imgPathList = workImgService.getImgPathListByIdx(workIdx);
        WorkResponse.WorkDetailResponse response = workService.getWork(findWork,imgPathList);

        return ResponseEntity.ok(new JsonResponse(200, "success get detail info of work", response));
    }

    @PatchMapping("/{workIdx}")
    public ResponseEntity<Object> updateWork(@PathVariable("workIdx") Long workIdx,
                                             @RequestBody WorkRequest.BasicWorkRequest req,
                                             HttpServletRequest httpServletRequest){
        log.info("[API] work/updateWork");

        // 작품 수정 전 체크
        Work findWork = workService.findWork(workIdx);
        userService.checkTokenValidation(httpServletRequest);
        checkValue(req);

        Major major = majorService.getMajorByCode(req.getMajor());
        authorService.updateAuthor(req,findWork.getAuthor());
        workImgService.updateWorkImg(req, findWork);
        workService.updateWork(req, findWork, major);

        return ResponseEntity.ok(new JsonResponse(200, "success update work", null));
    }


    @DeleteMapping("/{workIdx}")
    public ResponseEntity<Object> deleteWork(@PathVariable("workIdx") Long workIdx, HttpServletRequest httpServletRequest) throws Exception {
        log.info("[API] work/deleteWork");

        // 작품 삭제 전 체크
        Work findWork = workService.findWork(workIdx);
        userService.checkTokenValidation(httpServletRequest);

        workImgService.deleteWorkImg(workIdx);
        Author findAuthor = findWork.getAuthor();
        workService.deleteWork(workIdx);
        authorService.deleteAuthor(findAuthor);

        return ResponseEntity.ok(new JsonResponse(200, "success delete work", null));
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

    // 작품 생성 시 필수값 확인
    public void checkValue(@RequestBody WorkRequest.BasicWorkRequest req){

        // Work
        if(req.getTitle()==null) { throw new RestApiException(EMPTY_TITLE); }
        if(req.getWorkInfo()==null) { throw new RestApiException(EMPTY_WORK_INFO); }
        if(req.getWorkImg()==null) { throw new RestApiException(EMPTY_WORK_IMG); }
        if(req.getWorkDetailImg()==null) { throw new RestApiException(EMPTY_WORK_DETAIL_IMG); }
        if(req.getMajor()==null) { throw new RestApiException(EMPTY_MAJOR); }
        if(req.getYear()==0) { throw new RestApiException(EMPTY_YEAR); }

        // Author
        if(req.getName() == null){
            throw new RestApiException(EMPTY_NAME);
        }
        if(req.getProfileImg() == null){
            throw new RestApiException(EMPTY_PROFILE_IMG);
        }

    }
}
