package com.example.dsexhibit2022server.application;

import com.example.dsexhibit2022server.config.global.JsonResponse;
import com.example.dsexhibit2022server.dao.*;
import com.example.dsexhibit2022server.domain.*;
import com.example.dsexhibit2022server.dto.WorkRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkService {

    private final WorkRepository workRepository;
    private final AuthorRepository authorRepository;
    private final MajorRepository majorRepository;
    private final UserRepository userRepository;

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
        return workIdx;
    }

}
