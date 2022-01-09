package com.sks.java.twilio.resource.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private OTPHandler otpHandler;

    @Bean
    public RouterFunction<ServerResponse> handleSMS(){
        return RouterFunctions.route()
                .POST("/router/sendOtp",otpHandler::sendOtp)
                .POST("/router/validateOtp",otpHandler::validateOTP)
                .build();

    }
}
