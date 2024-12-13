package com.asklepios.hospitalreservation_asklepios.SecurityConfig;

import com.asklepios.hospitalreservation_asklepios.Service.IF_UserService;
import com.asklepios.hospitalreservation_asklepios.VO.MemberVO;
import com.asklepios.hospitalreservation_asklepios.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {
  @Autowired
  PasswordEncoder passwordEncoder;
  @Autowired
  IF_UserService userservice;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    MemberVO membervo = userservice.findUser(username);
    String passwordEncoding = passwordEncoder.encode(membervo.getUser_password());
      System.out.println(membervo.toString());
//    if(membervo == null) {
//          System.out.println(membervo.toString());
//      throw new UsernameNotFoundException(username);
//    }
    System.out.println("hi");
    return User.builder()
        .username(membervo.getUser_id())
        .password(passwordEncoding)
        .roles(membervo.getUser_authority())
        .build()
        ;
  }
}
