package com.spring.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@ComponentScan
@EntityScan(basePackageClasses = {
        EdgeServiceApplication.class,
        Jsr310JpaConverters.class
}, basePackages = "com.spring.demo.registration.model")
@EnableZuulProxy
public class EdgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdgeServiceApplication.class, args);
    }

    @PostConstruct
    void Init(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }


}
