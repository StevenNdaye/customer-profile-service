package com.soap.customerprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by steven on 2014/11/09.
 */
@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

}
