package com.example.dsexhibit2022server.application;

import com.example.dsexhibit2022server.dao.WorkImgRepository;
import com.example.dsexhibit2022server.domain.Work;
import com.example.dsexhibit2022server.domain.WorkImg;
import com.example.dsexhibit2022server.dto.WorkRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkImgService {
    private final WorkImgRepository workImgRepository;

    public void createWorkImg(WorkRequest.CreateWorkRequest request, Work newWork){
        List<String> imgList = request.getWorkDetailImg();
        for(String path : imgList){
            WorkImg newWorkImg = WorkImg.builder()
                    .imgPath(path)
                    .work(newWork)
                    .build();
            workImgRepository.save(newWorkImg);
        }
    }

    public void deleteWorkImg(Long workIdx) {
        List<Long> workImgList = workImgRepository.findWorkImg(workIdx);
        for(Long workImgIdx : workImgList){
            workImgRepository.deleteById(workImgIdx);
        }
    }

    public List<String> getImgPathListByIdx(Long workIdx) {
        return workImgRepository.findImgPath(workIdx);
    }
}
