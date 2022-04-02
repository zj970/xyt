package com.zj.xyt.Mapper;
import com.zj.xyt.Entity.Admin;
import com.zj.xyt.Entity.Student;
import com.zj.xyt.Entity.Teacher;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Mapper;

@Mapper
@Api(tags = "验证登录的持久层")
public interface LoginMapper {
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
    Admin queryAdmin(String Anu,String Apd);
}
