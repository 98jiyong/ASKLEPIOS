package com.asklepios.hospitalreservation_asklepios.Service;

import com.asklepios.hospitalreservation_asklepios.Repository.IF_SearchMapper;
import com.asklepios.hospitalreservation_asklepios.VO.HospitalVO;
import com.asklepios.hospitalreservation_asklepios.VO.Hospital_doctorVO;
import com.asklepios.hospitalreservation_asklepios.VO.ReviewVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import java.util.List;

@Service
public class IM_SearchService implements IF_SearchService{
    @Autowired
    IF_SearchMapper searchMapper;
    @Override
    public List<HospitalVO> searchHospital(String name) {
        return searchMapper.selectHospital(name);
    }

    @Override
    public List<Hospital_doctorVO> searchInfo(String name) {
        List<Hospital_doctorVO> list=searchMapper.selectInfo(name);
//        System.out.println(list.toString());
        return searchMapper.selectInfo(name);
    }


    @Override
    public List<Hospital_doctorVO> filterDate(List<Hospital_doctorVO> hospitalList) {
        List<Hospital_doctorVO> modHospitalList = hospitalList;
        modHospitalList.removeIf(hospitalVO -> hospitalVO.getHospital_date().equals("n"));
//        System.out.println(modHospitalList.size());
        return modHospitalList;
    }

    @Override
    public List<Hospital_doctorVO> filterIng(List<Hospital_doctorVO> hospitalList) {
        List<Hospital_doctorVO> modHospitalList=hospitalList;
        LocalTime localTime=LocalTime.now();
        int nowHour= LocalTime.now().getHour();
        int nowMinute=LocalTime.now().getMinute();
//        System.out.println(nowHour+":"+nowMinute);
//        System.out.println(localTime);
//        System.out.println(localTime.getHour());
//        System.out.println(localTime.getMinute());
//        System.out.println(modHospitalList.get(0).getHospital_time().substring(0,2));
//        System.out.println(modHospitalList.get(0).getHospital_time().substring(6,8));
        for(int i=0;i<modHospitalList.size();i++){
            int startTime=Integer.parseInt(modHospitalList.get(i).getHospital_time().substring(0,2));
            int endTime=Integer.parseInt(modHospitalList.get(i).getHospital_time().substring(6,8));
            int startMin=Integer.parseInt(modHospitalList.get(i).getHospital_time().substring(3,5));
            int endMin=Integer.parseInt(modHospitalList.get(i).getHospital_time().substring(9));
//            System.out.println(modHospitalList.get(i).getHospital_name());
//           System.out.println("시작시간");
//            System.out.println(startTime+":"+startMin);
//            System.out.println("종료시간");
//            System.out.println(endTime+":"+endMin);
            if(nowHour<startTime||nowHour>endTime){
                modHospitalList.remove(i);
                i--;
            }else if(nowHour==startTime){
                if(nowMinute<startMin){
                    modHospitalList.remove(i);
                    i--;
                }
            }else if(nowHour==endTime){
                if(nowMinute>=endMin){
                    modHospitalList.remove(i);
                    i--;
                }
            }

        }
//        for (HospitalVO hospitalVO : modHospitalList) {
//            System.out.println(hospitalVO.getHospital_time());
//        }
        return modHospitalList;

    }

    @Override
    public List<ReviewVO> searchReview(String hospital_code) {
        return searchMapper.selectReview(hospital_code);
    }

    @Override
    public Integer getAvg(String hospitalCode) {
        Integer avg=searchMapper.avgScore(hospitalCode);
        if(avg==null){
            return 0;
        }
        System.out.println(avg);
      return avg;
    }

    @Override
    public Workbook excelPrint(List<Hospital_doctorVO> hospitalList) throws IOException {
      System.out.println(hospitalList.getClass());
      //엑셀 저장경로지정
      final String sep = File.separator;
      final String staticPath = System.getProperty("user.dir") + sep + "src" + sep + "main" + sep + "resources" + sep + "static" + sep+"ExcelTemplate"+sep+"hospitalList.xlsx";
      //엑셀파일 생성
      Workbook workbook = new XSSFWorkbook();
      Sheet sheet =workbook.createSheet("All Hospital");
      Row row = sheet.createRow(0);

      //헤더 입력
      row.createCell(0).setCellValue("번호");
      row.createCell(1).setCellValue("병원이름");
      row.createCell(2).setCellValue("진료과목");
      row.createCell(3).setCellValue("진료시간");
      row.createCell(4).setCellValue("전화번호");
      row.createCell(5).setCellValue("주소");


      if (!hospitalList.isEmpty()) {
        int excelRow = 1;
        int excelLength = hospitalList.size();
        System.out.println(excelLength);
        String[] hospital_name = new String[excelLength];
        String[] hospital_medical = new String[excelLength];
        String[] hospital_time = new String[excelLength];
        String[] hospital_tel = new String[excelLength];
        String[] hospital_address = new String[excelLength];
        for (int i = 0; i < excelLength; i++) {
          //열 너비 조절
          sheet.autoSizeColumn(i);
          if(i==0||i==2||i==3||i==4){
            sheet.setColumnWidth(i,sheet.getColumnWidth(i)+1024);
          } else if (i==1) {
            sheet.setColumnWidth(i,sheet.getColumnWidth(i)+4096);
          } else if (i==5) {
            sheet.setColumnWidth(i,sheet.getColumnWidth(i)+5120);
          }

          //배열에 정보 저장하기
          hospital_name[i] = hospitalList.get(i).getHospital_name();
          hospital_medical[i] = hospitalList.get(i).getMedicals();
          hospital_time[i] = hospitalList.get(i).getHospital_time();
          hospital_tel[i] = hospitalList.get(i).getHospital_tel();
          hospital_address[i] = hospitalList.get(i).getHospital_addr();
          System.out.println(i);
          //엑셀파일에 정보 저장하기
          row = sheet.createRow(excelRow);
          row.createCell(0).setCellValue(excelRow);
          System.out.println(excelRow);
          row.createCell(1).setCellValue(hospital_name[i]);
          System.out.println(hospital_name[i]);
          row.createCell(2).setCellValue(hospital_medical[i]);
          System.out.println(hospital_medical[i]);
          row.createCell(3).setCellValue(hospital_time[i]);
          System.out.println(hospital_time[i]);
          row.createCell(4).setCellValue(hospital_tel[i]);
          System.out.println(hospital_tel[i]);
          row.createCell(5).setCellValue(hospital_address[i]);
          System.out.println(hospital_address[i]);
          System.out.println(row.getPhysicalNumberOfCells());
          excelRow++;
        }
      }
      sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, 5));


      //엑셀파일 지정된 경로에 저장하기
      try {
        FileOutputStream fileOutputStream = new FileOutputStream(staticPath);
        workbook.write(fileOutputStream);
      }catch (Exception e){
        e.printStackTrace();
      }
      return workbook;
    }
}
