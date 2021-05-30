package com.syc.service.impl;

import com.syc.dao.NoticeDao;
import com.syc.pojo.Notice;
import com.syc.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeDao noticeDao;
    @Override
    public void addNotice(Notice notice) {
        noticeDao.addNotice(notice);
    }

    @Override
    public List<Notice> selAllNotice() {
        return noticeDao.selAllNotice();
    }

    @Override
    public void delNoticeByID(Integer id) {
        noticeDao.delNoticeByID(id);
    }

    @Override
    public void increase(Integer id) {
        noticeDao.increase(id);
    }
}
