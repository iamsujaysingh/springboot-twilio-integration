package com.sks.java.twilio.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Data
public class TwilioConfiguration {

    private String sid;
    private String authToken;
    private String phNumber;

}
