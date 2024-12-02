package com.asklepios.hospitalreservation_asklepios.VO;


import lombok.Data;

@Data
public class LikeVO {
    private String board_user_id;
    private boolean liked;
    private String board_sequence;
    private String like_id;


}
