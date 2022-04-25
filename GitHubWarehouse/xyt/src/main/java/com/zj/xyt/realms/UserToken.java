package com.zj.xyt.realms;

import com.zj.xyt.utils.UserType;
import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author zj970
 * @Description 用于登录时判断用户类型，替代UsernamePasswordToken
 * @Data 2022/4/22
 */
@Data
public class UserToken extends UsernamePasswordToken {
    //登录类型
    private UserType userType;
    public UserToken(final String username, final String password, UserType userType){
        super(username,password);
        this.userType = userType;
    }
}
