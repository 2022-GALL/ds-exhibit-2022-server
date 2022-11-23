package com.example.dsexhibit2022server.application;

import com.example.dsexhibit2022server.config.global.exception.RestApiException;
import com.example.dsexhibit2022server.config.global.exception.error.AuthErrorCode;
import com.example.dsexhibit2022server.config.global.exception.error.CommonErrorCode;
import com.example.dsexhibit2022server.config.security.jwt.JwtTokenProvider;
import com.example.dsexhibit2022server.dao.UserRepository;
import com.example.dsexhibit2022server.domain.User;
import com.example.dsexhibit2022server.dto.UserRequest;
import com.example.dsexhibit2022server.dto.UserResponse;
import com.example.dsexhibit2022server.dto.WorkResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    public Long addUser(UserRequest.SignUpRequest request) {
        log.info("[SERVICE] addUser");

        //이미 가입된 유저인지 확인
        Optional<User> findUser = userRepository.findByEmail(request.getEmail());
        if(findUser.isPresent()){
            throw new RestApiException(AuthErrorCode.ALREADY_EXIST_USER);
        }

        User newUser = request.toEntity();
        return userRepository.save(newUser).getUserIdx();
    }

    public UserResponse.LoginResponse login(UserRequest.LoginRequest request) {
        log.info("[SERVICE] login");

        // email로 DB 확인
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if(userOptional.isEmpty()){
            throw new RestApiException(AuthErrorCode.NOT_EXIST_USER);
        }

        User user = userOptional.get();

        //비밀번호 확인
        if(!user.isPassword(request.getPassword())){
            throw new RestApiException(AuthErrorCode.LOGIN_FAILED);
        }

        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRoles()); //사용자 식별용 고유값인 email과 권한단계인 role을 담은 토큰 생성
        return UserResponse.LoginResponse.builder()
                .name(user.getName())
                .token(token)
                .build();
    }

    public UserResponse.MyInfoResponse getMyInfo(User user, List<WorkResponse.WorkSimpleResponse> workList) {
        log.info("[SERVICE] getMyInfo");
        return user.toMyInfoResponse(workList);
    }



    //////// -- method -- ///////
    public User getUserByServlet(HttpServletRequest servletRequest) {
        String email = jwtTokenProvider.getUserPKByServlet(servletRequest);
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()){
            throw new RestApiException(AuthErrorCode.NOT_EXIST_USER);
        }

        return userOptional.get();
    }

    public void checkTokenValidation(HttpServletRequest servletRequest){
        if(!jwtTokenProvider.validateTokenByServlet(servletRequest)){
            throw new RestApiException(AuthErrorCode.INVALID_TOKEN);
        }
    }
}
