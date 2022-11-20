package com.example.dsexhibit2022server.application;

import com.example.dsexhibit2022server.config.security.jwt.JwtTokenProvider;
import com.example.dsexhibit2022server.dao.UserRepository;
import com.example.dsexhibit2022server.domain.User;
import com.example.dsexhibit2022server.dto.UserRequest;
import com.example.dsexhibit2022server.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    public Long addUser(UserRequest.SignUpRequest request) throws Exception {
        log.info("[SERVICE] addUser");

        //이미 가입된 유저인지 확인
        Optional<User> findUser = userRepository.findByEmail(request.getEmail());
        if(findUser.isPresent()){
            throw new Exception(); //TODO : 409로 변경하기
        }

        User newUser = request.toEntity();
        return userRepository.save(newUser).getUserIdx();
    }

    public UserResponse.LoginResponse login(UserRequest.LoginRequest request) throws Exception {
        log.info("[SERVICE] login");

        // email로 DB 확인
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if(userOptional.isEmpty()){
            throw new Exception(); //TODO : 403 으로 변경
        }

        User user = userOptional.get();

        //비밀번호 확인
        if(!user.isPassword(request.getPassword())){
            throw new Exception(); //TODO : 403 으로 변경
        }

        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRoles()); //사용자 식별용 고유값인 email과 권한단계인 role을 담은 토큰 생성
        return UserResponse.LoginResponse.builder()
                .name(user.getName())
                .token(token)
                .build();
    }
}
