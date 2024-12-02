package com.asklepios.hospitalreservation_asklepios.Service;

import com.asklepios.hospitalreservation_asklepios.Repository.IF_SearchMapper;
import com.asklepios.hospitalreservation_asklepios.VO.HospitalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IM_SearchService implements IF_SearchService{
    @Autowired
    IF_SearchMapper searchMapper;
    @Override
    public List<HospitalVO> searchHospital(String name) {
        return searchMapper.selectHospital(name);
    }
}
