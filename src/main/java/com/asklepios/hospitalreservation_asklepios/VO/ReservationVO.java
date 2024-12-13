package com.asklepios.hospitalreservation_asklepios.VO;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReservationVO {

  private String reservation_code;
  private String reservation_hospital_code;
  private String reservation_user_doctor_code;
  private String reservation_user_id;
  private String reservation_date;
  private String reservation_time;
  private String reservation_memo;
  private String reservation_accept;
}
