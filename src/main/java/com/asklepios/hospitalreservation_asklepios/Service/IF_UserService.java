package com.asklepios.hospitalreservation_asklepios.Service;

import com.asklepios.hospitalreservation_asklepios.VO.DoctorVO;
import com.asklepios.hospitalreservation_asklepios.VO.MemberVO;
import com.asklepios.hospitalreservation_asklepios.VO.UserVO;

import java.util.List;

public interface IF_UserService {
    public UserVO login(UserVO userVO);
    public String findId(String user_name, String reg_first, String reg_last);
    public String findEmail(String user_id);
    public String changePw(String user_id);
    public void addUserCommonInfo(UserVO userVO);
    public int duplicateID(String user_id);
    public int duplicateRegisterNumber(String user_register_number);
    public void addUserDoctorInfo(DoctorVO doctorVO);
    public String checkedPassword(String user_id);
    public UserVO printOneInfo(String user_id);
    public void modifyUserCommonInfo(UserVO userVO);
    public DoctorVO printOneDoctorInfo(String user_doctor_id);
    public void modifyUserDoctorInfo(DoctorVO doctorVO);
    public String findDoctorCode(String userId);
    public int countReservation(String doctorCode);
    public int countTotalReservation(String userId);
    public MemberVO findUser(String user_id);
    public MemberVO findMember();
}
