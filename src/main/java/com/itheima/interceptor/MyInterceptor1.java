package com.itheima.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 */
public class MyInterceptor1 implements HandlerInterceptor {
    /**
     * 预处理，控制器方法执行前回调
     * @return 返回true，则放行，执行下一个拦截器，如果没有下一个拦截器，则执行控制器中的方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor1 => preHandle()执行了");
        return true;
    }

    /**
     * 后处理方法，控制器方法执行后回调，jsp加载之前
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor1 => postHandle()执行了");
    }

    /**
     * jsp页面加载之后回调
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor1 => afterCompletion()执行了");
    }
}