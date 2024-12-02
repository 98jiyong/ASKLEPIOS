package com.asklepios.hospitalreservation_asklepios.Controller;

import com.asklepios.hospitalreservation_asklepios.Service.LikeService;
import com.asklepios.hospitalreservation_asklepios.VO.LikeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class LikeController {
    @Autowired
    LikeService likeService;
    @PostMapping("/like")
   public boolean like(@RequestBody LikeVO likeVO) {

//        System.out.println(likeVO.getLike_id());
//        System.out.println(likeVO.getBoard_sequence());
//        likeVO.setLiked(true);

//        HashMap<String,Object> map = new HashMap<>();
//        likeVO.setLiked(likeService.checkLike(likeVO));
//        map.put("likeVO", likeVO);
        //        System.out.println(likeVO.toString());
//        map.put("likeVO", likeVO);

     return likeService.checkLike(likeVO);
//        return map;
    }
    @PostMapping("/likecheck")
    public boolean likecheck(@RequestBody LikeVO likeVO) {
        return likeService.firstLike(likeVO);
    }
}
