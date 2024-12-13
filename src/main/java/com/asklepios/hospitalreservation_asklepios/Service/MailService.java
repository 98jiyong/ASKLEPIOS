package com.asklepios.hospitalreservation_asklepios.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MailService {

  private final JavaMailSender javaMailSender;
  private static final String senderEmail= "sjjidob@gmail.com";
  private static int number;
  private static LocalDateTime time;

  // 랜덤으로 숫자 생성
  public static void createNumber() {
    number = (int)(Math.random() * (90000)) + 100000; //(int) Math.random() * (최댓값-최소값+1) + 최소값
  }
  // 유효시간을 보내주기 위한 현재시간 생성
  public static void createDate() {
    time = LocalDateTime.now().plusMinutes(10);
//    time.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
  }
  public MimeMessage CreateMail(String mail) {
    createNumber();
    createDate();
    MimeMessage message = javaMailSender.createMimeMessage();

    try {
      message.setFrom(senderEmail);
      message.setRecipients(MimeMessage.RecipientType.TO, mail);
      message.setSubject("메일 인증 코드");
      String body = "";
      body += "<div style='display:flex; justify-content:center;'>";
      body += "<div style='width:600px; padding:10px; border:2px solid lightgray;'>";
      body += "<h1 style='color:black;'>메일 인증 코드</h1><hr>";
      body += "<div style='color:black;'>";
      body += "<h3>안녕하세요. Asklepios입니다.</h3>";
      body += "<h3>고객님의 임시 비밀번호 발급을 위해 인증 코드를 발급하였습니다.</h3>";
      body += "<h3>아래의 인증 코드를 제한 시간 내에 입력해 주세요.</h3>";
      body += "</div>";
      body += "<div style='text-align:center; border-radius:10px; background: white; box-shadow: 0px 2px 5px 0px;'>";
      body += "<span style='color: #3b82f6; font-size: 40px;'>" + number + "</span>";
      body += "<h4 style='margin-top: 10px; padding-bottom: 20px;'>인증코드는 " + time.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")) + " 까지 유효합니다.</h4>";
      body += "</div>";
      body += "</div>";
      body += "</div>";
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