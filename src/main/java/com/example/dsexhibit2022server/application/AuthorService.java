package com.example.dsexhibit2022server.application;

import com.example.dsexhibit2022server.config.global.exception.RestApiException;
import com.example.dsexhibit2022server.dao.AuthorRepository;
import com.example.dsexhibit2022server.domain.Author;
import com.example.dsexhibit2022server.dto.WorkRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.dsexhibit2022server.config.global.exception.error.WorkErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {
    public final AuthorRepository authorRepository;

    public Author createAuthor(WorkRequest.CreateWorkRequest req){

        Author newAuthor = Author.builder()
                .name(req.getName())
                .profileImg(req.getProfileImg())
                .memberName(req.getMemberName())
                .build();
        authorRepository.save(newAuthor);
        return newAuthor;
    }

    public void deleteAuthor(Author findAuthor){
        authorRepository.delete(findAuthor);
    }

}
