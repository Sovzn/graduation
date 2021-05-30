package com.syc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.syc.pojo.Notice;
import com.syc.pojo.User;
import com.syc.pojo.Users;
import com.syc.service.NoticeService;
import com.syc.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    NoticeService noticeService;
    @RequestMapping("/toindex")//跳转到用户列表界面
    public String toindex(Model model){
     List<Notice> notices= noticeService.selAllNotice();
        model.addAttribute("notices",notices);
        return "index";
    }
    @RequestMapping("/toaddnotice")//跳转到用户列表界面
    public String toaddnotice(){
        return "page-addnotice";
    }
    @RequestMapping("/toadduser")//跳转到添加用户界面
    public String toaddUser(){
        return "page-register";
    }
    @RequestMapping("/tolistadduser")//跳转到批量添加用户界面
    public String tolistadduser(){
        System.out.println("跳转到批量添加页面");
        return "tables-listadd";
    }
    @RequestMapping("/to404")
    public String to404(){
        System.out.println("未登录");
        return "404";
    }
    @RequestMapping("/to401")
    public String to401(){
        return "401";
    }
    @RequestMapping("/toinfo")
    public String toinfo(){
        return "user/info";
    }
    //添加用户
    @RequestMapping("/adduser")//跳转到添加用户界面
    public String addUser(User user){
//        System.out.println(user);
        System.out.println("aaaaaaaaa"+user.getStrDate());
        String strDate=user.getStrDate();
        userService.register(user);
        return "redirect:/userslist";
    }
    //查询所有用户
    @RequestMapping("/userslist")//跳转到用户列表界面
    public String selAllUser(Model model,@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue ="8") Integer pageSize){
        //引入分页查询，使用PageHelper分页功能之前，传入当前页和页中条数
        PageHelper.startPage(pageNum,pageSize);
        //startPage后紧跟的这个查询就是分页查询
        List<User> listusers=  userService.selAllUser();
        //计算年龄
        for (User listuser : listusers) {
            Calendar now=Calendar.getInstance();
            int year=now.get(Calendar.YEAR);
            int age=year-Integer.parseInt(listuser.getStrDate().substring(0,4));
            listuser.setAge(age);
        }

        //使用PageInfo包装这个查询结果，只需要将PageInfo交给页面就可以
        PageInfo<User> pageInfo=new PageInfo(listusers,5);
        model.addAttribute("pageInfo",pageInfo);
        //获得当前页
        model.addAttribute("pageNum",pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize",pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage",pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "tables-data";
    }

       //根据id删除用户
      @RequestMapping("/delUserByID/{id}")
     public String delUserByID(@PathVariable Integer id){
       User user= userService.selUserById(id);
          System.out.println(user);
          if (user.getYewustates()==2||user.getYewustates()==3){
           User u=new User();
           System.out.println(u);
           u.setId(user.getId());
           u.setStates(2);
           System.out.println(u);
           userService.update(u);
       }
       else {
           userService.delUserByID(id);
           System.out.println("删除用户=====");
       }
        return "redirect:/userslist";
      }

      //批量添加用户
    @PostMapping("/listadduser")
    public String listadduser(Users users){
        List<User> listusers=users.getUsers();
        for (User listuser : listusers) {
           userService.register(listuser);
        }
        return "tables-listadd";
    }

    //根据条件查询用户
    @PostMapping("selUserByMap")
    public String selUserByMap(Model model,@RequestParam String username,@RequestParam String name,@RequestParam String company
                                          ,@RequestParam(defaultValue = "1")Integer pageNum,@RequestParam(defaultValue ="8") Integer pageSize){
        Map<String,String> map=new HashMap<>();
        map.put("username",username);
        map.put("name",name);
        map.put("company",company);
        //引入分页查询，使用PageHelper分页功能之前，传入当前页和页中条数
        PageHelper.startPage(pageNum,pageSize);
        //startPage后紧跟的这个查询就是分页查询
        List<User> listusers=  userService.selUserByMap(map);
        //计算年龄
        for (User listuser : listusers) {
            Calendar now=Calendar.getInstance();
            int year=now.get(Calendar.YEAR);
            int age=year-Integer.parseInt(listuser.getStrDate().substring(0,4));
            listuser.setAge(age);
        }
        //使用PageInfo包装这个查询结果，只需要将PageInfo交给页面就可以
        PageInfo<User> pageInfo=new PageInfo(listusers,5);
        model.addAttribute("pageInfo",pageInfo);
        //获得当前页
        model.addAttribute("pageNum",pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize",pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage",pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "tables-data";
    }
    //跳转到编辑用户界面(管理员)
    @RequestMapping("/toupdateuser/{id}")
    public String toupdateuser(Model model,@PathVariable Integer id){
        User user=  userService.selUserById(id);
        model.addAttribute("updateuser",user);
        System.out.println("huoqu"+user);
        return "page-update";
    }
    //跳转到编辑用户界面(普通用户)
    @RequestMapping("/toupdateuser-user/{id}")
    public String toupdateusers(Model model,@PathVariable Integer id){
        User user=  userService.selUserById(id);
        model.addAttribute("updateuser",user);
        System.out.println("用户获取信息:"+user);
        return "page-update-user";
    }
    //修改用户（管理员）
    @PostMapping("updateuser")
    public String updateuser(User user){
        Md5Hash md5Hash=new Md5Hash();
        if(!StringUtils.isEmpty(user.getPassword())){
            md5Hash = new Md5Hash(user.getPassword(),"sovzn+shiyaochang",1024);
            //将加密后的密码赋给用户
            user.setPassword(md5Hash.toHex());
        }
        System.out.println("------------------"+user);
        userService.update(user);
        return   "redirect:/userslist";
    }
       //修改用户（普通用户）
      @PostMapping("updateuserbyuser")
      public String updateuserbyuser(HttpSession session , User user){
          System.out.println("用户修改信息");
          System.out.println("用户信息："+user);
          Md5Hash md5Hash=new Md5Hash();
          if(!StringUtils.isEmpty(user.getPassword())){
              md5Hash = new Md5Hash(user.getPassword(),"sovzn+shiyaochang",1024);
              //将加密后的密码赋给用户
              user.setPassword(md5Hash.toHex());
          }
        System.out.println(user);
        userService.update(user);
        User us=userService.selUserById(user.getId());
        //更新后，更新session
        session.setAttribute("user",us);
        return   "redirect:/toindex";
    }
}
