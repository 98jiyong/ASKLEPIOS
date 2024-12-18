package com.asklepios.hospitalreservation_asklepios.Controller;

import com.asklepios.hospitalreservation_asklepios.Service.IF_ReservationService;
import com.asklepios.hospitalreservation_asklepios.Service.IF_UserService;
import com.asklepios.hospitalreservation_asklepios.Util.Profile_ImageUtil;
import com.asklepios.hospitalreservation_asklepios.VO.DoctorVO;
import com.asklepios.hospitalreservation_asklepios.VO.MemberVO;
import com.asklepios.hospitalreservation_asklepios.VO.ReservationStatusVO;
import com.asklepios.hospitalreservation_asklepios.VO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class MyPageController {

    @Autowired
    IF_UserService userService;

    @Autowired
    IF_ReservationService reservationService;

    @Autowired
    Profile_ImageUtil profileImageUtil;

//    @Value("${upload.file.path}")
//    private String filePath;

    @GetMapping("/myPage")
    public String myPage(Model model) {
        MemberVO member = userService.findMember();
        System.out.println("0");
        System.out.println(member.toString());
        String id = member.getUser_id();
        System.out.println(id);
        UserVO user=userService.printOneInfo(id);
        System.out.println("0.1");
        if (user == null) {
            System.out.println("1");
            return "redirect:/login";
        } else if(user.getUser_authority().equals("client")) {
            System.out.println("2");
            user.divideEngName();
            user.divideAddr();
            user.divideEmail();
            user.divideTel();
            System.out.println("3");
            String filePath = "profile_image/" + user.getUser_image();
            // Reservation 불러오기
            List<ReservationStatusVO> reservationStatusVOList = reservationService.findAllReservation(user.getUser_id());
//            List<ReservationStatusVO> reservationDoctorStatusVOList = reservationService.findAllDoctorReservation(user_id);
            model.addAttribute("filePath", filePath);
            model.addAttribute("user", user);
            model.addAttribute("reservationStatusVOList", reservationStatusVOList);
//            model.addAttribute("reservationDoctorStatusVOList", reservationDoctorStatusVOList);
            return "myPage/myPageClient";
        }
        else {
            // UserVO 불러오기
//            UserVO userVO = userService.printOneInfo(user_id);
            user.divideEngName();
            user.divideAddr();
            user.divideEmail();
            user.divideTel();
            String filePath = "profile_image/" + user.getUser_image();
            // DoctorVO 불러오기
            System.out.println(user.getUser_id());
            DoctorVO doctorVO = userService.printOneDoctorInfo(user.getUser_id());
            System.out.println(doctorVO);
            // Reservation 불러오기
//            List<ReservationStatusVO> reservationStatusVOList = reservationService.findAllReservation(user_id);
//            List<ReservationStatusVO> reservationDoctorStatusVOList = reservationService.findAllDoctorReservation(user_id);
            model.addAttribute("filePath", filePath);
            model.addAttribute("user", user);
            model.addAttribute("doctorVO", doctorVO);
//            model.addAttribute("reservationStatusVOList", reservationStatusVOList);
//            model.addAttribute("reservationDoctorStatusVOList", reservationDoctorStatusVOList);
            return "myPage/myPageDoctor";
        }
    }

    @ResponseBody
    @GetMapping("/doctorreservationstatus")
    public List<ReservationStatusVO> doctorReservationStatus(@RequestParam("user_id") String user_id) {
        return reservationService.findAllDoctorReservation(user_id);
    }

    @ResponseBody
    @GetMapping("/userreservationstatus")
    public List<ReservationStatusVO> userReservationStatus(@RequestParam("user_id") String user_id) {
        return reservationService.findAllReservation(user_id);
    }

    @ResponseBody
    @GetMapping("/acceptreservation")
    public void acceptReservation(@RequestParam("reservation_code") String reservation_code) {
        System.out.println(reservation_code);
        reservationService.accept(reservation_code);
    }

    @ResponseBody
    @GetMapping("/cancelreservation")
    public void cancelReservation(@RequestParam("reservation_code") String reservation_code) {
        System.out.println(reservation_code);
        reservationService.cancel(reservation_code);
    }

    @ResponseBody
    @PostMapping("/verify_password_mypage")
    public String verify_password_mypage(@RequestParam("user_id") String user_id) {
        return userService.checkedPassword(user_id);
    }

    @PostMapping(value = "/updateUserInfo")
    public String modifyProfile(@ModelAttribute UserVO userVO, @ModelAttribute DoctorVO doctorVO, @RequestParam(value = "file", required = false) MultipartFile file, Model model) throws Exception {
        if(userVO.getUser_authority().equals("client")) {
            String newFileName = profileImageUtil.storeFile(file);
            System.out.println(userVO.toString());
            System.out.println(doctorVO.toString());
            userVO.setUser_image(newFileName);
            String doctor_id = userVO.getUser_id();
            model.addAttribute("user_id", doctor_id);
            doctorVO.setUser_doctor_id(doctor_id);
            userService.modifyUserDoctorInfo(doctorVO);
            userService.modifyUserCommonInfo(userVO);
    //        System.out.println(userVO.getUser_authority());
            return "redirect:home";
        }else{
            String newFileName = profileImageUtil.storeFile(file);
            System.out.println(userVO.toString());
            System.out.println(doctorVO.toString());
            userVO.setUser_image(newFileName);
            String doctor_id = userVO.getUser_id();
            model.addAttribute("user_id", doctor_id);
            doctorVO.setUser_doctor_id(doctor_id);
            userService.modifyUserDoctorInfo(doctorVO);
            userService.modifyUserCommonInfo(userVO);
            //        System.out.println(userVO.getUser_authority());
            return "redirect:home";
        }
    }
}
