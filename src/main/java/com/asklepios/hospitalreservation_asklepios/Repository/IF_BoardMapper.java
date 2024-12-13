package com.asklepios.hospitalreservation_asklepios.Repository;
import com.asklepios.hospitalreservation_asklepios.VO.BoardVO;
import com.asklepios.hospitalreservation_asklepios.VO.LikeVO;
import com.asklepios.hospitalreservation_asklepios.VO.PageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface IF_BoardMapper {
    public void insertOne(BoardVO boardVO);
    public List<BoardVO> selectAll(PageVO pagevo);
//    public List<BoardVO> selectHealth(PageVO pagevo);
//    public List<BoardVO> selectCampaign(PageVO pagevo);
//    public List<BoardVO> selectMed(PageVO pagevo);
//    public List<BoardVO> selectFree(PageVO pagevo);
    public List<BoardVO> selectNotice();
    public List<BoardVO> selectBoard(PageVO pagevo,String category);
    public int countBoard(String category);
    public int countAll();
    public BoardVO selectOne(String no);
    public void updateBoard(BoardVO boardVO);
    public void plusViewCount(BoardVO boardVO);



    public int checkLike(LikeVO likeVO);

    public void minusLikeCount(LikeVO likeVO);

    public void delLike(LikeVO likeVO);

    public void plusLikeCount(LikeVO likeVO);

    public void addLike(LikeVO likeVO);
    public int selectHeart(LikeVO likeVO);
}
