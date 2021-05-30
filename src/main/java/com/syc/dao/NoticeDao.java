package com.syc.dao;

import com.syc.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoticeDao {
    //添加通知
    void addNotice(Notice notice);
    //查询所有通知
    List<Notice> selAllNotice();
    //根据id删除通知
    void delNoticeByID(Integer id);
    //
    void increase(Integer id);
}
