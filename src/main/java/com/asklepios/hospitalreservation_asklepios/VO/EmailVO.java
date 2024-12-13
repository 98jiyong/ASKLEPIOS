package com.asklepios.hospitalreservation_asklepios.VO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmailVO {
    private String to;
    private String subject;
    private String message;
}
