package com.wbj.service.impl;

import com.wbj.mapper.AdminMapper;
import com.wbj.pojo.Admin;
import com.wbj.pojo.AdminExample;
import com.wbj.service.AdminService;
import com.wbj.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    /**
     * 登录操作
     * @param username
     * @param password
     * @return
     */
    @Override
    public Admin login(String username, String password) {
//        查找账户
        AdminExample example = new AdminExample();
        example.createCriteria().andANameEqualTo(username);
        List<Admin> list = adminMapper.selectByExample(example);

        if (list.size()>0){
            Admin admin = list.get(0);

            //验证密码
            if (MD5Util.getMD5(password).equals(admin.getaPass())) {
                return admin;
            }
        }
        return null;
    }
}
