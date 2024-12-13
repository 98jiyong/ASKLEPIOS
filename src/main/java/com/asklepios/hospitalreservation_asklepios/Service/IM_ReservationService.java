package com.asklepios.hospitalreservation_asklepios.Service;

import com.asklepios.hospitalreservation_asklepios.Repository.IF_ReservationMapper;
import com.asklepios.hospitalreservation_asklepios.VO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IM_ReservationService implements IF_ReservationService {

  @Autowired
  IF_ReservationMapper reservationmapper;


  @Override
  public boolean checkHospitalName(String hospitalName, String hospitalAddr) {
    boolean flag = false;
    List<HospitalVO> hospitalNameList = reservationmapper.getAllHospitalName();
    for (HospitalVO hospitalVO : hospitalNameList) {
      if(hospitalVO.getHospital_name().contains(hospitalName)){
        if(hospitalVO.getHospital_addr().contains(hospitalAddr)){
        flag = true;
        }
      }
    }
    return flag;
  }

  @Override
  public String findHospitalAddress(String hospitalName) {
    return reservationmapper.getHospitalAddress(hospitalName);
  }

  @Override
  public String findHospitalCode(String hospitalName) {
    return reservationmapper.getHospitalCode(hospitalName);
  }

  @Override
  public String findDoctorName(String doctorCode) {
    return reservationmapper.getDoctorName(doctorCode);
  }

  @Override
  public String[] findAllReservationTime(String hospitalCode, String doctorCode, String reservationDate) {
    return reservationmapper.getAllReservationTime(hospitalCode, doctorCode, reservationDate);
  }


  @Override
  public void addReservation(String hospitalCode, String doctorCode, String userName, String reservationDate,
                             String reservationTime, String reservationMemo) {
    ReservationVO reservationVO = new ReservationVO();
    //시퀀스 만들어서 가져오고
    String reservationCode = reservationmapper.getNextReservationCode();
    // 병원이름으로 병원코드 가져오고
//    String hospitalCode = reservationmapper.getHospitalCode(hospitalName);
    // 숨겨놓은 의사코드를 사용해서 의사코드 가져오고
    // 근데 지금은 모달 구현이 안돼있으니 doctorName으로 id를 받아오자
//    String doctorCode = reservationmapper.getDoctorCode(doctorName);
    // 사용자 또한 숨겨놓은 사용자 id를 가져와서 사용 지금은 구현돼있지 않으니 name으로 가져오자
    String userId = reservationmapper.getUserId(userName);
    // 선택한 날짜로 예약날짜 가져오고
    // reservationDate
    // 선택한 시간으로 예약시간 가져오고
    // reservationTime
    // 적힌 메모로 예약메모 가져오고
    // reservationMemo
    // 승인을 default reject로 설정
    // VO를 불러와서 집어넣는다.
    reservationVO.setReservation_code(reservationCode);
    reservationVO.setReservation_hospital_code(hospitalCode);
    reservationVO.setReservation_user_doctor_code(doctorCode);
    reservationVO.setReservation_user_id(userId);
    reservationVO.setReservation_date(reservationDate);
    reservationVO.setReservation_time(reservationTime);
    reservationVO.setReservation_memo(reservationMemo);
    System.out.println(reservationVO.toString());
    reservationmapper.insertReservation(reservationVO);
  }

  @Override
  public void addReservation(ReservationVO reservationvo) {
//    System.out.println(reservationvo.toString());
    reservationmapper.insertReservation(reservationvo);
  }

  @Override
  public List<ReservationStatusVO> findAllReservation(String user_id) {
    return reservationmapper.selectUserReservationStatus(user_id);
  }

  @Override
  public List<ReservationStatusVO> findAllDoctorReservation(String user_id) {
    return reservationmapper.selectDoctorReservationStatus(user_id);
  }

  @Override
  public void accept(String reservation_code) {
    reservationmapper.updateAccept(reservation_code);
  }

  @Override
  public void cancel(String reservation_code) {
    reservationmapper.updateCancel(reservation_code);
  }

  @Override
  public HospitalVO findHospital(String hospitalName, String hospitalAddr) {
    return reservationmapper.selectHospital(hospitalName, hospitalAddr);
  }

  @Override
  public List<Hospital_doctorVO> findDoctors(String hospitalCode) {
//    List<Hospital_doctorVO> hospitalDoctorList = reservationmapper.selectDoctor(hospitalCode);
//    for(Hospital_doctorVO h : hospitalDoctorList){
//      System.out.println(h.toString());
//      if (h.getUser_image() == null) {
//        System.out.println(h.toString());
//        h.setUser_image("doctor_image.jpg");
//        System.out.println(h.toString());
//      }
//    }
    return reservationmapper.selectDoctor(hospitalCode);
  }

  @Override
  public String[] popularHospital() {
    return reservationmapper.getPopularHospital();
  }

}
