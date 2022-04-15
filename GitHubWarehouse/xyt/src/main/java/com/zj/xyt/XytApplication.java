package com.zj.xyt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@MapperScan("com.zj.xyt.Mapper")
public class XytApplication{
    public static void main(String[] args) {
            SpringApplication.run(XytApplication.class, args);
    }

}
