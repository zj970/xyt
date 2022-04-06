package com.zj.xyt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@SpringBootApplication
@MapperScan("com.zj.xyt.Mapper")
public class XytApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(XytApplication.class, args);
        }catch (Exception e){
            System.out.println("报错原因");
            e.printStackTrace();
            log.println(e);
            System.out.println("报错原因");
        }

    }

}
