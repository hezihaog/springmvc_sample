package com.itheima.controller;

import com.itheima.domain.Account;
import com.itheima.domain.User;
import com.itheima.exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class HelloController {
    /**
     * 测试请求映射
     */
    @RequestMapping(path = "/hello")
    public String sayHello() {
        System.out.println("Hello Spring MVC");
        return "success";
    }

    /**
     * 测试数据绑定到JavaBean
     */
    @RequestMapping("/saveAccount")
    public String saveAccount(Account account) {
        System.out.println("---- saveAccount() 调用成功 ----");
        System.out.println(account);
        return "success";
    }

    /**
     * 测试自定义类型转换器
     */
    @RequestMapping("/saveUser")
    public String saveUser(User user) {
        System.out.println("---- saveUser() 调用成功 ----");
        System.out.println(user);
        return "success";
    }

    /**
     * 测试获取Servlet原生API
     */
    @RequestMapping("/testServlet")
    public String testServlet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("---- testServlet() 调用成功 ----");
        System.out.println(request);
        HttpSession session = request.getSession();
        System.out.println(session);
        ServletContext servletContext = session.getServletContext();
        System.out.println(servletContext);
        System.out.println(response);
        return "success";
    }

    //------------------------------------ 常用注解 ------------------------------------

    /**
     * 测试@RequestParam注解
     */
    @RequestMapping("/testParam")
    public String testParam(@RequestParam("name") String username,
                            @RequestParam(required = false, defaultValue = "18") Integer age) {
        System.out.println("---- testParam() 调用成功 ----");
        System.out.println("username：" + username);
        System.out.println("age：" + age);
        return "success";
    }

    /**
     * 测试@RequestBody注解，会将所有的表单数据按如下格式返回：username=hezihao&password=123
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String body) {
        System.out.println("---- testRequestBody() 调用成功 ----");
        System.out.println(body);
        return "success";
    }

    /**
     * 测试@testPathVariable注解
     */
    @RequestMapping("/testPathVariable/{sid}")
    public String testPathVariable(@PathVariable(name = "sid") int id) {
        System.out.println("---- testPathVariable() 调用成功 ----");
        System.out.println("id：" + id);
        return "success";
    }

    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept") String header) {
        System.out.println("---- testRequestHeader() 调用成功 ----");
        System.out.println("header => Accept：" + header);
        return "success";
    }

    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookieValue) {
        System.out.println("---- testCookieValue() 调用成功 ----");
        System.out.println("JSESSIONID：" + cookieValue);
        return "success";
    }

    /**
     * 测试接收json数据请求
     */
    @RequestMapping("/testAjax")
    @ResponseBody
    public User testAjax(@RequestBody User user) {
        System.out.println("---- testAjax() 调用成功 ----");
        System.out.println(user);
        //模拟结果
        user.setUname("haha");
        user.setAge(40);
        return user;
    }

    /**
     * 测试异常处理器
     */
    @RequestMapping("/testException")
    public String testException() throws SysException {
        try {
            int result = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SysException(e.getMessage());
        }
        return "success";
    }

    /**
     * 测试拦截器
     */
    @RequestMapping("/testInterceptor")
    public String testInterceptor() {
        return "success";
    }
}