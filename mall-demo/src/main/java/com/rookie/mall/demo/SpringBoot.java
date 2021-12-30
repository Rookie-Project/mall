package com.rookie.mall.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ganxinming
 * @createDate 2021/12/27
 * @description
 */
@SpringBootApplication
@MapperScan("com.rookie.mall.demo.dao")
public class SpringBoot {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot.class, args);
    }
}
