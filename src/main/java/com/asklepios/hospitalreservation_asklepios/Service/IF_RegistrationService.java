package com.asklepios.hospitalreservation_asklepios.Service;

import com.asklepios.hospitalreservation_asklepios.VO.HospitalVO;

public interface IF_RegistrationService {
  boolean duplicateCheck(String hospitalAddress, String hospitalName);
  void registerHospital(HospitalVO hospitalvo);
}
