package com.asklepios.hospitalreservation_asklepios.SecurityConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
                     AccessDeniedException accessDeniedException) throws IOException, ServletException {
    response.setContentType("text/html; charset=UTF-8");
    response.getWriter().write(
        "<script>" +
            "alert('접근 권한이 없습니다. 메인 페이지로 이동합니다.');" +
            "window.location.href='/asklepios';" +
            "</script>"
    );
    response.getWriter().flush();
  }
}

