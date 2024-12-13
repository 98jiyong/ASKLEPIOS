package com.asklepios.hospitalreservation_asklepios.VO;

import lombok.*;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private String user_id;
    private String user_password;
    private String user_name;
    private String user_name_eng;
    private String user_register_number;
    private String user_tel;
    private String user_addr;
    private String user_email;
    private String user_authority;
    private String user_info_agreement;
    private String user_image;

    // 값을 불러올때 분리해서 불러오기 위함
    private String user_name_eng_first;
    private String user_name_eng_last;

    private String user_tel_first;
    private String user_tel_second;
    private String user_tel_third;

    private String user_email_first;
    private String user_email_last;

    private String user_addr_first;
    private String user_addr_second;
    private String user_addr_third;
    private String user_addr_fourth;

    public void divideEngName() {
        if(this.user_name_eng != null && !this.user_name_eng.isEmpty()) {
            String[] divideEngName = this.user_name_eng.split(" ");
            this.user_name_eng_first = divideEngName[0];
            this.user_name_eng_last = divideEngName[1];
        }
    };
    public void divideTel() {
        if (this.user_tel != null && !this.user_tel.isEmpty()) {
            String[] divideTel = this.user_tel.split("-");
            this.user_tel_first = divideTel[0];
            this.user_tel_second = divideTel[1];
            this.user_tel_third = divideTel[2];
        }
    };
    public void divideAddr() {
        if (this.user_addr != null && !this.user_addr.isEmpty()) {
            String[] divideAddr = this.user_addr.split(",");
            this.user_addr_first = divideAddr[0];
            this.user_addr_second = divideAddr[1];
            this.user_addr_third = divideAddr[2];
            this.user_addr_fourth = divideAddr[3];
        }
    };
    public void divideEmail() {
        if (this.user_email != null && !this.user_email.isEmpty()) {
            String[] divideEmail = this.user_email.split("@");
            this.user_email_first = divideEmail[0];
            this.user_email_last = divideEmail[1];
        }
    };


}
