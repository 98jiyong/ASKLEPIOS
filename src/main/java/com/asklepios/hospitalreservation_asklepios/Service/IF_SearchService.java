package com.asklepios.hospitalreservation_asklepios.Service;

import com.asklepios.hospitalreservation_asklepios.VO.HospitalVO;
import com.asklepios.hospitalreservation_asklepios.VO.Hospital_doctorVO;
import com.asklepios.hospitalreservation_asklepios.VO.ReviewVO;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IF_SearchService {
    public List<HospitalVO> searchHospital(String name);
    public List<Hospital_doctorVO> searchInfo(String name);
    public List<Hospital_doctorVO> filterDate(List<Hospital_doctorVO> modHospitalList);
    public List<Hospital_doctorVO> filterIng(List<Hospital_doctorVO> modHospitalList);
    public List<ReviewVO> searchReview(String hospital_code);
    public Integer getAvg(String hospitalCode);

    public Workbook excelPrint(List<Hospital_doctorVO> hospitalList) throws IOException;
}
