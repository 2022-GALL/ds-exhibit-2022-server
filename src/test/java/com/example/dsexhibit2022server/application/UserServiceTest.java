package com.example.dsexhibit2022server.application;

import com.example.dsexhibit2022server.config.security.jwt.JwtTokenProvider;
import com.example.dsexhibit2022server.dao.UserRepository;
import com.example.dsexhibit2022server.domain.User;
import com.example.dsexhibit2022server.dto.UserRequest;
import com.example.dsexhibit2022server.dto.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//TODO : 왜 고쳐진 걸까... 알 수 없어

//@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@Import({UserService.class, JwtTokenProvider.class, UserRepository.class, User.class}) //사용되는(Mock으로 사용하거나 테스트할) 클래스들 import
class UserServiceTest {

    @Mock //@MockBean
    JwtTokenProvider jwtTokenProvider;

    @Mock //@MockBean
    UserRepository userRepository;

    @InjectMocks //@Autowired
    UserService userService;

    @Test
    void addUser() throws Exception {
        //dto
        UserRequest.SignUpRequest request = new UserRequest.SignUpRequest("jh@email", "0000", "박지혜", "컴공");
        //Entity
        User newUser = request.toEntity();
        ReflectionTestUtils.setField(newUser, "userIdx", 1L); //private field 값 채우기

        //given
        when(userRepository.save(any(User.class))) //save(newUser)
                .thenReturn(newUser); //savedUser);
        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.empty());

        //test
        Long userIdx = userService.addUser(request);

        //검증
        assertEquals(userIdx, 1L);

        verify(userRepository).save(any());
        verify(userRepository).findByEmail(request.getEmail());
    }

    @Test
    void login() throws Exception {
        //dto, Entity
        UserRequest.LoginRequest request = new UserRequest.LoginRequest("jh@email", "0000");
        User user = User.builder()
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .name("박지혜")
                        .major("컴공")
                        .build();

        String jwtToken = "eyJhbGciOiJIU어쩌구임시토큰값.CI6MTY2OTQwMDA5NX0.7Rk629fsOjEhUb3fB7MrNg";

        //given
        when(userRepository.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(user));
        when(jwtTokenProvider.createToken(any(String.class), any()))
                .thenReturn(jwtToken);

        //test
        UserResponse.LoginResponse response = userService.login(request);

        //검증
        assertEquals(response.getName(), "박지혜");
        assertEquals(response.getToken(), jwtToken);

        verify(userRepository).findByEmail(request.getEmail());
        verify(jwtTokenProvider).createToken(any(String.class), any());
    }
}