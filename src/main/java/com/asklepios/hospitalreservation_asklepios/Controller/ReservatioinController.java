package com.asklepios.hospitalreservation_asklepios.Controller;

import com.asklepios.hospitalreservation_asklepios.Service.IF_ReservationService;
import com.asklepios.hospitalreservation_asklepios.Service.IF_UserService;
import com.asklepios.hospitalreservation_asklepios.VO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReservatioinController {
  @Autowired
  IF_UserService userservice;

  @Autowired
  IF_ReservationService reservationService;

  @GetMapping("/reservation")
  public String reservation(Model model) {
    model.addAttribute("user", userservice.findMember());
    return "reservation/reservationPlace";
  }

  @GetMapping("/reservationForm")
  public String reservationForm(Model model) {
    model.addAttribute("user", userservice.findMember());
    return "redirect:/home";
  }

  @ResponseBody
  @PostMapping("/findHospital")
  public HospitalVO findHospital(@RequestParam("hospital_name1") String hospitalName,
                                 @RequestParam("hospital_address1") String hospitalAddr) {
//    System.out.println(hospitalName);
//    System.out.println(hospitalAddr);
//    System.out.println(reservationService.findHospital(hospitalName, hospitalAddr));
    return reservationService.findHospital(hospitalName, hospitalAddr);
  }

  @ResponseBody
  @PostMapping("/findHospitalName")
  public boolean findHospitalName(@RequestParam("hospital_name") String hospitalName,
                                  @RequestParam("hospital_address") String hospitalAddr) {
    return reservationService.checkHospitalName(hospitalName, hospitalAddr);
  }

  @ResponseBody
  @PostMapping("/findDoctors")
  public List<Hospital_doctorVO> findDoctor(@RequestParam("hospital_code") String hospitalCode){
//    System.out.println(reservationService.findDoctors(hospitalCode));
    return reservationService.findDoctors(hospitalCode);
  }
  @PostMapping("/reserve")
  public String reserve(@RequestParam("hospitalCode") String hospitalCode, @RequestParam("doctorCode") String doctorCode,
                        @RequestParam("hospitalName") String hospitalName, Model model){
    MemberVO user = userservice.findMember();
//    System.out.println(user.toString());
    model.addAttribute("user", user);
//    String doctorCode ="4fb63188-1578-4a0a-ae21-5a2786cb85cf";
    String doctorName = reservationService.findDoctorName(doctorCode);
//    String hospitalCode = reservationService.findHospitalCode(hospitalName);
    model.addAttribute("hospital_name", hospitalName);
    model.addAttribute("hospital_code", hospitalCode);
    model.addAttribute("doctor_name", doctorName);
    model.addAttribute("doctor_code", doctorCode);
    model.addAttribute("user_name",user.getUser_name());
    model.addAttribute("user_id",user.getUser_id());
    model.addAttribute("user_tel",user.getUser_tel());
    return "reservation/reservationForm";
  }

  @ResponseBody
  @PostMapping("/isReserve")
  public String[] reserve1(@RequestParam("reservation_date") String reservationDate,
                           @RequestParam("hospital_code") String hospitalCode,
                           @RequestParam("doctor_code") String doctorCode){
//    System.out.println(hospitalCode);
//    System.out.println(doctorCode);
//    System.out.println(reservationDate);

    return reservationService.findAllReservationTime(hospitalCode, doctorCode, reservationDate);
  }

  @PostMapping("/reserve/submit")
  public String reservationSubmit(@ModelAttribute ReservationVO reservationvo){
//    System.out.println(reservationvo.toString());
    reservationService.addReservation(reservationvo);
    return "redirect:/home";
  }

//  @GetMapping("/reservationForm")
//  public String reservationForm(@SessionAttribute(name = "loginUserId", required = false) String userId, Model model) {
//    model.addAttribute("userId", userId);
//    return "reservationForm";
//  }

}
