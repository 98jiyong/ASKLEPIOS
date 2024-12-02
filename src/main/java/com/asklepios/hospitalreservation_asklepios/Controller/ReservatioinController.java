package com.asklepios.hospitalreservation_asklepios.Controller;

import com.asklepios.hospitalreservation_asklepios.Service.IF_ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReservatioinController {

  @Autowired
  IF_ReservationService reservationService;

  @ResponseBody
  @PostMapping("/findHospitalName")
  public boolean findHospitalName(@RequestParam("hospital_name") String hospitalName) {
    return reservationService.checkHospitalName(hospitalName);
  }

  @ResponseBody
  @PostMapping("/reserve")
  public String reserve(@RequestParam("hospital_name") String hospitalName){
    System.out.println(hospitalName);
    return "reservationForm";
  }

  @GetMapping("/reservationForm")
  public String reservationForm() {
    return "reservationForm";
  }

}
