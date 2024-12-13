package com.asklepios.hospitalreservation_asklepios.VO;

import lombok.Data;

@Data
public class ReviewVO {
  private String review_sequence;
  private String review_user_id;
  private String review_hospital_code;
  private String review_content;
  private String review_date;
  private int review_score;

}
