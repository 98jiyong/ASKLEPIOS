package com.asklepios.hospitalreservation_asklepios.Controller;

import com.asklepios.hospitalreservation_asklepios.Service.IF_BoardService;
import com.asklepios.hospitalreservation_asklepios.Service.LikeService;
import com.asklepios.hospitalreservation_asklepios.VO.BoardVO;
import com.asklepios.hospitalreservation_asklepios.VO.LikeVO;
import com.asklepios.hospitalreservation_asklepios.VO.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



import java.util.List;


import static org.apache.tomcat.util.http.fileupload.FileUtils.*;

@Controller
public class HomeController {
    @Autowired
    IF_BoardService boardService;
    LikeService likeService;
    @GetMapping("/home")
    public String main(){
        return "home";
    }

    @GetMapping("/bboard_all")
    public String board_all(Model model, @ModelAttribute PageVO pagevo) throws Exception{
        if(pagevo.getPage()==null){
            pagevo.setPage(1);
        }
        pagevo.setTotalCount(boardService.boardCount());
        List<BoardVO> boardlist=boardService.boardAll(pagevo);
        List<BoardVO> noticelist=boardService.boardNoticeList();
        model.addAttribute("boardlist", boardlist);
        model.addAttribute("noticelist", noticelist);
        return "board/main";
    }

    @GetMapping("/bboard_health")
    public String board_health(Model model, @ModelAttribute PageVO pagevo) throws Exception {
        if(pagevo.getPage()==null){
            pagevo.setPage(1);
        }
        pagevo.setTotalCount(boardService.boardCount());
        List<BoardVO> boardlist=boardService.boardHealthList(pagevo);
//        System.out.println(pagevo.getPage());
//        System.out.println(pagevo.getStartNo()+"/"+pagevo.getEndNo());
//        System.out.println(pagevo.isNext());
//        System.out.println(pagevo.isPrev());

        model.addAttribute("boardlist",boardlist);
        return "board/main";
    }
    @GetMapping("/bboard_campaign")
    public String board_cam(Model model, @ModelAttribute PageVO pageVO ) throws Exception {
        if(pageVO.getPage()==null){
            pageVO.setPage(1);
        }
        pageVO.setTotalCount(boardService.boardCount());
        List<BoardVO> boardlist=boardService.boardCampaignList( pageVO);

        model.addAttribute("boardlist",boardlist);
        return "board/main";
    }
    @GetMapping("/bboard_med")
    public String board_med(Model model , @ModelAttribute PageVO pagevo) throws Exception {
        if(pagevo.getPage()==null){
            pagevo.setPage(1);
        }
        pagevo.setTotalCount(boardService.boardCount());
        List<BoardVO> boardlist=boardService.boardMedList( pagevo);
        model.addAttribute("boardlist",boardlist);
        return "board/main";
    }
    @GetMapping("/bboard_free")
    public String board_free(Model model , @ModelAttribute PageVO pagevo) throws Exception {
        if(pagevo.getPage()==null){
            pagevo.setPage(1);
        }
        pagevo.setTotalCount(boardService.boardCount());
        List<BoardVO> boardlist=boardService.boardFreeList(pagevo);
        model.addAttribute("boardlist",boardlist);
        return "board/main";
    }
    @GetMapping("bboard/write")
    public String write(){
        return "board/write";
    }

    @PostMapping("bboard/submitwrite")
    public String submitWrite(@ModelAttribute BoardVO boardVO
                              ) throws Exception {

        boardService.addBoard(boardVO);
        return "redirect:/bboard_all";

    }
    @GetMapping("/detail")
    public String detail(Model model, @ModelAttribute PageVO pagevo,
                         @RequestParam("no") String no) throws Exception {
        BoardVO boardVO=boardService.detail(no);
        model.addAttribute("boardVO",boardVO);
        return "board/detail";
    }
    @GetMapping("/modboard")
    public String mod(Model model,@ModelAttribute BoardVO boardVO
                      ) throws Exception {
//        System.out.println(no);
         boardVO=boardService.modBoard(boardVO.getBoard_sequence());
//        System.out.println(boardvo.getBoard_content());
        model.addAttribute("boardVO",boardVO);
        return "board/modwrite";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute BoardVO boardVO) throws Exception {
//        System.out.println(boardVO.getBoard_category());
        boardService.modBoard(boardVO);
        return "redirect:/bboard_health";
    }
}
