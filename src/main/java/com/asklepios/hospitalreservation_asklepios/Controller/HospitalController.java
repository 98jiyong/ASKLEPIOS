package com.asklepios.hospitalreservation_asklepios.Controller;

import com.asklepios.hospitalreservation_asklepios.Service.IF_SearchService;
import com.asklepios.hospitalreservation_asklepios.VO.HospitalVO;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class HospitalController {

    @Autowired
    IF_SearchService searchService;

    @ResponseBody
    @GetMapping("/hospitalList")
    public void searchHospital(@RequestParam String hospital_name, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        List<HospitalVO> hospitalList = searchService.searchHospital(hospital_name);
        System.out.println(hospital_name);
        String hospitalJson = gson.toJson(hospitalList);
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.print(hospitalJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(hospital_name);
    }
}
