package com.asklepios.hospitalreservation_asklepios.Repository;

import com.asklepios.hospitalreservation_asklepios.VO.BoardVO;
import com.asklepios.hospitalreservation_asklepios.VO.DoctorVO;
import com.asklepios.hospitalreservation_asklepios.VO.MemberVO;
import com.asklepios.hospitalreservation_asklepios.VO.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IF_UserMapper {
    String selectPwd(UserVO userVO);
    String selectRegnum(String user_name);
    List<UserVO> selectAllName();
    String selectId(String user_register_number);
    String selectEmail(String user_id);
    void updatePwd(String user_id, String pwd);
    void insertUserCommonInfo(UserVO userVO);
    String duplicateIDCheck(String user_id);
    String duplicateRegisterNumberCheck(String user_register_number);
    void insertUserDoctorInfo(DoctorVO doctorVO);
    String selectPwdUsingID(String user_id);
    UserVO selectUserByID(String user_id);
    UserVO selectUser(UserVO userVO);
    void updateUserCommonInfo(UserVO userVO);
    DoctorVO selectDoctorByID(String user_doctor_id);
    void updateUserDoctorInfo(DoctorVO doctorVO);
    String selectDoctorCode(String userId);
    int selectReservationCount(String doctorCode);
    int selectTotalReservationCount(String userId);
    MemberVO selectMember(String user_id);
}
