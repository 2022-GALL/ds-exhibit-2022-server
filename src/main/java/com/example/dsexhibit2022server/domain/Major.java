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
@Table(name="major")
@Entity
public class Major {

    @Id
    private Long majorIdx;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 30, nullable = false)
    private String code;

    @JoinColumn(name = "department_idx", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;
}
