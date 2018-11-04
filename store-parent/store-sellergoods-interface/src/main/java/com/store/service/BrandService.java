package com.store.service;

import com.store.pojo.TbBrand;
import entity.PageResult;

import java.util.List;

public interface BrandService {
    public List<TbBrand> findAll();

    /**
     *
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return
     */
    public PageResult findPage(int pageNum,int pageSize);

    /**
     * 增加
     * @param brand
     */
    public void add(TbBrand brand);

    /**
     * 查找一个
     * @param id
     * @return
     */
    public TbBrand findOne(Long id);

    /**
     * 更新
     * @param brand
     */
    public void update(TbBrand brand);

    public void delete(Long[] ids);

    /**
     * 按条件模糊查询
     * @param brand 条件
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult findPage(TbBrand brand,int pageNum,int pageSize);
}
