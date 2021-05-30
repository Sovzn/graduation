package com.syc.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录成功后，有用户的session
       /* Object loginUser=request.getSession().getAttribute("loginUser");
        if(loginUser==null){
             setAttribute("msg","没有权限，请先登录");
            request.getRequestDispatcher("/index.html").forward(request,response);
            System.out.println("被拦截了++++++++++++++++++++++++++++++++++++++++++++++");
            return false;
        }
     else {
         return true;

        }*/
            return true;
    }
}

