package com.wbj.service;

import com.github.pagehelper.PageInfo;
import com.wbj.pojo.ProductInfo;
import com.wbj.pojo.vo.ProductInfoVo;

import java.util.List;

public interface ProductInfoService {
    public List<ProductInfo> getAll();
    public PageInfo split(int pageNum,int pageSize);

    int save(ProductInfo info);

    ProductInfo getById(int pId);

    int update(ProductInfo info);

    int delete(int pid);

    int deleteBatch(String[] ids);

    List<ProductInfo> selectCondition(ProductInfoVo vo);

    public PageInfo splitPageVo(ProductInfoVo vo,int pageSize);
}
