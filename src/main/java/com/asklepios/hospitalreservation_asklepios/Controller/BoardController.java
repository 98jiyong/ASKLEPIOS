package com.asklepios.hospitalreservation_asklepios.Controller;

import com.asklepios.hospitalreservation_asklepios.Service.IF_BoardService;
import com.asklepios.hospitalreservation_asklepios.Service.IF_UserService;
import com.asklepios.hospitalreservation_asklepios.Util.FileDataUtil;
//import com.asklepios.hospitalreservation_asklepios.Util.FileDataUtil;
import com.asklepios.hospitalreservation_asklepios.VO.BoardVO;
import com.asklepios.hospitalreservation_asklepios.VO.MemberVO;
import com.asklepios.hospitalreservation_asklepios.VO.PageVO;
import com.asklepios.hospitalreservation_asklepios.VO.UserVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class BoardController {
  @Autowired
  IF_BoardService boardService;
  @Autowired
  FileDataUtil fileDataUtil;
  @Autowired
  IF_UserService userService;

  @GetMapping("/bboard_all")
  public String board_all(Model model, @ModelAttribute PageVO pagevo) throws Exception{
    MemberVO user=userService.findMember();
    model.addAttribute("user", user);
    if(pagevo.getPage()==null){
      pagevo.setPage(1);
    }
    String category="모든 글";
    pagevo.setTotalCount(boardService.boardCount(category));
//    System.out.println(pagevo.getTotalCount());
//    System.out.println(pagevo.getPage());
//    System.out.println(pagevo.getStartNo());
    List<BoardVO> boardlist=boardService.boardAll(pagevo);
//    List<BoardVO> noticelist=boardService.boardNoticeList();
    model.addAttribute("boardlist", boardlist);
//    model.addAttribute("noticelist", noticelist);
    model.addAttribute("category", category);
    return "board/main";
  }

  @GetMapping("/bboard_health")
  public String board_health(Model model, @ModelAttribute PageVO pagevo) throws Exception {
    MemberVO user=userService.findMember();
    if(pagevo.getPage()==null){
      pagevo.setPage(1);
    }
    String category="오늘의 건강";
//    System.out.println(pagevo.getSearchKeyword());
    pagevo.setTotalCount(boardService.boardCount(category));
    List<BoardVO> boardlist=boardService.boardList(pagevo,category);
//        System.out.println(pagevo.getPage());
//        System.out.println(pagevo.getStartNo()+"/"+pagevo.getEndNo());
//        System.out.println(pagevo.isNext());
//        System.out.println(pagevo.isPrev());
    model.addAttribute("user", user);
    model.addAttribute("boardlist",boardlist);
    model.addAttribute("category", category);
    return "board/main";
  }
  @GetMapping("/bboard_campaign")
  public String board_cam(Model model, @ModelAttribute PageVO pageVO ) throws Exception {
    MemberVO user=userService.findMember();
    if(pageVO.getPage()==null){
      pageVO.setPage(1);
    }
    String category="캠페인";
    pageVO.setTotalCount(boardService.boardCount(category));
    List<BoardVO> boardlist=boardService.boardList(pageVO,category);
    model.addAttribute("user", user);
    model.addAttribute("boardlist",boardlist);
    model.addAttribute("category", category);
    return "board/main";
  }
  @GetMapping("/bboard_med")
  public String board_med(Model model , @ModelAttribute PageVO pagevo) throws Exception {
    MemberVO user=userService.findMember();
    if(pagevo.getPage()==null){
      pagevo.setPage(1);
    }
    String category="의료정보";
    pagevo.setTotalCount(boardService.boardCount(category));
    List<BoardVO> boardlist=boardService.boardList(pagevo,category);
    model.addAttribute("user", user);
    model.addAttribute("boardlist",boardlist);
    model.addAttribute("category", category);
    return "board/main";
  }
  @GetMapping("/bboard_free")
  public String board_free(Model model , @ModelAttribute PageVO pagevo) throws Exception {
    if(pagevo.getPage()==null){
      pagevo.setPage(1);
    }
    String category="자유게시판";
    pagevo.setTotalCount(boardService.boardCount(category));
    List<BoardVO> boardlist=boardService.boardList(pagevo,category);
    model.addAttribute("user", userService.findMember());
    model.addAttribute("boardlist",boardlist);
    model.addAttribute("category", category);
    return "board/main";
  }
  @GetMapping("/write")
  public String write(  Model model){
    model.addAttribute("user", userService.findMember());
    return "board/write";
  }

  @PostMapping("/submitwrite")
  public String submitWrite(@ModelAttribute BoardVO boardVO,
                            @ModelAttribute MultipartFile [] file) throws Exception {

//    System.out.println(file.toString());
    String []newFileName=fileDataUtil.fileUpload(file);
    String boardFilename="";
    for(int i=0;i<newFileName.length;i++){
      boardFilename+=(newFileName[i]);
      if(i!=newFileName.length-1){
        boardFilename+=",";
      }
    }
    boardVO.setBoard_binary(boardFilename);
    System.out.println(boardVO.getBoard_binary());
    boardService.addBoard(boardVO);
    return "redirect:/bboard_all";
  }
  @GetMapping("/detail")
  public String detail(Model model, @ModelAttribute PageVO pagevo,
                       @RequestParam("no") String no) throws Exception {
    BoardVO boardVO=boardService.detail(no);
    model.addAttribute("user", userService.findMember());
    model.addAttribute("boardVO",boardVO);
    return "board/detail";
  }

  @GetMapping("/modboard")
  public String mod(@ModelAttribute BoardVO boardVO,
                    Model model, HttpServletResponse response, HttpServletRequest request) throws Exception {
//        System.out.println(no);
    MemberVO user=userService.findMember();
    if(user.getUser_id().equals(boardVO.getBoard_user_id())){
      boardVO=boardService.modBoard(boardVO.getBoard_sequence());
//        System.out.println(boardvo.getBoard_content());
      model.addAttribute("user", user);
      model.addAttribute("boardVO",boardVO);
      return "board/modwrite";
    }else{
      response.setContentType("text/html; charset=UTF-8");
      response.setCharacterEncoding("UTF-8");
      request.setCharacterEncoding("UTF-8");
      response.getWriter().println("<script> alert('게시글은 작성자만 수정이 가능합니다');history.back(-1);</script>");
      response.getWriter().close();
    }
    return null;
  }
  @PostMapping("/save")
  public String save(@ModelAttribute BoardVO boardVO) throws Exception {
//        System.out.println(boardVO.getBoard_category());
    boardService.modBoard(boardVO);
    return "redirect:/bboard_health";
  }

}

