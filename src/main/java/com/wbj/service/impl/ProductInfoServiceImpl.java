package com.wbj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wbj.mapper.ProductInfoMapper;
import com.wbj.pojo.ProductInfo;
import com.wbj.pojo.ProductInfoExample;
import com.wbj.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getAll() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    @Override
    public PageInfo split(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);

        ProductInfoExample example = new ProductInfoExample();
        example.setOrderByClause("p_id desc");

        List<ProductInfo> list = productInfoMapper.selectByExample(example);

        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

}
