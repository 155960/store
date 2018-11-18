package com.store.service.impl;
import java.util.List;
import java.util.Map;

import com.store.mapper.TbSpecificationOptionMapper;
import com.store.pojo.TbSpecificationExample;
import com.store.pojo.TbSpecificationOption;
import com.store.pojo.TbSpecificationOptionExample;
import com.store.pojogroup.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.store.mapper.TbSpecificationMapper;
import com.store.pojo.TbSpecification;
import com.store.service.SpecificationService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;

	@Autowired
	TbSpecificationOptionMapper specificationOptionMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
		TbSpecification tbSpecification=specification.getSpecification();

		specificationMapper.insert(tbSpecification);
		List<TbSpecificationOption> specificationOptionList=specification.getSpecificationOptionList();
		for(TbSpecificationOption option:specificationOptionList){
			option.setId(tbSpecification.getId());
			specificationOptionMapper.insert(option);
		}
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification){

		TbSpecification tbSpecification=specification.getSpecification();
		specificationMapper.updateByPrimaryKey(tbSpecification);

		TbSpecificationOptionExample example=new TbSpecificationOptionExample();

		TbSpecificationOptionExample.Criteria criteria=example.createCriteria();
		criteria.andSpecIdEqualTo(tbSpecification.getId());
		specificationOptionMapper.deleteByExample(example);

		List<TbSpecificationOption> specificationOptionList=specification.getSpecificationOptionList();
		for(TbSpecificationOption option:specificationOptionList){
			specificationOptionMapper.insert(option);
		}


		specification.setSpecificationOptionList(specificationOptionMapper.selectByExample(example));
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
		Specification specification=new Specification();
		specification.setSpecification( specificationMapper.selectByPrimaryKey(id));
		TbSpecificationOptionExample example=new TbSpecificationOptionExample();
		TbSpecificationOptionExample.Criteria criteria=example.createCriteria();
		criteria.andSpecIdEqualTo(id);


		specification.setSpecificationOptionList(specificationOptionMapper.selectByExample(example));
		return specification;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			specificationMapper.deleteByPrimaryKey(id);
			TbSpecificationOptionExample example=new TbSpecificationOptionExample();
			TbSpecificationOptionExample.Criteria criteria=example.createCriteria();
			criteria.andSpecIdEqualTo(id);
			specificationOptionMapper.deleteByExample(example);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		TbSpecificationExample.Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public List<Map> selectOptionList() {
		return specificationMapper.selectOptionList();
	}

}
