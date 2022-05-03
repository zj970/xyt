package com.zj.xyt.Server;

import com.zj.xyt.Entity.Notice;
import com.zj.xyt.utils.PageUtil;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *<p>
 *     公告业务类
 *</p>
 * @author zj970
 */
@Service
@Api(tags = "公告业务层")
public interface NoticeService {

    int addNotice(@Param("notice")Notice notice) throws Exception;

    int updateNotice(@Param("notice")Notice notice) throws Exception;

    int deleteNotice(@Param("notice")Notice notice);

    int deleteNoticeList(@Param("list")List<Integer> list);

    int getCountByTypeAndNotice(int type, Notice notice);

    int getCountByType(@Param("type")int type, @Param("searchKey")String searchKey) throws Exception;

    List<Notice> getNoticeListByType(int type, String searchKey, PageUtil pageUtil) throws Exception;

    List<Notice> getNoticeListByTypeAndNotice(int type, Notice notice, PageUtil pageUtil);

    List<Notice> getNoticeById(Integer id);
}
