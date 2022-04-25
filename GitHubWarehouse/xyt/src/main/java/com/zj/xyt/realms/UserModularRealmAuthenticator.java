package com.zj.xyt.realms;

import com.zj.xyt.utils.UserType;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 当配置了多个Realm时，我们通常使用的认证器是shiro自带的org.apache.shiro.authc.pam.ModularRealmAuthenticator,其中决定使用的是Realm的doAuthenticate()方法
 *
 * 自定义Authenticator
 * 注意：当需要分别定义处理学生、教师、管理员验证的realm时，对应的reaml的全类名应该包含字符串"student","admin","teacher"
 * 并且，他们不能相互包含，例如，处理学生的realm的全类名不应该包含字符串”admin"
 */
public class UserModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.判断getRealms()是否为空
        assertRealmsConfigured();
        //2.强制转换回定义的userToken
        UserToken userToken = (UserToken) authenticationToken;
        //3.获取登录类型
        UserType userType = userToken.getUserType();
        //所有的Realm
        Collection<Realm> realms = getRealms();
        //登录类型对应的所有Realm
        Collection<Realm> typeReamls = new ArrayList<>();
        for (Realm realm : realms){
            //4.根据Reaml的类名和UserType对比决定那个reaml起作用
            if (realm.getName().contains(userType.toString()))
            {
                typeReamls.add(realm);
            }
        }
        //5.判断是单realm还是多realm
        if (typeReamls.size() == 1){
            return doSingleRealmAuthentication(typeReamls.iterator().next(),userToken);
        }else {
            return doMultiRealmAuthentication(typeReamls,userToken);
        }
    }
}
