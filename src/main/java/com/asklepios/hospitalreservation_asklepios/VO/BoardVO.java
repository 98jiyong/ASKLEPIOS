package com.asklepios.hospitalreservation_asklepios.VO;

import lombok.Data;

@Data
public class BoardVO {
    private String board_sequence=null;
    private String board_user_id=null;
    private String board_category=null;
    private String board_title=null;
    private String board_content=null;
    private String board_binary=null;
    private Integer board_viewcount=null;
    private String board_date=null;
    private Integer board_goodcount=null;



}
