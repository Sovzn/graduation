package com.syc.controller;

import com.syc.pojo.Notice;
import com.syc.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    @RequestMapping("/addnotice")
    public String addNotice(Notice notice){
        noticeService.addNotice(notice);
       return  "redirect:/toindex";
    }
     @RequestMapping("/selAllNotice")
    public String  selAllNotice(Model model){
        List<Notice> notices=noticeService.selAllNotice();
         System.out.println("所有通知："+notices);
         model.addAttribute("notices",notices);
         return "tables-data-notice";
    };
    @RequestMapping("/delNoticeByID/{id}")
    public String delNoticeByID(@PathVariable Integer id){
        noticeService.delNoticeByID(id);
        return "redirect:/selAllNotice";
    };
    @RequestMapping("/increase/{id}")
    public String increase(@PathVariable Integer id){
        noticeService.increase(id);
        return "redirect:/toindex";
    };
}
