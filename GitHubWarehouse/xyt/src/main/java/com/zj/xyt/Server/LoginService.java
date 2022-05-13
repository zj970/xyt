package com.zj.xyt.Server;

import com.zj.xyt.Entity.Admin;
import com.zj.xyt.Entity.Student;
import com.zj.xyt.Entity.Teacher;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 *<p>
 *     登录业务类
 *</p>
 * @author zj970
 */
@Service
@Api(tags = "验证登录的业务层")
public interface LoginService {
    /**
     * 查询学生账户密码是否存在
     * @param Snu 学生用户账号
     * @param Spd 学生用户密码
     * @return
     */
    Student queryStudent(@Param("Snu") String Snu, @Param("Spd")String Spd);

    /**
     * 查询教师账户密码是否存在
     * @param Tnu
     * @param Tpd
     * @return
     */
    Teacher queryTeacher(@Param("Tnu")String Tnu,@Param("Tpd")String Tpd);

    /**
     * 查询管理员账户密码是否存在
     * @param Anu
     * @param Apd
     * @return
     */
    Admin queryAdmin(@Param("Anu")String Anu,@Param("Apd")String Apd);

    /**
     * 判断用户是否存在
     * @param nu 账户
     * @param pd 密码
     */
    String queryUser(String nu,String pd);

    /**
     * 修改密码
     * @param Snu 学生用户账号
     * @param Spd 学生用户密码
     * @return
     */
    void updateStudent(@Param("Snu") String Snu, @Param("Spd")String Spd);

    /**
     * 修改密码
     * @param Tnu
     * @param Tpd
     * @return
     */
    void updateTeacher(@Param("Tnu")String Tnu,@Param("Tpd")String Tpd);

    /**
     * 修改密码
     * @param Anu
     * @param Apd
     * @return
     */
    void updateAdmin(@Param("Anu")String Anu,@Param("Apd")String Apd);

}
