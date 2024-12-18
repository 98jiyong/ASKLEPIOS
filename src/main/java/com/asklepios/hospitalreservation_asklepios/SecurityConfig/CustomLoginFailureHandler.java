package com.asklepios.hospitalreservation_asklepios.SecurityConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

@Component
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException exception)
          throws IOException, ServletException {

    String errorMessage = "🚫 알 수 없는 에러가 발생했습니다. 관리자에게 문의하세요.";

    // Exception 종류에 따라 에러 메시지 설정
    if (exception instanceof BadCredentialsException) {
      errorMessage = "🚫 아이디와 비밀번호를 확인해주세요.";
    } else if (exception instanceof UsernameNotFoundException) {
      errorMessage = "🚫 존재하지 않는 계정입니다.";
    } else if (exception instanceof DisabledException) {
      errorMessage = "🚫 비활성화된 계정입니다.";
    } else if (exception instanceof CredentialsExpiredException) {
      errorMessage = "🚫 비밀번호가 만료되었습니다.";
    } else if (exception instanceof InternalAuthenticationServiceException) {
      errorMessage = "🚫 인증 서비스에 문제가 발생했습니다.";
    }

    // 에러 메시지 URL 인코딩
    errorMessage = URLEncoder.encode(errorMessage, "UTF-8");

    // 실패 페이지로 리다이렉트하면서 에러 메시지 전달
    setDefaultFailureUrl("/login?error=true&message=" + errorMessage);
    super.onAuthenticationFailure(request, response, exception);
  }
}
