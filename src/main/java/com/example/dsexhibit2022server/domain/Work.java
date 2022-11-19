package com.example.dsexhibit2022server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
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

    private LocalDate startDate;

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
}
