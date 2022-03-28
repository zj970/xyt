package com.zj.xyt.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zj
 * @since 2022/3/12 15:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"Anu","Apd"})
public class Admin {
    private String Anu;//管理员账号
    private String Apd;//管理员密码

    public String getAnu(){
        return Anu;
    }

    public String getApd(){
        return Apd;
    }

    public void setAnu(String Anu){
        this.Anu = Anu;
    }

    public void setApd(String Apd){
        this.Apd = Apd;
    }

}
