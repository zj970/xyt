package com.zj.xyt.Mapper;

import com.zj.xyt.Entity.Notice;
import com.zj.xyt.utils.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NoticeMapper {

    int addNotice(Notice notice) throws Exception;

    int updateNotice(Notice notice) throws Exception;

    int deleteNotice(Notice notice);

    int deleteNoticeList(@Param("list")List<Integer> list);

    int getCountByTypeAndNotice(@Param("type") int type, @Param("notice") Notice notice);

    int getCountByType(@Param("type")int type,@Param("searchKey")String searchKey) throws Exception;


    List<Notice> getNoticeListByType(@Param("type")int type, @Param("searchKey")String searchKey, @Param("pageUtil")PageUtil pageUtil) throws Exception;

    List<Notice> getNoticeListByTypeAndNotice(@Param("type")int type, @Param("notice")Notice notice, @Param("pageUtil")PageUtil pageUtil);

    List<Notice> getNoticeById(Integer id);

}
