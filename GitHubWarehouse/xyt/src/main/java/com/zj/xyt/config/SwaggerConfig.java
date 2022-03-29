package com.zj.xyt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    //配置Swagger的Docket的Bean实例
    public Docket docket(Environment environment){
        //设置要显示的Seagger环境
        Profiles profiles = Profiles.of("dev");
        //获取项目的环境：通过environment.acceptsProfiles判断是否处于自己设定的环境中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("周健")
                .enable(!flag)//是否启用Swagger,默认是true,false则不能访问swaggeer
                .select()
                //配置要扫描接口的方式RequestHandlerSelectors
                //指定扫描的包basePackage
                //any():扫描全部
                //none():都不扫描
                //withClassAnnotation:扫描类上的注解，参数是一个注解的反射对象
                //withMethodAnnotation : 扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.zj.xyt.Controller"))
                //paths() 过滤什么路径
                //.paths(PathSelectors.ant("/swaggerdemo/**"))
                .build();//build建造者模式
    }
    //配置Swagger信息=apiInfo
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("周健","","3060529292@qq.com");

        return new ApiInfo("校园通接口测试",
                "天行健，君子当以自强不息",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/license/LICENSE-2.0",
                new ArrayList());
    }
}
