package com.asklepios.hospitalreservation_asklepios.Controller;

import com.asklepios.hospitalreservation_asklepios.VO.UserVO;
import com.asklepios.hospitalreservation_asklepios.Service.IF_UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController {
    @Autowired
    IF_UserService userService;

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserVO userVO, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();

        if(userService.login(userVO)){
            session.setAttribute("loginUserId", userVO.getUser_id());
            session.setMaxInactiveInterval(30 * 60);
            return "redirect:/home";
        }else {
            String error = "🚫 아이디 또는 비밀번호가 잘못 되었습니다.";
            model.addAttribute("error", error);
            return "/login";
        }
    }

    @PostMapping(value = "logout")
    public String logoutGET(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/home";
    }

    @GetMapping("/findId")
    public String findId() {
        return "findId";
    }

    @PostMapping("/findId")
    public String findId(@RequestParam("user_name")String user_name,
                         @RequestParam("reg_first")String reg_first,
                         @RequestParam("reg_last")String reg_last,
                         Model model) {
        String user_id = userService.findId(user_name, reg_first, reg_last);
        if(user_id == null){
            String error = "🚫 입력한 정보와 일치하는 아이디가 없습니다.";
            model.addAttribute("error", error);
            return "findId";
        }else{
//            System.out.println(user_name + "의 아이디는 " + user_id);
            model.addAttribute("user_name", user_name);
            model.addAttribute("user_id", user_id);
            return "resultId";
        }

    }
    @GetMapping("/findPw")
    public String findPw() {
        return "findPw";
    }
    @PostMapping("/findPw")
    public String findPw(@RequestParam("user_id") String user_id, Model model) {
        String tempPw = userService.changePw(user_id);
        model.addAttribute("user_password", tempPw);
        return "resultPw";
    }

    @ResponseBody
    @PostMapping("/findEmail")
    public String findEmail(@RequestParam("user_id") String user_id) {
        // 받아온 id값을 넘겨줌
        return userService.findEmail(user_id);
    }
    @GetMapping("/userjoin")
    public String userjoin() {
        return "userJoin/agreement";
    }
}
