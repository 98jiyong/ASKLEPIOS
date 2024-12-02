package com.asklepios.hospitalreservation_asklepios.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavbarController {

    @GetMapping("/list")
    public String hospitalList() {
        return "hospitalList";
    }

    @GetMapping("/reservation")
    public String reservation() {
        return "reservationPlace";
    }

//    @GetMapping("/board")
//    public String noticeBoard() {
//        return "noticeBoard";
//    }

    @GetMapping("/vaccine")
    public String vaccinationInfo() {
        return "vaccinationInfo";
    }
}
