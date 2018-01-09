package com.dascom.product.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dascom.product.dao.DataSharingDao;
import com.dascom.product.entity.DataSharing;
import com.dascom.product.service.DataSharingService;
import com.dascom.product.util.PageBean;

@Service
public class DataSharingServiceImpl implements DataSharingService{
	@Autowired
	private DataSharingDao dataSharingDao;
	
	@Override
	public PageBean<DataSharing> getAllDataSharing(int page) {
		PageBean<DataSharing> pageBean = new PageBean<DataSharing>();
		//设置总页数
		pageBean.setPage(page);
		//设置当前每页显示的记录数
		int limit = 6;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = dataSharingDao.findCountAll();
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
		List<DataSharing> sharingList=dataSharingDao.findAllDataSharing(begin,limit);
		pageBean.setList(sharingList);
		return pageBean;
	}

	@Override
	public PageBean<DataSharing> findShareInformationByKey(String key,
			Integer page) {
		PageBean<DataSharing> pageBean = new PageBean<DataSharing>();
		//设置总页数
		pageBean.setPage(page);
		//设置当前每页显示的记录数
		int limit = 6;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = dataSharingDao.findCountAllByKey(key);
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
		List<DataSharing> list = dataSharingDao.findAllDataSharing(begin, limit, key);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public boolean insertDataSharing(DataSharing share) {
		boolean result=false;
		int i=dataSharingDao.addDataSharing(share);
		if(i>0){
			result=true;
		}
		return result;
	}

	@Override
	public boolean updateDataSharing(DataSharing share) {
		boolean result=false;
		try{
			dataSharingDao.updateDateSharing(share);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteDataSharing(DataSharing share) {
		boolean result=false;
		try{
			dataSharingDao.deleteDateSharing(share);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public DataSharing findShareInformationById(int id) {
		
		return dataSharingDao.findShareInformationById(id);
	}

}
