package com.dascom.product.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dascom.product.dao.PartnersMapper;
import com.dascom.product.entity.DataSharing;
import com.dascom.product.entity.Partners;
import com.dascom.product.service.PartnersService;
import com.dascom.product.util.PageBean;
@Service
public class PartnersServiceImpl implements PartnersService{
	@Autowired
	private PartnersMapper partnersMapper;
	
	@Override
	public PageBean<Partners> getAllPartners(int page) {
		PageBean<Partners> pageBean = new PageBean<Partners>();
		//设置总页数
		pageBean.setPage(page);
		//设置当前每页显示的记录数
		int limit = 6;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = partnersMapper.findCountAll();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0)
		{
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		if(totalPage==0){
			totalPage=1;
		}
		pageBean.setTotalPage(totalPage);
		//从哪页开始
		int begin = (page - 1) * limit;
		//每页显示的数据集合
		List<Partners> sharingList=partnersMapper.findAllPartners(begin,limit,null);
		pageBean.setList(sharingList);
		return pageBean;
	}

	@Override
	public PageBean<Partners> findPartnersByKey(String key, Integer page) {
		PageBean<Partners> pageBean = new PageBean<Partners>();
		//设置总页数
		pageBean.setPage(page);
		//设置当前每页显示的记录数
		int limit = 6;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = partnersMapper.findCountAllByKey(key);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0)
		{
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		if(totalPage==0){
			totalPage=1;
		}
		pageBean.setTotalPage(totalPage);
		//从哪页开始
		int begin = (page - 1) * limit;
		//每页显示的数据集合
		List<Partners> list = partnersMapper.findAllPartners(begin, limit, key);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public boolean updatePartners(Partners partners) {
		boolean result=false;
		try{
			partnersMapper.updatePartners(partners);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deletePartners(Partners partners) {
		boolean result=false;
		try{
			partnersMapper.deletePartners(partners);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Partners findPartnersById(int id) {
		
		return partnersMapper.findPartnersById(id);
	}
	
	public boolean insertPartners(Partners partners) {
		boolean result=false;
		try{
			int count=partnersMapper.insertPartners(partners);
			if(count>0){
				result=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
