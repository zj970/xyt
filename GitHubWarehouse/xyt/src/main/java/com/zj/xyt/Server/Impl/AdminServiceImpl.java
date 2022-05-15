package com.zj.xyt.Server.Impl;

import com.zj.xyt.Entity.Admin;
import com.zj.xyt.Entity.Department;
import com.zj.xyt.Entity.StudentVo;
import com.zj.xyt.Entity.TeacherVo;
import com.zj.xyt.Mapper.AdminMapper;
import com.zj.xyt.Server.AdminService;
import com.zj.xyt.utils.PageUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Api(tags = "管理员的业务实现层")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> queryAdminList() {
        return adminMapper.queryAdminList();
    }

    @Override
    public Admin queryAdminByID(String Anu) {
        return adminMapper.queryAdminByID(Anu);
    }

    @Override
    public List<StudentVo> queryStudentVoList(PageUtil pageUtil) {
        return adminMapper.queryStudentVoList(pageUtil);
    }

    @Override
    public List<TeacherVo> queryTeacherVoList(PageUtil pageUtil) {
        return adminMapper.queryTeacherVoList(pageUtil);
    }

    @Override
    public List<Department> getAllDepartment() {
        return adminMapper.getAllDepartment();
    }
}
