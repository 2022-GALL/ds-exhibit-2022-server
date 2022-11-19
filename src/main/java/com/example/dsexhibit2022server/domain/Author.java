package com.example.dsexhibit2022server.domain;


import lombok.*;

import javax.persistence.*;

@Builder
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
