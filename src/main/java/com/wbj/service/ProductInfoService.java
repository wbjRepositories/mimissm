package com.wbj.service;

import com.github.pagehelper.PageInfo;
import com.wbj.pojo.ProductInfo;

import java.util.List;

public interface ProductInfoService {
    public List<ProductInfo> getAll();
    public PageInfo split(int pageNum,int pageSize);

    int save(ProductInfo info);

    ProductInfo getById(int pId);

    int update(ProductInfo info);
}
