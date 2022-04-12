package com.wbj.controller;

import com.github.pagehelper.PageInfo;
import com.wbj.pojo.ProductInfo;
import com.wbj.service.ProductInfoService;
import com.wbj.utils.FileNameUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    @Autowired
    ProductInfoService productInfoService;

    String saveFileName = "";

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

    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage,HttpServletRequest request){
        saveFileName = FileNameUtil.getUUIDFileName()+FileNameUtil.getFileType(pimage.getOriginalFilename());
        String path = request.getServletContext().getRealPath("/image_big");
        try {
            pimage.transferTo(new File(path+File.separator+saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject object = new JSONObject();
        object.put("imgurl",saveFileName);

        return object.toString();
    }


    @RequestMapping("/save")
    public String save(ProductInfo info,HttpServletRequest request){
        info.setpImage(saveFileName);
        info.setpDate(new Date());
        int num = -1;
        num = productInfoService.save(info);
        if (num>0){
            request.setAttribute("msg","增加成功");
        }else {
            request.setAttribute("msg","增加失败");
        }
        saveFileName = "";
        return "forward:/prod/split.action";
    }

    @RequestMapping("/one")
    public String one(int pid, Model model){
        ProductInfo info = productInfoService.getById(pid);
        model.addAttribute("prod",info);
        return "update";
    }

    @RequestMapping("/update")
    public String update(ProductInfo info,HttpServletRequest request){
        if (!saveFileName.equals("")){
            info.setpImage(saveFileName);
        }

        int num = -1;
        try {
            num = productInfoService.update(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num>0){
            request.setAttribute("msg","更新成功");
        }else {
            request.setAttribute("msg","更新失败");
        }

        saveFileName = "";
        return "forward:/prod/split.action";
    }

}
