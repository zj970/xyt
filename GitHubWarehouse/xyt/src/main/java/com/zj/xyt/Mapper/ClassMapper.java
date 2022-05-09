package com.zj.xyt.Mapper;

import com.zj.xyt.Entity.Classes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zj970
 */
@Repository
@Mapper
public interface ClassMapper {
    /**
     * 查询所有班级
     */
    List<Classes> getAll();

    /**
     * 查询学生所在班级
     */
    Classes queryByCnu(@Param("Cnu") String Cnu);
}
