package com.zust.mallapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zust.mallapi.mapper")
@SpringBootApplication
public class MallApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallApiApplication.class, args);
    }

}
