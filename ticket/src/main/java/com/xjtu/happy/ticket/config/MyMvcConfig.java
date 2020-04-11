package com.xjtu.happy.ticket.config;


import com.xjtu.happy.ticket.component.LoginHandlerInterceptor;
import com.xjtu.happy.ticket.controller.login.LoginController;
import com.xjtu.happy.ticket.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer adapter = new WebMvcConfigurer() {

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/login").setViewName("login");
                registry.addViewController("/admin.html").setViewName("admin");
                registry.addViewController("/search.html").setViewName("search");
            }
            @Autowired
            LoginService userService;
            //拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor(userService)).addPathPatterns("/**")
                        .excludePathPatterns("/login", "/index.html", "/register","/user/login","/"
                                ,"/user/register","/register.html","/static/**","/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg",
                                "/**/*.jpeg", "/**/*.gif", "/**/fonts/*", "/**/*.svg");


            }


        };
        return adapter;
    }

}

