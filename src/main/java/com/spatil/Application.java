package com.spatil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration 
@Configuration 
@ComponentScan("com.agilent")
public class Application { 
 
    public static void main(String[] args) throws Throwable { 
        SpringApplication app = new SpringApplication(Application.class); 
        app.setShowBanner(false); 
        app.run(args); 
    } 
    
} 
