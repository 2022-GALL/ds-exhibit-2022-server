package com.example.dsexhibit2022server.application;

import com.example.dsexhibit2022server.dao.AuthorRepository;
import com.example.dsexhibit2022server.domain.Author;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {

    public final AuthorRepository authorRepository;

    public Author getAuthorByIdx(Long authorIdx){
        return authorRepository.findById(authorIdx).get();
    }


}
