package com.zj.xyt.realms;

import com.zj.xyt.Entity.Permission;
import com.zj.xyt.Entity.Teacher;
import com.zj.xyt.Server.PermissionService;
import com.zj.xyt.Server.TeacherService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zj970
 * @Description 自定义TeacherRealm
 * @Data 2022/4/22
 */
public class TeacherRealm extends AuthorizingRealm {
    /**
     *在自定义realm中注入的Service声明中加入@Lazy注解即可解决@cacheble注解无效问题
     *解决同时使用redis缓存数据和缓存shiro时，@cacheble无效问题
     */
    @Lazy
    @Autowired
    TeacherService teacherService;
    @Autowired
    PermissionService permissionService;
    //日志实例
    //private static final Logger log = (Logger) LoggerFactory.getLogger(AdminRealm.class);    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("开始teacher权限授权");
        if (principalCollection == null){
            throw new ArithmeticException("PrincipalCollection method argument cannot be null");
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (principalCollection.getPrimaryPrincipal() instanceof Teacher){
           Teacher teacher = (Teacher) principalCollection.getPrimaryPrincipal();
            info.addRole("teacher");
            //log.info(admin.get);
            //每次都从数据中重新查找，确保能及时更新权限
            List<Permission> list = permissionService.queryByID("3");
            Set<String> set = new HashSet<>();
            for (Permission permission : list){
                set.add(permission.getPercode());
            }
            info.setStringPermissions(set);
            System.out.println("当前teacher授权角色：" +info.getRoles() + "，权限：" + info.getStringPermissions());
            return info;
        }
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("开始teacher登录认证。。。。。。。");
        UserToken userToken = (UserToken)authenticationToken;
        System.out.println("=-=-=-=-=-=-="+userToken.getUsername()+userToken.getUserType());
        //1.连接数据库
        Teacher teacher = teacherService.queryTeacherByID(userToken.getUsername());
        if (teacher == null){
            //没有返回登录用户名对应的SimpleAuthorizationInfo对象时，就会在LoginController中抛出UUnknownAccountException
            //return (AuthenticationInfo) new UnknownAccountException("此教师不存在");
            return null;
        }
        else {
            //根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
            //通常需要以下四个参数
            //1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
            Object principal = teacher;
            //2). credentials: 密码.即从数据库中获取的密码
            Object credentials = teacher.getTpd();
            //3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
            String realmName = getName();
            //4). credentialsSalt: 盐值,这里我使用的是用户名
//            /**
//             *模拟加密后的密码为多少
//             */
//            System.out.println("-------------------------------------------------------");
//            String hashAlgorithmName = "MD5";//加密方式
//            Object crdentials = "123456";//密码原值
//            ByteSource salt = ByteSource.Util.bytes(teacher.getTnu());//以账号作为盐值
//            int hashIterations = 1024;//加密1024次
//            Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
//            System.out.println(teacher.getTnu()+"加密后的结果为:"+result);
//            System.out.println("=================================================================");
            ByteSource credentialsSalt = ByteSource.Util.bytes(teacher.getTnu());
            return new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realmName);
        }
    }
}
