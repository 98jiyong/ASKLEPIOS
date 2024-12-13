package com.asklepios.hospitalreservation_asklepios.Service;

import com.asklepios.hospitalreservation_asklepios.Repository.IF_BoardMapper;
import com.asklepios.hospitalreservation_asklepios.VO.LikeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    IF_BoardMapper boardMapper;
    public boolean checkLike(LikeVO likeVO){
        boolean flag =false;
        if(boardMapper.checkLike(likeVO)!=0) {
            flag = true;
            boardMapper.minusLikeCount(likeVO);
            boardMapper.delLike(likeVO);

        }else{
            boardMapper.plusLikeCount(likeVO);
            boardMapper.addLike(likeVO);
        }
       return flag;
    }
    public boolean firstLike(LikeVO likeVO){
        int check=boardMapper.checkLike(likeVO);
        boolean flag =false;
        if(check==1) {
            flag=true;
        }
        return flag;
    }

    public int countHeart(LikeVO likeVO){
        return boardMapper.selectHeart(likeVO);
    }
}
