package com.syc.controller;

import com.syc.pojo.User;
import com.syc.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @PostMapping ("/login")
  public String login(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      Model model, HttpSession session ){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
       UsernamePasswordToken token= new UsernamePasswordToken(username,password);
       try{
           subject.login(token);
           User user=userService.selUserByusername(username);
           if(user.getStates()==2){
               model.addAttribute("msg","您的账号已被封禁，请联系管理员:sovzn@Gmail.com");
               return "login";
           }
           session.setAttribute("loginUser",user.getName());
           session.setAttribute("UserID",user.getId());
           session.setAttribute("user",user);
           return "redirect:/toindex";
       }catch (UnknownAccountException e){
           model.addAttribute("msg","提示：用户名错误 φ(*￣0￣)");
           return "login";
       }
        catch (IncorrectCredentialsException e){
           model.addAttribute("msg","提示：密码错误 φ(*￣0￣)");
           return "login";
       }
}
    @RequestMapping("/logout")   //退出登录
    public String login(){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //退出
        subject.logout();
        return "login";
    }

}
