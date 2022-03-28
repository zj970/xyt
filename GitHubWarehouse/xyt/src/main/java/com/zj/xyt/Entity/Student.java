package com.zj.xyt.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

/**
 * @author zj
 * @creatDate 2022/1/12 15:00
 */
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    /**学生学号--账号*/
    private String Snu;
    /**学生姓名*/
    private String Sname;
    /**学生性别(男false女true)*/
    private boolean Ssex;
    /**学生出生日期*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Sbirth;
    /**班级编号*/
    private String Cnu;
    /**学生总学分*/
    private float Scredit;
    /**学生入学时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  Date Sette;
    /**学生账号密码*/
    private String Spd;

    public String getSnu() {
        return Snu;
    }

    public void setSnu(String snu) {
        Snu = snu;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public boolean isSsex() {
        return Ssex;
    }

    public void setSsex(boolean ssex) {
        Ssex = ssex;
    }

    public Date getSbirth() {
        return Sbirth;
    }

    public void setSbirth(Date sbirth) {
        Sbirth = sbirth;
    }

    public String getCnu() {
        return Cnu;
    }

    public void setCnu(String cnu) {
        Cnu = cnu;
    }

    public float getScredit() {
        return Scredit;
    }

    public void setScredit(float scredit) {
        Scredit = scredit;
    }

    public Date getSette() {
        return Sette;
    }

    public void setSette(Date sette) {
        Sette = sette;
    }

    public String getSpd() {
        return Spd;
    }

    public void setSpd(String spd) {
        Spd = spd;
    }
}
