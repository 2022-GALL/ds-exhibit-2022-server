package com.example.dsexhibit2022server.domain;

import com.example.dsexhibit2022server.dto.WorkRequest;
import com.example.dsexhibit2022server.dto.WorkResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name="work")
@Entity
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workIdx;

    @Column(nullable = false)
    private int year;

    @Column(length = 500, nullable = false)
    private String workImg;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String workInfo;

    @Column(length = 500)
    private String link;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @JoinColumn(name = "department_idx", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @JoinColumn(name = "major_idx", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Major major;

    @JoinColumn(name = "user_idx", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "author_idx", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;


    public void updateWork(WorkRequest.BasicWorkRequest req){
        this.title=req.getTitle();
        this.workInfo=req.getWorkInfo();
        this.link=req.getLink();
        //this.year=req.getYear();
        this.startDate=req.getStartDate();
        this.endDate=req.getEndDate();
    }

    public void updateMajorAndDepartment(Major major){
        this.major = major;
        this.department = major.getDepartment();
    }


    /////// -- to dto -- ///////
    public WorkResponse.WorkSimpleResponse toSimpleResponse() {
        return WorkResponse.WorkSimpleResponse.builder()
                .workImg(this.workImg)
                .title(this.title)
                .build();
    }

    public WorkResponse.WorkThumbnailResponse toThumbnailResponse() {
        return WorkResponse.WorkThumbnailResponse.builder()
                .workImg(this.workImg)
                .title(this.title)
                .authorName(this.author.getName())
                .workIdx(this.workIdx)
                .build();
    }
}