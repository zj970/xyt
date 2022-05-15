package com.zj.xyt.Server;

import com.zj.xyt.Entity.Admin;
import com.zj.xyt.Entity.Department;
import com.zj.xyt.Entity.StudentVo;
import com.zj.xyt.Entity.TeacherVo;
import com.zj.xyt.utils.PageUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *<p>
 *     管理员服务类
 *</p>
 * @author zj970
 */
@Service
public interface AdminService{
    /**
     * 全查询管理员信息
     */
    List<Admin> queryAdminList();
    /**
     * 根据Anu查询管理员账号
     * @return 返回管理员
     */
    Admin queryAdminByID(@Param("Anu") String Anu);


    /**
     * 获取所有学生信息
     */
    List<StudentVo> queryStudentVoList(@Param("pageUtil") PageUtil pageUtil);

    /**
     * 获取所有教师信息
     */
    List<TeacherVo> queryTeacherVoList(@Param("pageUtil") PageUtil pageUtil);


    /**
     * 获取所有系的信息
     */
    List<Department> getAllDepartment();
}
