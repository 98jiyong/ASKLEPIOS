package com.asklepios.hospitalreservation_asklepios.SecurityConfig;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {
  @Bean
  AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

//  @Bean
//  public CustomAccessDeniedHandler accessDeniedHandler() {
//    return new CustomAccessDeniedHandler();
//  }

  private final CustomAccessDeniedHandler accessDeniedHandler;
  private final CustomAuthenticationEntryPoint authenticationEntryPoint;
  private final CustomLoginFailureHandler loginFailureHandler;

  public SecurityConfig(CustomAccessDeniedHandler accessDeniedHandler,
                        CustomAuthenticationEntryPoint authenticationEntryPoint,
                        CustomLoginFailureHandler loginFailureHandler) {
    this.accessDeniedHandler = accessDeniedHandler;
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.loginFailureHandler = loginFailureHandler;
  }


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf((csrfConfig) -> csrfConfig.disable())
        .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests

            .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
            .requestMatchers("Img/**", "CSS/**","JS/**", "profile_image/**",
                "/", "/home","/login","/findId","/resultId","/findPw","findEmail","/resultPw","mailSend","mailCheck",
                "/agreement","/commoninfo","/doctorinfo","/userjoin","/getreview","/filter","/insertedID","/hospitalList",
                "/bboard_all","/bboard_campaign","/bboard_med","/doctorreservationstatus","/acceptreservation","/cancelreservation","/verify_password_mypage",
                "/bboard_health","/bboard_free","/detail", "/updateUserInfo",
                "/search").permitAll() // 요청은 허용
                .requestMatchers("/reservation","/reservationForm","/reserve").hasRole("client")
            .requestMatchers("/registration").hasRole("doctor")
            .requestMatchers("/myPage","/excelDownload").hasAnyRole("doctor","client")
            .anyRequest().authenticated()
        )
        .exceptionHandling(exception -> exception
                        .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint)

        )

        .formLogin((formLogin) ->
                formLogin
                    .loginPage("/login")  //로그인 페이지
                    .defaultSuccessUrl("/")   //로그인 성공시
                    .loginProcessingUrl("/loginProc")
                    .usernameParameter("user_id")   // 입력한 ID
                    .passwordParameter("user_password")   //입력한 PW
                    .failureHandler(loginFailureHandler)
        )

        .logout((logout)->logout
                  .logoutSuccessUrl("/")  //로그아웃
                  .logoutUrl("/logout")
                  .invalidateHttpSession(true)   //전체 세션 삭제
        );
    return http.build();
  }
//  @Override
//  public void configure(WebSecurity web) throws Exception{
//    web.ignoring().antMatchers("/static/JS/**","/static/CSS/**","/static/Img/**", "/static/profile_image/**","/static/ExcelTemplate/**");
//  }
//  @Override
//  public void configure(AuthenticationManagerBuilder auth) throws Exception{
//    auth.userDetailsService(myUserDetailService);
//  }

}
