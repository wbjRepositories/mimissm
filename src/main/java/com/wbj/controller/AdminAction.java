package com.wbj.controller;

import com.wbj.pojo.Admin;
import com.wbj.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminAction {

    @Autowired
    AdminService adminService;

    @RequestMapping("/login")
    public String login(String name, String pwd, HttpServletRequest httpServletRequest){
        Admin admin = adminService.login(name, pwd);
        if (admin != null){
            //登陆成功
            httpServletRequest.setAttribute("admin",admin);
            return "main";
        }else {
            //登陆失败
            httpServletRequest.setAttribute("errmsg","用户名或密码不正确");
            return "login";
        }
    }
}
