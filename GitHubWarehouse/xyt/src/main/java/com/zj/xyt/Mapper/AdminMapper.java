package com.zj.xyt.Mapper;

import com.zj.xyt.Entity.Admin;
import com.zj.xyt.Entity.Department;
import com.zj.xyt.Entity.StudentVo;
import com.zj.xyt.Entity.TeacherVo;
import com.zj.xyt.utils.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zj970
 */
@Repository
@Mapper
public interface AdminMapper {
    /**
     * 查询所有的管理员账号
     * @return 返回所有管理员
     */
    List<Admin> queryAdminList();

    /**
     * 根据Anu查询管理员账号
     * @return 返回管理员
     */
    Admin queryAdminByID(@Param("Anu")String Anu);

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
