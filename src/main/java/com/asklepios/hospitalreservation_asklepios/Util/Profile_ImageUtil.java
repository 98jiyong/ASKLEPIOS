//package com.asklepios.hospitalreservation_asklepios.Util;
//
//import jakarta.annotation.Resource;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.io.File;
//import java.util.ArrayList;
//
//@Controller
//public class Profile_ImageUtil {
//    private ArrayList<String> extendNameArray = new ArrayList<String>() {
//        {
//            add("gif");
//            add("jpg");
//            add("png");
//            add("jpeg");
//        }
//    };
//
//    @Resource
//    @Getter
//    @Setter
//    private String uploadPath;
//
//    @GetMapping(value = "/download")
//    @ResponseBody
//    public FileSystemResource fileDownload(@RequestParam("filename") String fileName, HttpServletResponse response) {
//        File file = new File(uploadPath + "/" + fileName);
//        response.setContentType("application/download; charset=UTF-8");
//        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
//        return new FileSystemResource(file);
//    }
//}
