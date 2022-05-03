package com.zj.xyt.Server.Impl;

import com.zj.xyt.Entity.Notice;
import com.zj.xyt.Mapper.NoticeMapper;
import com.zj.xyt.Server.NoticeService;
import com.zj.xyt.utils.PageUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Api(tags = "公告的业务实现层")
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public int addNotice(Notice notice) throws Exception {
        return noticeMapper.addNotice(notice);
    }

    @Override
    public int updateNotice(Notice notice) throws Exception {
        return noticeMapper.updateNotice(notice);
    }

    @Override
    public int deleteNotice(Notice notice) {
        return noticeMapper.deleteNotice(notice);
    }

    @Override
    public int deleteNoticeList(List<Integer> list) {
        return noticeMapper.deleteNoticeList(list);
    }

    @Override
    public int getCountByTypeAndNotice(int type, Notice notice) {
        return noticeMapper.getCountByTypeAndNotice(type, notice);
    }

    @Override
    public int getCountByType(int type, String searchKey) throws Exception {
        return noticeMapper.getCountByType(type, searchKey);
    }

    @Override
    public List<Notice> getNoticeListByType(int type, String searchKey, PageUtil pageUtil) throws Exception {
        return noticeMapper.getNoticeListByType(type, searchKey, pageUtil);
    }

    @Override
    public List<Notice> getNoticeListByTypeAndNotice(int type, Notice notice, PageUtil pageUtil) {
        return noticeMapper.getNoticeListByTypeAndNotice(type, notice, pageUtil);
    }

    @Override
    public List<Notice> getNoticeById(Integer id) {
        return noticeMapper.getNoticeById(id);
    }
}
