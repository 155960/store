package com.store.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.store.mapper.TbBrandMapper;
import com.store.pojo.TbBrand;
import com.store.service.BrandService;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {

    private TbBrandMapper tbBrandMapper;

    @Override
    public List<TbBrand> findAll() {

        return tbBrandMapper.selectByExample(null);
    }
}
