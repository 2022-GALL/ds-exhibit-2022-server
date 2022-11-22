package com.example.dsexhibit2022server.application;

import com.example.dsexhibit2022server.dao.*;
import com.example.dsexhibit2022server.domain.*;
import com.example.dsexhibit2022server.dto.WorkRequest;
import com.example.dsexhibit2022server.dto.WorkResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkService {
    private final WorkRepository workRepository;

    public Work createWork(WorkRequest.CreateWorkRequest req,
                           User user, Author newAuthor, Major major) throws Exception {
        log.info("[SERVICE] work/createWork");

        Work newWork = Work.builder()
                .title(req.getTitle())
                .workInfo(req.getWorkInfo())
                .workImg(req.getWorkImg())
                .year(req.getYear())
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .link(req.getLink())
                .author(newAuthor)
                .major(major)
                .department(major.getDepartment())
                .user(user)
                .build();

        return newWork;
    }


    public WorkResponse.WorkDetailResponse getWork(Long workIdx, List<String> detailImgList) {
        log.info("[SERVICE] work/getWork");

        Work work = workRepository.findById(workIdx).get();
        Author author = work.getAuthor();
        String majorName = work.getMajor().getName();

        WorkResponse.WorkDetailResponse result=WorkResponse.WorkDetailResponse.builder()
                .name(author.getName())
                .profileImg(author.getProfileImg())
                .memberName(author.getMemberName())
                .major(majorName)
                .title(work.getTitle())
                .workInfo(work.getWorkInfo())
                .workImg(work.getWorkImg())
                .workDetailImg(detailImgList)
                .year(work.getYear())
                .startDate(work.getStartDate())
                .endDate(work.getEndDate())
                .link(work.getLink())
                .build();
        return result;
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

        List<Work> workList = workRepository.findWorkByDepartmentAndMajorAndYear(department, major, year, pageable);
        return workList.stream().map(work -> work.toThumbnailResponse()).collect(Collectors.toList());
    }


    public Author getAuthorByWork(Long workIdx) {
        return workRepository.findById(workIdx).get().getAuthor();
    }
}
