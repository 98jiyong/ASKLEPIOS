package com.asklepios.hospitalreservation_asklepios.Controller;

import com.asklepios.hospitalreservation_asklepios.Service.IF_SearchService;

import com.asklepios.hospitalreservation_asklepios.Service.IF_UserService;
import com.asklepios.hospitalreservation_asklepios.VO.Hospital_doctorVO;
import com.asklepios.hospitalreservation_asklepios.VO.ReviewVO;
import com.asklepios.hospitalreservation_asklepios.VO.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class SearchController {
    @Autowired
    IF_SearchService searchService;
    @Autowired
    IF_UserService userservice;

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("keyword") String keyword) {
//        System.out.println(keyword);
//        List<HospitalVO> hlist =searchService.searchHospital(keyword);
        List<Hospital_doctorVO> hlist=searchService.searchInfo(keyword);
//        System.out.println(hlist.size());
//        System.out.println(hlist.toString());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userservice.findMember());
        modelAndView.addObject("hlist", hlist);
        modelAndView.setViewName("search/searchmain");
        return modelAndView;
//        return "search/searchmain";
    }
    @PostMapping("/filter")
    public List<Hospital_doctorVO> filterDate(@RequestParam Map<String, Object> hospitalList,
                                       @RequestParam("type")String type) throws JsonProcessingException {
            String data = hospitalList.get("hospitalList").toString();
        ObjectMapper mapper = new ObjectMapper();
        List<Hospital_doctorVO> modHospitalList = mapper.readValue(data, new TypeReference<List<Hospital_doctorVO>>() {
        });
//        for(HospitalVO hospitalVO:modHospitalList){
//            System.out.println(hospitalVO.toString());
//        }
        if(type.equals("date")){
          modHospitalList = searchService.filterDate(modHospitalList);
        }else if(type.equals("ing")){
          modHospitalList = searchService.filterIng(modHospitalList);
        }
        return modHospitalList;
    }
    @PostMapping("/getreview")
//    public List<ReviewVO> getReview(@RequestParam("hospitalCode") String hospitalCode){
////        System.out.println(hospitalCode);
//        return searchService.searchReview(hospitalCode);
//    }
    public HashMap<String,Object> getReview(@RequestParam("hospitalCode") String hospitalCode){
        List<ReviewVO> reviewList=searchService.searchReview(hospitalCode);
        Integer avgScore=searchService.getAvg(hospitalCode);
//        System.out.println(avgScore);
        HashMap<String,Object> map=new HashMap<>();
        map.put("avgScore",avgScore);
        map.put("reviewList",reviewList);
        return map;
    }
    @GetMapping("/excelDownload")
    public void excelDownload(HttpServletResponse response,
                              @RequestParam Map<String,Object> jsonList) throws IOException {
//        String json=jsonList.get("hospitalList").toString();
//        System.out.println(json);
//        ObjectMapper mapper = new ObjectMapper();
//        List<Hospital_doctorVO> hospitalList=
//            mapper.readValue(json,
//                new TypeReference<List<Hospital_doctorVO>>() {});
//        System.out.println(hospitalList);
        List<Hospital_doctorVO>hospitalList=searchService.searchInfo("");
        Workbook workbook = searchService.excelPrint(hospitalList);
        //html페이지에서 요청받은 데이터를 엑셀파일에 작성
          //컨텐츠 타입, 파일명 지정
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=hospitalList.xlsx");
        ServletOutputStream servletOutputStream = response.getOutputStream();
        //엑셀파일 저장
        workbook.write(servletOutputStream);
        workbook.close();
        servletOutputStream.close();
    }
}
