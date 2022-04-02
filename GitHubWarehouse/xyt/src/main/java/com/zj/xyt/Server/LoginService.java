package com.zj.xyt.Server;

import com.zj.xyt.Entity.Admin;
import com.zj.xyt.Entity.Student;
import com.zj.xyt.Entity.Teacher;
import io.swagger.annotations.Api;

/**
 *<p>
 *     登录业务类
 *</p>
 * @author zj970
 */
@Api(tags = "验证登录的业务层")
public interface LoginService {
    /**
     * 查询学生账户密码是否存在
     * @param Snu 学生用户账号
     * @param Spd 学生用户密码
     * @return
     */
    Student queryStudent(String Snu,String Spd);

    /**
     * 查询教师账户密码是否存在
     * @param Tnu
     * @param Tpd
     * @return
     */
    Teacher queryTeacher(String Tnu,String Tpd);

    /**
     * 查询管理员账户密码是否存在
     * @param Anu
     * @param Apd
     * @return
     */
    Admin queryAdmin(String Anu, String Apd);

    /**
     * 判断用户是否存在
     * @param nu 账户
     * @param pd 密码
     */
    String queryUser(String nu,String pd);
}
