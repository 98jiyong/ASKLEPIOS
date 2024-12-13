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

  public SecurityConfig(CustomAccessDeniedHandler accessDeniedHandler,
                        CustomAuthenticationEntryPoint authenticationEntryPoint) {
    this.accessDeniedHandler = accessDeniedHandler;
    this.authenticationEntryPoint = authenticationEntryPoint;
  }


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf((csrfConfig) -> csrfConfig.disable())
        .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests

            .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
            .requestMatchers("Img/**", "CSS/**","JS/**", "profile_image/**",
                "/", "/home","/login",
                "/agreement","commoninfo","/userjoin","/getreview","/filter",
                "bboard_all","bboard_campaign","bboard_med",
                "bboard_health","bboard_free","/detail",
                "search").permitAll() // 요청은 허용
            .requestMatchers("/myPage","/excelDownload",
                    "/reservation","/reservationForm","/reserve").hasAnyRole("doctor","client")
            .requestMatchers("/registration").hasRole("doctor")
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
                    .failureUrl("/login")   //로그인 실패시
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
