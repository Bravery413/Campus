package com.gdufe.campus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gdufe.campus.mapper")
public class CampusApplication {
//测试git
    public static void main(String[] args) {
        SpringApplication.run(CampusApplication.class, args);
    }

}
