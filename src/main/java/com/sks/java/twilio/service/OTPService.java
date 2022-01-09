package com.sks.java.twilio.service;

import com.sks.java.twilio.config.TwilioConfiguration;
import com.sks.java.twilio.dto.request.PasswordResetRequest;
import com.sks.java.twilio.dto.response.OtpStatus;
import com.sks.java.twilio.dto.response.PasswordResetResponse;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OTPService {

    @Autowired
    private TwilioConfiguration twilioConfiguration;

    Map<String,String> otpMap = new HashMap();

    public Mono<PasswordResetResponse> sendOtpForPassowrdReset(PasswordResetRequest passwordResetRequest){

        PasswordResetResponse passwordResetResponse = null;

        try{

            PhoneNumber receiver = new PhoneNumber(passwordResetRequest.getPhNumber());
            PhoneNumber sender = new PhoneNumber(twilioConfiguration.getPhNumber());
            String otp = generateOtp();
            String otpMessage = "Hello , your OTP is "+otp+" Please don not share this with anyone.";
            Message message = Message
                    .creator(receiver, sender,
                            otpMessage)
                    .create();
            otpMap.put(passwordResetRequest.getUsername(),otp);
            passwordResetResponse = new PasswordResetResponse(OtpStatus.Delivered,otpMessage);
        }
        catch (Exception e){
            passwordResetResponse = new PasswordResetResponse(OtpStatus.Failed,e.getMessage());
        }

        return Mono.just(passwordResetResponse);

    }

    public Mono<String> validateOTP(String userOtp,String userName){
        if(otpMap.get(userName).equals(userOtp)){
            otpMap.remove(userName,userOtp);
            return Mono.just("Valid OTP. Please proceed with your trnasaction");
        }
        else
            return Mono.error(new IllegalArgumentException("Invalid otp , please try again"));
    }

    //6 digit otp;
    private String generateOtp(){
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }
}
