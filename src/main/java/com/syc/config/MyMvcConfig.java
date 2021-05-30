package com.syc.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//扩展springmvc
//如果想自定义(diy)一些定制化的功能，只要写这个组件，然后把它交给SpringBoot，SpringBoot就会给我们自动装配
@Configuration
//@EnableWebMvc从容器中获取所有的WebMvcConfiguration
public class MyMvcConfig implements WebMvcConfigurer {
/*//自定义一个自己的视图解析器MyViewResolver
    public static class  MyViewResolver implements ViewResolver{
    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        return null;
    }
}
//ViewResolver  实现了视图解析器接口的类，我们就可以把它看成视图解析器
    @Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver();
    }
*/
    //首页
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main").setViewName("index");//首页
    }
    //自定义的国际化组件
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/index.html", "/user/login","/css/*","/js/**","/img/**");
    }
}
