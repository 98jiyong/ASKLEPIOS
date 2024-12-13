package com.asklepios.hospitalreservation_asklepios.VO;

import lombok.*;
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReservationStatusVO {
    String reservation_code;
    String hospital_name;
    String user_name;
    String reservation_date;
    String reservation_time;
    String reservation_memo;
    String reservation_accept;
}
