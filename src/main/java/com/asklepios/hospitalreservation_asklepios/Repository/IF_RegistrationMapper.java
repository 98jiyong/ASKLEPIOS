package com.asklepios.hospitalreservation_asklepios.Repository;

import com.asklepios.hospitalreservation_asklepios.VO.HospitalVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IF_RegistrationMapper {
  String[] selectHospitalName(String hospitalAddress);
  void insertHospital(HospitalVO hospitalVO);
}
