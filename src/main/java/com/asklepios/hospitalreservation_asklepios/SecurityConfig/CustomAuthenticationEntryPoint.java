package com.asklepios.hospitalreservation_asklepios.SecurityConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
                       AuthenticationException authException) throws IOException, ServletException {
    response.setContentType("text/html; charset=UTF-8");
    response.getWriter().write(
        "<script>" +
            "alert('로그인 후 이용 가능합니다.');" +
            "window.location.href='/asklepios/login';" +
            "</script>"
    );
    response.getWriter().flush();
  }

}
