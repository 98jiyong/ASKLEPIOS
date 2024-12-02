package com.asklepios.hospitalreservation_asklepios.Repository;

import com.asklepios.hospitalreservation_asklepios.VO.BoardVO;
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

}
