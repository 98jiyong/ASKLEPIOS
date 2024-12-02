package com.asklepios.hospitalreservation_asklepios.Service;

import com.asklepios.hospitalreservation_asklepios.VO.UserVO;

import java.util.List;

public interface IF_UserService {
    public boolean login(UserVO userVO);
    public String findId(String user_name, String reg_first, String reg_last);
    public String findEmail(String user_id);
    public String changePw(String user_id);
    public void addUserCommonInfo(UserVO userVO);
    public int duplicateID(String user_id);
    public int duplicateRegisterNumber(String user_register_number);
}
