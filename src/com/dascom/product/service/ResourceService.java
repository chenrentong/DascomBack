package com.dascom.product.service;

import java.util.List;

import com.dascom.product.entity.Product_Video;
import com.dascom.product.entity.Software;
import com.dascom.product.entity.Software_Type;
import com.dascom.product.util.PageBean;


public interface ResourceService {

	//根据模糊查询 查询出产品的视频教程
	PageBean<Product_Video> findVideoPname(String keyword, int page);
	
	//查询所有视频教程
	PageBean<Product_Video> findVideoAll(int page);
	
	//修改视频
	boolean updateVideo(Product_Video product_Video);
	
	//添加视频
	boolean insertVideo(Product_Video product_Video);
	
	//删除视频
	boolean deleteVideoById(Product_Video product_Video);
	
	//根据视频id查询视频
	Product_Video findByVid(int vid);
	
	//查询所有资源
	PageBean<Software> findSoftwareAll(int page);
	
	//根据资源id查询资源
	Software findSoftById(int id);
	
	//根据搜索名称和类型查询资源
	PageBean<Software> findSoftwareByTypeAndName(String keyword,Integer type,int page);
	
	//添加资源
	boolean insertSoftware(Software software);
	
	//修改资源
	boolean updateSoftware(Software software);
	
	//删除资源
	boolean deleteSoftware(Software software);
	
	//查询所有资源类型
	List<Software_Type> findAllSoftwareType();
	
	//根据id查询资源类型
	Software_Type findSoftwareTypeById(int id);
}
