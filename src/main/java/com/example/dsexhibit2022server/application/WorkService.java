package com.example.dsexhibit2022server.application;

import com.example.dsexhibit2022server.config.global.JsonResponse;
import com.example.dsexhibit2022server.dao.*;
import com.example.dsexhibit2022server.domain.*;
import com.example.dsexhibit2022server.dto.WorkRequest;
import com.example.dsexhibit2022server.dto.WorkResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkService {

    private final AuthorRepository authorRepository;
    private final MajorRepository majorRepository;
    private final UserRepository userRepository;
    private final WorkRepository workRepository;

    private final WorkImgRepository workImgRepository;
    public Long createWork(WorkRequest.CreateWorkRequest request, String email) throws Exception {
        log.info("[SERVICE] work/createWork");

        // Author
        Author newAuthor = Author.builder()
                .name(request.getName())
                .profileImg(request.getProfileImg())
                .memberName(request.getMemberName())
                .build();
        authorRepository.save(newAuthor);

        // Major, Department
        String majorCode = request.getMajor();
        Major major = majorRepository.findByCode(majorCode);
        Department department = major.getDepartment();

        // User
        Optional<User> findUser = userRepository.findByEmail(email);

        // Work
        Work newWork = Work.builder()
                .title(request.getTitle())
                .workInfo(request.getWorkInfo())
                .workImg(request.getWorkImg())
                .year(request.getYear())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .link(request.getLink())
                .author(newAuthor)
                .major(major)
                .department(department)
                .user(findUser.get())
                .build();
        Long workIdx = workRepository.save(newWork).getWorkIdx();

        // Work Detail Img
        List<String> imgList = request.getWorkDetailImg();
        for(String path : imgList){
            WorkImg newWorkImg = WorkImg.builder()
                    .imgPath(path)
                    .work(newWork)
                    .build();
            workImgRepository.save(newWorkImg);
        }

        return workIdx;
    }

    public void deleteWork(Long workIdx) {
        log.info("[SERVICE] work/deleteWork");

        // work detail images 삭제
        List<Long> workImgList = workImgRepository.findWork(workIdx);
        for(Long workImgIdx : workImgList){
            workImgRepository.deleteById(workImgIdx);
        }

        // work 삭제
        workRepository.deleteById(workIdx);
    }

    public List<WorkResponse.WorkSimpleResponse> getMyWorkList(User user) {
        log.info("[SERVICE] getMyWorkList");

        List<Work> workList = workRepository.findWorkByUser(user);
        return workList.stream().map( work -> work.toSimpleResponse() ).collect(Collectors.toList());
    }
}
