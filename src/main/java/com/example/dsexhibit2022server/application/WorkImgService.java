package com.example.dsexhibit2022server.application;

import com.example.dsexhibit2022server.dao.WorkImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkImgService {
    public final WorkImgRepository workImgRepository;
    public List<String> getImgPathListByIdx(Long workIdx) {
        return workImgRepository.findImgPath(workIdx);
    }
}
