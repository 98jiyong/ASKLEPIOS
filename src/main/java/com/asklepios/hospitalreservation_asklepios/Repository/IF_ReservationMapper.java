package com.asklepios.hospitalreservation_asklepios.Repository;

import com.asklepios.hospitalreservation_asklepios.VO.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IF_ReservationMapper {
  List<HospitalVO> getAllHospitalName();
  String getHospitalAddress(String hospitalName);
  String getDoctorName(String doctorCode);
  String[] getAllReservationTime(String hospitalCode, String doctorCode, String reservationDate);
  String getNextReservationCode();
  String getHospitalCode(String hospitalName);
  String getDoctorCode(String doctorName);
  String getUserId(String userName);
  void insertReservation(ReservationVO reservationvo);
  List<ReservationStatusVO> selectUserReservationStatus(String user_id);
  List<ReservationStatusVO> selectDoctorReservationStatus(String user_id);
  void updateAccept(String reservation_code);
  void updateCancel(String reservation_code);
  List<Hospital_doctorVO> selectDoctor(String hospitalCode);
  HospitalVO selectHospital(@Param("hospitalName") String hospitalName, @Param("hospitalAddr") String HospitalAddr);
  String[] getPopularHospital();
}
