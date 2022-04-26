package com.zj.xyt.realms;

import com.zj.xyt.Entity.Admin;
import com.zj.xyt.Server.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
/**
 * @author zj970
 * @Description 自定义AdminRealm
 * @Data 2022/4/22
 */
public class AdminRealm extends AuthorizingRealm {
    //private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AdminRealm.class);
    /**
     *在自定义realm中注入的Service声明中加入@Lazy注解即可解决@cacheble注解无效问题
     *解决同时使用redis缓存数据和缓存shiro时，@cacheble无效问题
     */
    @Lazy
    @Autowired
    AdminService adminService;
    //日志实例
    //private static final Logger log = (Logger) LoggerFactory.getLogger(AdminRealm.class);
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("开始admin权限授权");
        if (principalCollection == null){
            throw new ArithmeticException("PrincipalCollection method argument cannot be null");
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (principalCollection.getPrimaryPrincipal() instanceof Admin){
            Admin admin = (Admin) principalCollection.getPrimaryPrincipal();
            System.out.println("Admin:"+admin);
            info.addRole("admin");
            //每次都从数据中重新查找，确保能及时更新权限
            //log.info(admin.get);
            System.out.println("当前Admin授权角色：" +info.getRoles() + "，权限：" + info.getStringPermissions());
            return info;
        }
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("开始admin登录认证。。。。。。。");
        UserToken userToken = (UserToken)authenticationToken;
        //1.连接数据库 从userToken中获取账号
        System.out.println("=-=-=-=-=-=-="+userToken.getUsername());
        Admin admin = adminService.queryAdminByID(userToken.getUsername());
        if (admin == null){
            System.out.println("此管理员不存在");
            //没有返回登录用户名对应的SimpleAuthorizationInfo对象时，就会在LoginController中抛出UUnknownAccountException
            return (AuthenticationInfo) new UnknownAccountException("此管理员不存在");
        }else {
            System.out.println("此管理员存在");
            //根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
            //通常需要以下四个参数
            //1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
            Object principal = admin;
            //2). credentials: 密码.即从数据库中获取的密码
            Object credentials = admin.getApd();
            //3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
            String realmName = getName();
            //4). credentialsSalt: 盐值,这里我使用的是用户名
            ByteSource credentialsSalt = ByteSource.Util.bytes(admin.getAnu());

            return new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realmName);
        }
    }
}
