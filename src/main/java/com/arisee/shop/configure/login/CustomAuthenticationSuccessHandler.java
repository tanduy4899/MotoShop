package com.arisee.shop.configure.login;

import com.arisee.shop.domain.user.User;
import com.arisee.shop.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final UserService userService;
    private final ObjectMapper objectMapper;
    public CustomAuthenticationSuccessHandler(UserService userService, ObjectMapper objectMapper){
        this.userService = userService;
        this.objectMapper = objectMapper;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {


        httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        httpServletResponse.setContentType("application/json; charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication.getAuthorities()));
        httpServletResponse.setContentType("application/json; charset=UTF-8");



    }
}
