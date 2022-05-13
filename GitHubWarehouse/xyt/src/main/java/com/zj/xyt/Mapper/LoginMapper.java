package com.zj.xyt.Mapper;
import com.zj.xyt.Entity.Admin;
import com.zj.xyt.Entity.Student;
import com.zj.xyt.Entity.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {
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
