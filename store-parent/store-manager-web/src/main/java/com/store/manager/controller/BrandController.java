package com.store.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.store.pojo.TbBrand;
import com.store.service.BrandService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @RequestMapping("/findAll")
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }

    @RequestMapping("/findPage")
    public PageResult findPage(int page,int size){
        return brandService.findPage(page,size);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand brand){
        try{
            brandService.add(brand);
            return new Result(true,"新增成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"新增失败");
        }
    }

    @RequestMapping("/findOne")
    public TbBrand findOne(Long id){
        return brandService.findOne(id);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand brand){
        try{
            brandService.update(brand);
            return new Result(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }

    @RequestMapping("/delete")
    public Result delete(Long[] ids){
        //应当考虑事务，防止删除其中某个成功而其他不成功
        try{
            brandService.delete(ids);
            return Result.executeSuccess("删除成功");
        }catch (Exception e){
            return Result.executeFail("删除失败");
        }
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand brand,int page,int size){
        return brandService.findPage(brand,page,size);
    }

    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return brandService.selectOptionList();
    }
}
