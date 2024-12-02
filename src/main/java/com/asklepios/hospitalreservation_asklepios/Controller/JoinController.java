package com.asklepios.hospitalreservation_asklepios.Controller;

import com.asklepios.hospitalreservation_asklepios.Service.IF_UserService;
import com.asklepios.hospitalreservation_asklepios.Service.IM_UserService;
import com.asklepios.hospitalreservation_asklepios.VO.UserVO;
import jakarta.activation.DataSource;

import jakarta.activation.FileDataSource;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Controller
public class JoinController {

    @Autowired
    IF_UserService userService;


    @GetMapping("/agreement")
    public String agreement(Model model) throws Exception {
//        for(int i = 0; i < userService.getUserIDAndRegisterNumber().size(); i++){
//            System.out.println(userService.getUserIDAndRegisterNumber().get(i).toString());
//        };
//        model.addAttribute("userInfo", userService.getUserIDAndRegisterNumber());
        return "userJoin/insertCommonInfo";
    }

    @ResponseBody
    @PostMapping("/insertedID")
    public Integer insertedID(@RequestParam String user_id) throws Exception {
        return userService.duplicateID(user_id);
    }

    @ResponseBody
    @PostMapping("/insertedRegisterNumber")
    public Integer insertedRegisterNumber(@RequestParam String user_register_number) throws Exception {
        return userService.duplicateRegisterNumber(user_register_number);
    }

    @PostMapping("/commoninfo")
    public String commoninfo(@ModelAttribute UserVO userVO, MultipartFile[] user_image) throws Exception {
        System.out.println(userVO.toString());
        userService.addUserCommonInfo(userVO);
        return "userJoin/insertAdditionalInfo";
    }

}
