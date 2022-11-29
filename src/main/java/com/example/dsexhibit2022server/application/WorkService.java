package com.example.dsexhibit2022server.application;

import com.example.dsexhibit2022server.config.global.exception.CustomException;
import com.example.dsexhibit2022server.config.global.exception.RestApiException;
import com.example.dsexhibit2022server.dao.*;
import com.example.dsexhibit2022server.domain.*;
import com.example.dsexhibit2022server.dto.WorkRequest;
import com.example.dsexhibit2022server.dto.WorkResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.Basic;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.dsexhibit2022server.config.global.exception.error.WorkErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkService {
    private final WorkRepository workRepository;

    public Work createWork(WorkRequest.BasicWorkRequest req,
                           User user, Author newAuthor, Major major) throws Exception {
        log.info("[SERVICE] work/createWork");

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String nowYear = sdf.format(now);

        Work newWork = Work.builder()
                .title(req.getTitle())
                .workInfo(req.getWorkInfo())
                .workImg(req.getWorkImg())
                .year(Integer.parseInt(nowYear))
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .link(req.getLink())
                .author(newAuthor)
                .major(major)
                .department(major.getDepartment())
                .user(user)
                .build();
        workRepository.save(newWork);
        return newWork;
    }

    public Work findWork(Long workIdx){
        Optional<Work> findWork = workRepository.findById(workIdx);
        if(findWork.isEmpty()){
            throw new RestApiException(NOT_EXIST_WORK);
        }
        return findWork.get();
    }

    public WorkResponse.WorkDetailResponse getWork(Work work, List<String> detailImgList) {
        log.info("[SERVICE] work/getWork");

        Author author = work.getAuthor();
        User user = work.getUser();
        String majorName = work.getMajor().getName();

        WorkResponse.WorkDetailResponse result=WorkResponse.WorkDetailResponse.builder()
                .name(author.getName())
                .profileImg(author.getProfileImg())
                .memberName(author.getMemberName())
                .email(user.getEmail())
                .major(majorName)
                .title(work.getTitle())
                .workInfo(work.getWorkInfo())
                .workDetailImg(detailImgList)
                .year(work.getYear())
                .startDate(work.getStartDate())
                .endDate(work.getEndDate())
                .link(work.getLink())
                .build();
        return result;
    }

    @Transactional
    public void updateWork(WorkRequest.BasicWorkRequest req, Work work, Major major){
        log.info("[SERVICE] work/updateWork");

        work.updateWork(req);

        if(work.getMajor() != major){
            work.updateMajorAndDepartment(major);
        }
    }

    public void deleteWork(Long workIdx) {
        log.info("[SERVICE] work/deleteWork");

        workRepository.deleteById(workIdx);
    }

    public List<WorkResponse.WorkSimpleResponse> getMyWorkList(User user) {
        log.info("[SERVICE] getMyWorkList");

        List<Work> workList = workRepository.findWorkByUser(user);
        return workList.stream().map( work -> work.toSimpleResponse() ).collect(Collectors.toList());
    }

    public List<WorkResponse.WorkThumbnailResponse> getWorkList(Department department, Major major, int year, Pageable pageable) {
        List<Work> workList;
//        if(department.equals("all") && major.equals("all")){
//            //전체 학과
//            workList = (List<Work>) workRepository.findAll(pageable);
//        }
        if(year == 0){
            //전체 연도
            workList = workRepository.findWorkByDepartmentAndMajor(department, major, pageable);
        } else{
            workList = workRepository.findWorkByDepartmentAndMajorAndYear(department, major, year, pageable);
        }
        return workList.stream().map(work -> work.toThumbnailResponse()).collect(Collectors.toList());
    }

}
