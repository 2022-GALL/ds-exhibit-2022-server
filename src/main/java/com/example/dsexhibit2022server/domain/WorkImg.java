package com.example.dsexhibit2022server.domain;


import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name="work_img")
@Entity
public class WorkImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workImgIdx;

    @Column(length = 500, nullable = false)
    private String imgPath;

    @JoinColumn(name = "work_idx", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Work work;
}
