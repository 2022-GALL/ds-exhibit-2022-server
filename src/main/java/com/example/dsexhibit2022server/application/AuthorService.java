package com.example.dsexhibit2022server.application;

import com.example.dsexhibit2022server.dao.AuthorRepository;
import com.example.dsexhibit2022server.domain.Author;
import com.example.dsexhibit2022server.dto.WorkRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorService {
    public final AuthorRepository authorRepository;

    public Author createAuthor(WorkRequest.CreateWorkRequest request){
        Author newAuthor = Author.builder()
                .name(request.getName())
                .profileImg(request.getProfileImg())
                .memberName(request.getMemberName())
                .build();
        authorRepository.save(newAuthor);
        return newAuthor;
    }

}
