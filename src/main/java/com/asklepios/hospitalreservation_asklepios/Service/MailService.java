package com.asklepios.hospitalreservation_asklepios.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

  private final JavaMailSender javaMailSender;
  private static final String senderEmail= "98jiyong@gmail.com";
  private static int number;

  // 랜덤으로 숫자 생성
  public static void createNumber() {
    number = (int)(Math.random() * (90000)) + 100000; //(int) Math.random() * (최댓값-최소값+1) + 최소값
  }

  public MimeMessage CreateMail(String mail) {
    createNumber();
    MimeMessage message = javaMailSender.createMimeMessage();

    try {
      message.setFrom(senderEmail);
      message.setRecipients(MimeMessage.RecipientType.TO, mail);
      message.setSubject("메일 인증 코드");
      String body = "";
      body += "<h1>" + "메일 인증 코드" + "</h1><hr>";
      body += "<h3>" + "안녕하세요. Asklepios입니다." + "</h3>";
      body += "<h3>" + "고객님의 임시 비밀번호 발급을 위해 인증 코드를 발급하였습니다." + "</h3>";
      body += "<h3>" + "아래의 인증 코드를 제한 시간 내에 입력해 주세요." + "</h3>";
      body += "<h1><mark>" + number + "</mark></h1>";
      body += "<h3>" + "감사합니다." + "</h3>";
      message.setText(body,"UTF-8", "html");
    } catch (MessagingException e) {
      e.printStackTrace();
    }

    return message;
  }

  public int sendMail(String mail) {
    MimeMessage message = CreateMail(mail);
    javaMailSender.send(message);

    return number;
  }
}