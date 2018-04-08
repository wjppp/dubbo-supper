package com.jk;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@ImportResource(locations={"classpath:dubbo_provider.xml","classpath:spring-mybatis.xml"})
@EnableTransactionManagement//事务管理
@SpringBootApplication
public class StartApp {

    public static void main(String[] args) {

        SpringApplication.run(StartApp.class, args);

    }

}
