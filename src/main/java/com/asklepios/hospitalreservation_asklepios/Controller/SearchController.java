package com.asklepios.hospitalreservation_asklepios.Controller;

import com.asklepios.hospitalreservation_asklepios.Service.IF_SearchService;
import com.asklepios.hospitalreservation_asklepios.VO.HospitalVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    @Autowired
    IF_SearchService searchService;
    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword,
                         Model model) {
//        System.out.println(keyword);
        List< HospitalVO> hlist =searchService.searchHospital(keyword);
//        System.out.println(hlist.size());
//        System.out.println(hlist.toString());
        model.addAttribute("hlist", hlist);
        return "search/searchmain";
    }

}
