package com.asklepios.hospitalreservation_asklepios.Controller;

import com.asklepios.hospitalreservation_asklepios.Service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MailController {

  private final MailService mailService;
  private int number; // 이메일 인증 숫자를 저장하는 변수

  // 인증 이메일 전송
  @ResponseBody
  @PostMapping("/mailSend")
  public String mailSend(@RequestParam("user_email")String user_email) {
    number = mailService.sendMail(user_email);

    String num = "" + number;

    return num;
  }

  // 인증번호 일치여부 확인
  @GetMapping("/mailCheck")
  public ResponseEntity<?> mailCheck(@RequestParam String userNumber) {

    boolean isMatch = userNumber.equals(String.valueOf(number));

    return ResponseEntity.ok(isMatch);
  }
}
