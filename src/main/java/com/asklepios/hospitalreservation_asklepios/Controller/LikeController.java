package com.asklepios.hospitalreservation_asklepios.Controller;

import com.asklepios.hospitalreservation_asklepios.Service.LikeService;
import com.asklepios.hospitalreservation_asklepios.VO.HospitalVO;
import com.asklepios.hospitalreservation_asklepios.VO.LikeVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LikeController {
    @Autowired
    LikeService likeService;

//    @PostMapping("/like")
//    public boolean like(@RequestBody LikeVO likeVO) {
//
////        System.out.println(likeVO.getLike_id());
////        System.out.println(likeVO.getBoard_sequence());
////        likeVO.setLiked(true);
//
////        HashMap<String,Object> map = new HashMap<>();
////        likeVO.setLiked(likeService.checkLike(likeVO));
////        map.put("likeVO", likeVO);
//        //        System.out.println(likeVO.toString());
////        map.put("likeVO", likeVO);
//
//        return likeService.checkLike(likeVO);
////        return map;
//    }
    @PostMapping("/like")
    public HashMap<String,Object> like(@RequestBody LikeVO likeVO){
        HashMap<String,Object> map = new HashMap<>();
        likeVO.setLiked(likeService.checkLike(likeVO));
        int heart= likeService.countHeart(likeVO);
        map.put("heart",heart);
        map.put("likeVO",likeVO);
        return map;
    }
    @PostMapping("/likecheck")
    public boolean likecheck(@RequestBody LikeVO likeVO) {
        return likeService.firstLike(likeVO);
    }


}
