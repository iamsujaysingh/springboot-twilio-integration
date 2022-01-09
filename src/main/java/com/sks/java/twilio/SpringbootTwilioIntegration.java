package com.sks.java.twilio;

import com.sks.java.twilio.config.TwilioConfiguration;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringbootTwilioIntegration {

    @Autowired
    private TwilioConfiguration twilioConfiguration;

    @PostConstruct
    public void init(){
        Twilio.init(twilioConfiguration.getSid(),twilioConfiguration.getAuthToken());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootTwilioIntegration.class, args);
    }
}
