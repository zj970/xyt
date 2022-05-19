package com.zj.xyt.config.shiro;

import com.zj.xyt.Entity.Permission;
import com.zj.xyt.Server.PermissionService;
import com.zj.xyt.realms.*;
import com.zj.xyt.utils.MyFormAuthenticationFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zj970
 * @Description 自定义AdminRealm
 * @Data 2022/4/22
 */
@Configuration
public class ShiroConfiguration {
    //TODO:日志实例出现问题
    //static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);
    @Autowired
    PermissionService permissionService;
    //获取application.properties参数
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     *
     * 部分过滤器可指定参数，如perms，roles
     * @param securityManager
     * @return
     */
    //ShiroFilterFactoryBean 过滤的对象 3
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager ){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        System.out.println("ShiroConfiguration.shiroFilter()");
        Map<String, Filter> filters = bean.getFilters();//获取filters
        //设置SecuritManager
        //设置安全管理器
        bean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        //bean.setLoginUrl("/login");
        //添加shiro的内置过滤器
        /*
         *   anon:无需认证就可以访问
         *   authc:必须认证才可以访问
         *   user:必须拥有 记住我 功能才能用
         *   perms:拥有对某个资源的权限才能访问
         *   role；拥有某个角色权限才能访问
         *         filterChainDefinitionMap.put("/user/add","authc");
         *         filterChainDefinitionMap.put("/user/update","authc");
         * */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/logout", "logout");
        //放行静态资源
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/layui/**", "anon");
        filterChainDefinitionMap.put("/lib/**", "anon");
        //放行swagger
        filterChainDefinitionMap.put("/swagger-ui/**","anon");
        filterChainDefinitionMap.put("/swagger/**","anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**","anon");
        filterChainDefinitionMap.put("/v2/**","anon");
        filterChainDefinitionMap.put("/druid/**","anon");
        //放行测试页面
        filterChainDefinitionMap.put("/test/**","anon");
        //验证码可以匿名访问
        filterChainDefinitionMap.put("/validatecode.jsp", "anon");
        filterChainDefinitionMap.put("/refuse.jsp", "anon");
        //授权
        //filterChainDefinitionMap.put("/admin/**", "roles[admin]");
        //filterChainDefinitionMap.put("/teacher/**", "roles[teacher]");
        //filterChainDefinitionMap.put("/student/**", "roles[student]");
        filters.put("authc", new MyFormAuthenticationFilter());//将自定义的FormAuthenticationFilter注入shiroFilter中
        //从数据库获取所有的权限
//        List<Permission> list = permissionService.queryAll();
//        for (Permission permission :list){
//            filterChainDefinitionMap.put(permission.getUrl(),permission.getPercode());
//        }

        // <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**","authc");
        System.out.println("拦截器链：" + filterChainDefinitionMap);
        //传入未登录用户访问登录
        //如果没有权限登录
        bean.setLoginUrl("/login");

        //设置成功后后返回页面
        bean.setSuccessUrl("/success");
        //未授权页面
        bean.setUnauthorizedUrl("/unauth");

        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);


        return bean;
    }
    //defaultWebSecurityManager 管理对象 2
    //SecurityManager 是 Shiro 架构的核心，通过它来链接Realm和用户(文档中称之为Subject.)
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("adminRealm") AdminRealm adminRealm,@Qualifier("studentRealm")StudentRealm studentRealm,@Qualifier("teacherRealm")TeacherRealm teacherRealm) {
        DefaultWebSecurityManager  securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(modularRealmAuthenticator());
        securityManager.setSessionManager(sessionManager());
        //设置realm.
        List<Realm> realms = new ArrayList<>();
        //添加多个Realm
        realms.add(adminRealm);
        realms.add(teacherRealm);
        realms.add(studentRealm);
        securityManager.setRealms(realms);
        // 自定义缓存实现 使用redis
        //securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        //securityManager.setSessionManager(sessionManager());
        //注入记住我管理器;
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }
    /**
     * 系统自带的Realm管理，主要针对多realm
     * */
    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        //自己重写的ModularRealmAuthenticator
        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

    //创建Realm对象,需要自定义 1
    @Bean(name = "adminRealm")
    public AdminRealm adminRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher){
        AdminRealm adminRealm = new AdminRealm();
        //设置解密规则
        //adminRealm.setCredentialsMatcher(hashedCredentialsMatcher());//设置解密规则
        adminRealm.setCredentialsMatcher(matcher);
        return adminRealm;
    }
    @Bean(name = "studentRealm")
    public StudentRealm studentRealm(){
        StudentRealm studentRealm = new StudentRealm();
        //设置解密规则
        studentRealm.setCredentialsMatcher(hashedCredentialsMatcher());//设置解密规则
        return studentRealm;
    }
    @Bean(name = "teacherRealm")
    public TeacherRealm teacherRealm(){
        TeacherRealm teacherRealm = new TeacherRealm();
        //设置解密规则
        teacherRealm.setCredentialsMatcher(hashedCredentialsMatcher());//设置解密规则
        return teacherRealm;
    }
    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisCacheManager cacheManager() {
        System.out.println("创建RedisCacheManager...");
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }
    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisManager redisManager() {
        System.out.println("创建shiro redisManager,连接Redis..URL= " + host + ":" + port);
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setExpire(1800);// 配置缓存过期时间
        redisManager.setTimeout(timeout);
        // redisManager.setPassword(password)
        return redisManager;
    }
    /**
     * Session Manager
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());//缓存失败
        sessionManager.setGlobalSessionTimeout(3600000L);//设置有效期
        return sessionManager;
    }
    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * cookie管理对象;记住我功能
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

    /**
     * cookie对象;
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }
    //因为我们的密码是加过密的，所以，如果要Shiro验证用户身份的话，需要告诉它我们用的是md5加密的，并且是加密了两次。同时我们在自己的Realm中也通过SimpleAuthenticationInfo返回了加密时使用的盐。这样Shiro就能顺利的解密密码并验证用户名和密码是否正确了。
    /***
     * 替换当前Realm的credentialsMatcher属性
     * 直接使用 HashedCredentialsMatcher 对象，并设置加密
     * @return
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }
}

