package com.syc.service;

import com.syc.pojo.Notice;

import java.util.List;

public interface NoticeService {
    void addNotice(Notice notice);
    List<Notice> selAllNotice();
    void delNoticeByID(Integer id);
    void increase(Integer id);
}
