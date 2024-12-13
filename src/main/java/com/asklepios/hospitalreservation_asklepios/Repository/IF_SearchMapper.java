package com.asklepios.hospitalreservation_asklepios.Repository;

import com.asklepios.hospitalreservation_asklepios.VO.HospitalVO;
import com.asklepios.hospitalreservation_asklepios.VO.Hospital_doctorVO;
import com.asklepios.hospitalreservation_asklepios.VO.ReviewVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IF_SearchMapper {
    public List<HospitalVO> selectHospital(String name);
    public List<Hospital_doctorVO> selectInfo(String name);
    public List<ReviewVO> selectReview(String hospital_code);


  public Integer avgScore(String hospitalCode);
}
