package com.asklepios.hospitalreservation_asklepios.Util;


import jakarta.servlet.http.HttpServletResponse;


import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@Data
@Controller
public class FileDataUtil {
    private ArrayList<String>extNameArray=new ArrayList<>();
    @Setter
    @Value("${filePath}")
    private String uploadPath;


  @RequestMapping(value = "/downloadfile", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource fileDownload(@RequestParam("filename")String fileName, HttpServletResponse response){
        File file=new File(uploadPath+"/"+fileName);
        response.setContentType("application/download;charset=UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        return new FileSystemResource(file);
    }
  public String [] fileUpload(MultipartFile [] file) throws IOException {
    String [] files = new String[file.length];
      for(int i=0;i<files.length;i++){
        if(!Objects.equals(file[i].getOriginalFilename(), "")){
          String originalName=file[i].getOriginalFilename();
          UUID uid= UUID.randomUUID();
          String saveName=uid.toString()+"."+originalName.split("\\.")[1];
          files=new String[]{saveName};
          byte[]fileData=files[i].getBytes();
          File target=new File(uploadPath,saveName);
          FileCopyUtils.copy(fileData,target);
          files[i]=saveName;
        }
      }
      return files;
  }


}
