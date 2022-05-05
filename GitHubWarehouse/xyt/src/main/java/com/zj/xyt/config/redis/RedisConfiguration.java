package com.zj.xyt.config.redis;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;


/**
 * @Author zj970
 * @Date 2022-05-04
 * Redis配置类
 * @EnableCaching注解来开启我们的项目支持缓存
 * 继承CachingConfigurerSupport重写keyGenerator()来自定义key
 */
@Configuration
@EnableCaching
public class RedisConfiguration  extends CachingConfigurerSupport {

}
