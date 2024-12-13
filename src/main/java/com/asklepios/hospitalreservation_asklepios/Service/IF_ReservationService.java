package com.asklepios.hospitalreservation_asklepios.Service;

import com.asklepios.hospitalreservation_asklepios.VO.*;

import java.util.List;

public interface IF_ReservationService {
  public boolean checkHospitalName(String hospitalName, String hospitalAddr);
  public String findHospitalAddress(String hospitalName);
  public String findHospitalCode(String hospitalName);
  public String findDoctorName(String doctorCode);
  public String[] findAllReservationTime(String hospitalName, String doctorName, String reservationDate);
  public void addReservation(String hospitalName, String doctorName, String userName, String reservationDate, String reservationTime, String reservationMemo);
  public void addReservation(ReservationVO reservationvo);
  public List<ReservationStatusVO> findAllReservation(String user_id);
  public List<ReservationStatusVO> findAllDoctorReservation(String user_id);
  public void accept(String reservation_code);
  public void cancel(String reservation_code);
  public HospitalVO findHospital(String hospitalName, String hospitalAddr);
  public List<Hospital_doctorVO> findDoctors(String hospitalCode);
  public String[] popularHospital();

}

