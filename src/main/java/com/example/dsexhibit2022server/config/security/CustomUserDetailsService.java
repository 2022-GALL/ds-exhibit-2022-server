package com.example.dsexhibit2022server.config.security;

import com.example.dsexhibit2022server.config.global.exception.RestApiException;
import com.example.dsexhibit2022server.config.global.exception.error.AuthErrorCode;
import com.example.dsexhibit2022server.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserRepository userRepository;
    
    //토큰에서 유저 정보 확인한 후 정보로 User 객체를 가져올 때 쓰임
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RestApiException(AuthErrorCode.NOT_EXIST_USER)); //TODO : 이거 왜 Handler에 안잡힐까..
    }
}
