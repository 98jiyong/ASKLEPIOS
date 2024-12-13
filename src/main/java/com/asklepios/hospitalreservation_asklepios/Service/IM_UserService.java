package com.asklepios.hospitalreservation_asklepios.Service;

import com.asklepios.hospitalreservation_asklepios.Repository.IF_UserMapper;
import com.asklepios.hospitalreservation_asklepios.VO.DoctorVO;
import com.asklepios.hospitalreservation_asklepios.VO.MemberVO;
import com.asklepios.hospitalreservation_asklepios.VO.UserVO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IM_UserService implements IF_UserService{
    @Autowired
    IF_UserMapper usermapper;

    @Override
    public UserVO login(UserVO userVO) {
        String pwd = usermapper.selectPwd(userVO);
        UserVO uservo;
        if(userVO.getUser_password().equals(pwd)){
            uservo = usermapper.selectUser(userVO);
//            System.out.println("일치");
        }else{
            uservo = null;
//            System.out.println("불일치");
        }
        return uservo;
    }

    @Override
    public String findId(String user_name, String reg_first, String reg_last) {
        int cnt = 0;
        List<UserVO> ulist = usermapper.selectAllName();
        String reg_num = reg_first + '-' + reg_last;
//        System.out.println(reg_num);
//        System.out.println(register_number);
        // 유저 목록에 사용자가 있는지 확인
        // 있으면 조회
        // 없으면 없다고 통보
        for(UserVO userVO : ulist){
//            System.out.println(userVO.getUser_name());
            if(user_name.equals(userVO.getUser_name())){
//                System.out.println("일치");
                cnt++;
            }
        }

        if(cnt > 0){
            String register_number = usermapper.selectRegnum(user_name);
            if(reg_num.equals(register_number)){
//                System.out.println("일치");
                String user_id = usermapper.selectId(reg_num);
                int idx = user_id.length()/2;
                String str = user_id.substring(0, idx) + user_id.replaceAll(".","*").substring(idx);
                return str;
            }else {
//                System.out.println("불일치");
                return null;
            }
        }else{
            return null;
        }
    }

    @Override
    public String findEmail(String user_id) {



        return usermapper.selectEmail(user_id);
    }

    @Override
    public String changePw(String user_id) {
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z' };

        StringBuilder tempPw = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int idx = (int) (charSet.length * Math.random());
            tempPw.append(charSet[idx]);
        }
        usermapper.updatePwd(user_id,tempPw.toString()) ;
        return tempPw.toString();
    }

    @Override
    public void addUserCommonInfo(UserVO userVO) {
        usermapper.insertUserCommonInfo(userVO);
    }

    @Override
    public int duplicateID(String user_id) {
        if(usermapper.duplicateIDCheck(user_id) != null){
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int duplicateRegisterNumber(String user_register_number) {
        if(usermapper.duplicateRegisterNumberCheck(user_register_number) != null){
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void addUserDoctorInfo(DoctorVO doctorVO) {
        usermapper.insertUserDoctorInfo(doctorVO);
    }

    @Override
    public String checkedPassword(String user_id) {
        return usermapper.selectPwdUsingID(user_id);
    }

    @Override
    public UserVO printOneInfo(String user_id) {
        return usermapper.selectUserByID(user_id);
    }

    @Override
    public void modifyUserCommonInfo(UserVO userVO) {
        usermapper.updateUserCommonInfo(userVO);
    }

    @Override
    public DoctorVO printOneDoctorInfo(String user_doctor_id) {
        return usermapper.selectDoctorByID(user_doctor_id);
    }

    @Override
    public void modifyUserDoctorInfo(DoctorVO doctorVO) {
        usermapper.updateUserDoctorInfo(doctorVO);
    }

    @Override
    public String findDoctorCode(String userId) {
        return usermapper.selectDoctorCode(userId);
    }

    @Override
    public int countReservation(String doctorCode) {
        return usermapper.selectReservationCount(doctorCode);
    }

    @Override
    public int countTotalReservation(String userId) {
        return usermapper.selectTotalReservationCount(userId);
    }

    @Override
    public MemberVO  findUser(String user_id) {
        return usermapper.selectMember(user_id);
    }

    @Override
    public MemberVO findMember(){
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return usermapper.selectMember(username);
    }


}
