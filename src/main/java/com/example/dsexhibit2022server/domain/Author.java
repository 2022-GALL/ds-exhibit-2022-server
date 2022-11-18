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
@Table(name="author")
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorIdx;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 500, nullable = false)
    private String profileImg;

    @Column(length = 500)
    private String memberName;
}
