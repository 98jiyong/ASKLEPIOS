package com.asklepios.hospitalreservation_asklepios.VO;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DoctorVO {
    private String user_doctor_code;
    private String user_doctor_id;
    private String user_doctor_hospital_code;
    private String user_doctor_medical;
    private String user_doctor_career;
    private String user_doctor_graduate;
}
