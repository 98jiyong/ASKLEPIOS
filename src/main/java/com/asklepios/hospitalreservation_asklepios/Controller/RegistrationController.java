package com.asklepios.hospitalreservation_asklepios.Controller;

import com.asklepios.hospitalreservation_asklepios.Service.IF_RegistrationService;
import com.asklepios.hospitalreservation_asklepios.Service.IF_UserService;
import com.asklepios.hospitalreservation_asklepios.VO.HospitalVO;
import com.asklepios.hospitalreservation_asklepios.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {
  @Autowired
  IF_UserService userservice;

  @GetMapping("/registration")
  public String registration(Model model) {
    model.addAttribute("user",  userservice.findMember());
    return "registration/registrationForm";
  }

  @Autowired
  IF_RegistrationService registrationservice;

  @ResponseBody
  @PostMapping("/duplicateHospital")
  public boolean duplicateHospital(@RequestParam("hospital_address") String hospitalAddress,
                                  @RequestParam("hospital_name") String hospitalName) {
    return registrationservice.duplicateCheck(hospitalAddress, hospitalName);
  }

  @PostMapping("/register")
  public String register(@ModelAttribute HospitalVO hospitalvo){
    registrationservice.registerHospital(hospitalvo);
    return "redirect:/home";
  }
}
