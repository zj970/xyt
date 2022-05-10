package com.zj.xyt.Server;

import com.zj.xyt.Entity.Classes;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *<p>
 *     班级业务类
 *</p>
 * @author zj970
 */
@Service
@Api(tags = "班级业务层")
public interface ClassService {
    /**
     * 查询所有班级
     */
    List<Classes> getAll();

    /**
     * 查询学生所在班级
     */
    Classes queryByCnu(String Cnu);
}