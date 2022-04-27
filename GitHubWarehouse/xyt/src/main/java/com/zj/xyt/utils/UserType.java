package com.zj.xyt.utils;

/**
 * 用于判断用户类型
 * @author zj970
 */
public enum UserType {
    STUDENT("Student"),  ADMIN("Admin"), TEACHER("Teacher");
    private String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
