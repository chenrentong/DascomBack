package com.dascom.product.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dascom.product.dao.DataSharingDao;
import com.dascom.product.dao.PartnersDao;
import com.dascom.product.entity.DataSharing;
import com.dascom.product.entity.Partners;
import com.dascom.product.service.PartnersService;
import com.dascom.product.util.PageBean;
@Service
public class PartnersServiceImpl implements PartnersService{
	@Autowired
	private PartnersDao partnersDao;

	/*@Override
	public List<Partners> find() {
		// TODO Auto-generated method stub
		List<Partners> a = partnersDao.findPartners();
		System.out.println(a.size());
		System.out.println(a);
		return a;
	}*/
	
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
		totalCount = partnersDao.findCountAll();
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
		List<Partners> sharingList=partnersDao.findAllPartners(begin,limit);
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
		totalCount = partnersDao.findCountAllByKey(key);
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
		List<Partners> list = partnersDao.findAllPartners(begin, limit, key);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public boolean updatePartners(Partners partners) {
		boolean result=false;
		try{
			partnersDao.updatePartners(partners);
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
			partnersDao.deletePartners(partners);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Partners findPartnersById(int id) {
		
		return partnersDao.findPartnersById(id);
	}
	
	public Partners findPartnersByUid(int uid) {
		
		Partners partners=partnersDao.findPartnersByUid(uid);
			
		return partners;
	}
	
	public boolean insertPartners(Partners partners) {
		boolean result=false;
		try{
			int count=partnersDao.insertPartners(partners);
			if(count>0){
				result=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
