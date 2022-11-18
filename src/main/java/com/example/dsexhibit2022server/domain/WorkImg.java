package com.example.dsexhibit2022server.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name="work_img")
@Entity
public class WorkImg {

    @Id
    private Long workImgIdx;

    @Column(length = 500, nullable = false)
    private String imgPath;

    @JoinColumn(name = "work_idx", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Work work;
}
