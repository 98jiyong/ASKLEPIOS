package com.asklepios.hospitalreservation_asklepios.Service;

import com.asklepios.hospitalreservation_asklepios.VO.HospitalVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IF_SearchService {
    public List<HospitalVO> searchHospital(String name);
}
