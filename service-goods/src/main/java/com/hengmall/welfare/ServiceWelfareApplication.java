package com.hengmall.welfare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.hengmall.welfare.mapper")
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class ServiceWelfareApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceWelfareApplication.class, args);
    }
}
