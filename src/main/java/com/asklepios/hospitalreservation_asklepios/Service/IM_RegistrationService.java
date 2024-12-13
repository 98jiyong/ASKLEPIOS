package com.asklepios.hospitalreservation_asklepios.Service;

import com.asklepios.hospitalreservation_asklepios.Repository.IF_RegistrationMapper;
import com.asklepios.hospitalreservation_asklepios.VO.HospitalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IM_RegistrationService implements IF_RegistrationService {

  @Autowired
  IF_RegistrationMapper registrationMapper;

  @Override
  public boolean duplicateCheck(String hospitalAddress, String hospitalName) {
    String[] hospitalNameList = registrationMapper.selectHospitalName(hospitalAddress);
    if(hospitalNameList != null){
      for(int i=0; i<hospitalNameList.length; i++){
        System.out.println(hospitalNameList[i]);
        if(hospitalNameList[i].contains(hospitalName)){
//          System.out.println("포함");
          return true;
        }
        else if(hospitalName.contains(hospitalNameList[i])){
//          System.out.println("포함");
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public void registerHospital(HospitalVO hospitalvo) {
    registrationMapper.insertHospital(hospitalvo);
  }
}
