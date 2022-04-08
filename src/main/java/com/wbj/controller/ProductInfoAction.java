package com.wbj.controller;

import com.github.pagehelper.PageInfo;
import com.wbj.pojo.ProductInfo;
import com.wbj.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    @Autowired
    ProductInfoService productInfoService;

    private static final int PAGE_SIZE = 5;

    @RequestMapping("/getAll")
    public String getAll(HttpServletRequest request){
        List<ProductInfo> list = productInfoService.getAll();
        request.setAttribute("list",list);
        return "product";
    }

    @RequestMapping("/split")
    public String split(HttpServletRequest request){
        PageInfo pageInfo = productInfoService.split(1, PAGE_SIZE);
        request.setAttribute("info",pageInfo);
        return "product";
    }

    @ResponseBody
    @RequestMapping("/ajaxSplit")
    public void ajaxSplit(int page, HttpSession session){
        PageInfo info = productInfoService.split(page, PAGE_SIZE);
        session.setAttribute("info",info);
    }
}
