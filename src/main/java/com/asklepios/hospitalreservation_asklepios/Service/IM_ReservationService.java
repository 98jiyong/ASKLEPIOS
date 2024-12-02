package com.asklepios.hospitalreservation_asklepios.Service;

import com.asklepios.hospitalreservation_asklepios.Repository.IF_ReservationMapper;
import com.asklepios.hospitalreservation_asklepios.VO.HospitalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IM_ReservationService implements IF_ReservationService {

  @Autowired
  IF_ReservationMapper reservationmapper;


  @Override
  public boolean checkHospitalName(String hospitalName) {
    boolean flag = false;
    List<HospitalVO> hospitalNameList = reservationmapper.selectAllHospitalName();
    for (HospitalVO hospitalVO : hospitalNameList) {
      if(hospitalName.equals(hospitalVO.getHospital_name())){
        flag = true;
      }
    }
    return flag;
  }
}
