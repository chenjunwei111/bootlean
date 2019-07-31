package com.cjw.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
* Description SpringBoot启动类文件
* @Author junwei
* @Date 9:35 2019/7/31
**/
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@MapperScan(value = "com.cjw.boot.mapper")
public class BootApplication  {

    public static void main(String[] args) {
            SpringApplication.run(BootApplication.class, args);
            System.out.println("=================================");
            System.out.println("=============启动成功===============");
            System.out.println("=================================");
            System.out.println("http://localhost:8081/boot/");
    }

}
