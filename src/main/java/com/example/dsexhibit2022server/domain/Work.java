package com.example.dsexhibit2022server.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

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
}