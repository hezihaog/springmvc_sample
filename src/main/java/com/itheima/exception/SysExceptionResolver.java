package com.itheima.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 */
public class SysExceptionResolver implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        SysException ex;
        if (e instanceof SysException) {
            ex = (SysException) e;
        } else {
            ex = new SysException("请联系系统管理员");
        }
        //跳转到友好的错误页面
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("errorMsg", ex.getMessage());
        return mv;
    }
}