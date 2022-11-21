package com.example.dsexhibit2022server.config.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean { // GenericFilterBean를 상속받아 필터 구현. 필터가 자동 등록됨

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //토큰 유효 여부 확인
        if(jwtTokenProvider.validateTokenByServlet((HttpServletRequest) request)) {
            // 유효함.
            // 토큰에서 유저정보(Authentication) 받아옴
            Authentication authentication = jwtTokenProvider.getAuthenticationByServlet((HttpServletRequest) request);
            // SecurityContext에 Authentication 객체를 저장함
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
