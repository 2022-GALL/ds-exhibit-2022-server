/*
package com.example.dsexhibit2022server.api;

import com.example.dsexhibit2022server.api.util.TestUtil;
import com.example.dsexhibit2022server.config.security.WebSecurityConfig;
import com.example.dsexhibit2022server.config.security.jwt.JwtAuthenticationFilter;
import com.example.dsexhibit2022server.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RequiredArgsConstructor
@WebMvcTest(controllers = TestController.class,
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebSecurityConfig.class})})

//        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebSecurityConfig.class, JwtAuthenticationFilter.class})})
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JwtTokenProvider jwtTokenProvider;

    @Test
    void hello() throws Exception {
//        given()

        mockMvc.perform(MockMvcRequestBuilders.get("/api/test/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("hello"))
                .andDo(print());

//        verify()
    }

////가장 베이직한 것만 남긴 코드
*/
/*

    @Test
//    @WithMockUser
    void checkTokenInfo() throws Exception {
        String jwtToken = "eyJhbGciOi어쩌구 토큰값.VzIjpbA5NX0.7Rk629fsSnUb3fB7MrNg";
        String email = "jh@email";
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        servletRequest.addHeader("X-AUTH-TOKEN", jwtToken);

        given(jwtTokenProvider.getUserPKByServlet(servletRequest)).willReturn(
                email
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/api/test/token-info")
                        .header("X-AUTH-TOKEN", jwtToken)) //.session(session)
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").value(email))  //왜 null일까....
                .andDo(print());

        verify(jwtTokenProvider).getUserPKByServlet(servletRequest);

    }
*//*




*/
/*

//
//    private MockHttpSession session;
//    public MockHttpServletRequest servletRequest;
//    @Autowired
//    private WebApplicationContext context;
    @MockBean
    private UserDetailsService userDetailsService;
    @Test
    void checkTokenInfo() throws Exception {
        String jwtToken = "eyJhbGciOiJIU어쩌구임시토큰값.CI6MTY2OTQwMDA5NX0.7Rk629fsOjEhUb3fB7MrNg";
        String email = "jh@email";
*//*

*/
/*

        //가짜 HttpServletRequest 만들기
        session = new MockHttpSession();
        session.setAttribute("X-AUTH-TOKEN", jwtToken);

        servletRequest = new MockHttpServletRequest();
        servletRequest.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(servletRequest)); //?

//        MockitoAnnotations.initMocks(this); //?
*//*
*/
/*

*//*

*/
/*
        final Map<String, String[]> params = new HashMap<>();
        params.put("X-AUTH-TOKEN", new String[] {jwtToken});
        HttpServletRequest servletRequest = TestUtil.getRequest(params);
        *//*
*/
/*

*//*

*/
/*

        Map<String, String> headers = new HashMap<>();
        headers.put("X-AUTH-TOKEN", jwtToken);
        Enumeration<String> headerNames = Collections.enumeration(headers.keySet());

        servletRequest = new MockHttpServletRequest();
        HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        when(servletRequest.getHeaderNames()).thenReturn(headerNames);

        // mock the returned value of request.getHeader(String name)
        doAnswer(new Answer<String>() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return headers.get((String) args[0]);
            }
        }).when(servletRequest).getHeader("X-AUTH-TOKEN"); //.getHeader("Content-Type");
*//*
*/
/*

*//*

*/
/*
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).addFilter( new CharacterEncodingFilter("UTF-8", true)).build();
*//*
*/
/*


        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        servletRequest.addHeader("X-AUTH-TOKEN", jwtToken);


//        System.out.println("X-AUTH-TOKEN 값 확인 : "+servletRequest.getHeader("X-AUTH-TOKEN"));//ok
//
        given(jwtTokenProvider.validateTokenByServlet((HttpServletRequest) servletRequest))
                .willReturn(true);
//        given(userDetailsService.loadUserByUsername(email)).willReturn()

        UserDetails userDetails = userDetailsService.loadUserByUsername(email); //토큰에서 회원정보 추출하는 getUserOK메서드 사용
        given(jwtTokenProvider.getAuthenticationByServlet((HttpServletRequest) servletRequest))
                .willReturn(new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));

//        ServletRequest request = mock(ServletRequest.class);
//        ServletResponse response = mock(ServletResponse.class);
//        FilterChain chain = mock(FilterChain.class);

        given(jwtTokenProvider.getUserPKByServlet(servletRequest))
                .willReturn(email);
        
//        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtTokenProvider);
//        given(jwtAuthenticationFilter.doFilter(servletRequest, response, chain))

        mockMvc.perform(MockMvcRequestBuilders.get("/api/test/token-info")
                        .header("X-AUTH-TOKEN", jwtToken)) //.session(session)
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.data").value(email))  //왜 null일까....
                .andDo(print());

        verify(jwtTokenProvider).getUserPKByServlet(servletRequest);

    }
*//*



}*/
