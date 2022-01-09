package com.sks.java.twilio.dto.request;

import lombok.Data;

@Data
public class PasswordResetRequest {

    private String phNumber;
    private String username;
    private String otp;

}
