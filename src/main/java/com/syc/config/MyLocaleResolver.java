package com.syc.config;

import org.apache.tomcat.jni.Local;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    //解析请求
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //获取请求中的语言参数
        String language=httpServletRequest.getParameter("l");
       // System.out.println("debug+++++++++++++++++++++"+language);
        //如果没有就使用默认的
        Locale locale=Locale.getDefault();
        // 如果请求的连接携带了国际化的参数
        if(!StringUtils.isEmpty(language)){
            //zh_CN
          String[] split=  language.split("_");
            locale=  new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
