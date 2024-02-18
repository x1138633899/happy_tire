package com.happiness.happy.tire;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.happiness.happy.tire.mapper")
@EnableTransactionManagement
public class HappyTireApplication {
    public static void main(String[] args) {
        SpringApplication.run(HappyTireApplication.class, args);
    }
}
