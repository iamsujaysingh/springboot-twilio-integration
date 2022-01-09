package com.sks.java.twilio.resource.handler;

import com.sks.java.twilio.dto.request.PasswordResetRequest;
import com.sks.java.twilio.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class OTPHandler {

    @Autowired
    private OTPService otpService;

    public Mono<ServerResponse> sendOtp(ServerRequest serverRequest){
        return serverRequest.bodyToMono(PasswordResetRequest.class)
                .flatMap(dto->otpService.sendOtpForPassowrdReset(dto))
                .flatMap(dto->ServerResponse.status(HttpStatus.OK)
                .body(BodyInserters.fromValue(dto)));
    }

    public Mono<ServerResponse> validateOTP(ServerRequest serverRequest){
        return serverRequest.bodyToMono(PasswordResetRequest.class)
                .flatMap(dto->otpService.validateOTP(dto.getOtp(),dto.getUsername()))
                .flatMap(dto->ServerResponse.status(HttpStatus.OK)
                .bodyValue(dto));
    }
}
