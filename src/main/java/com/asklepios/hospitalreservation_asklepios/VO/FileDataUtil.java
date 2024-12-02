//package com.asklepios.hospitalreservation_asklepios.VO;
//
//import jakarta.annotation.Resource;
//import jakarta.servlet.http.HttpServletResponse;
//
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.io.File;
//import java.util.ArrayList;
//
//@Controller
//public class FileDataUtil {
//    private ArrayList<String>extNameArray=new ArrayList<>();
//    @Resource
//    private String uploadPath;
//
//    public String getUploadPath() {
//        return uploadPath;
//    }
//    public void setUploadPath(String uploadPath) {
//        this.uploadPath = uploadPath;
//    }
//
//    @RequestMapping(value="/download",method= RequestMethod.GET)
//    @ResponseBody
//    public FileSystemResource fileDownload(@RequestParam("filename")String fileName, HttpServletResponse response){
//        File file=new File(uploadPath+"/"+fileName);
//        response.setContentType("application/download;charset=UTF-8");
//        response.setHeader("Content-Disposition","attachment;filename="+fileName);
//        return new FileSystemResource(file);
//    }
//
//
//
//}
