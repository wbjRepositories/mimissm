package com.wbj.service.impl;

import com.wbj.mapper.ProductTypeMapper;
import com.wbj.pojo.ProductType;
import com.wbj.pojo.ProductTypeExample;
import com.wbj.service.ProductInfoService;
import com.wbj.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getAll() {
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
