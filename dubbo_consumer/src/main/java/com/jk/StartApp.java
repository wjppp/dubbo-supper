package com.jk;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:dubbo_customer.xml")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class StartApp {

    public static void main(String[] args) {

        SpringApplication.run(StartApp.class, args);

    }

}
