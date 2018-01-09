package com.dascom.product.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dascom.product.dao.ProductDao;
import com.dascom.product.dao.Product_TypeDao;
import com.dascom.product.entity.Product;
import com.dascom.product.entity.Product_Type;
import com.dascom.product.entity.Product_Video;
import com.dascom.product.entity.Software;
import com.dascom.product.service.ProductService;
import com.dascom.product.util.PageBean;
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private Product_TypeDao product_TypeDao;
	@Autowired
	private ProductDao productDao;
	
	@Override
	public PageBean<Product_Type> findByPtAll(int page) {
		PageBean<Product_Type> pageBean = new PageBean<Product_Type>();
		//设置总页数
		pageBean.setPage(page);
		//设置当前每页显示的记录数
		int limit = 6;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = product_TypeDao.findCountAll();
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
		List<Product_Type> list=product_TypeDao.findByPtAll(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public Product_Type findByPtaname(Integer ptid) {

		return product_TypeDao.findByPtname(ptid);
	}

	@Override
	public PageBean<Product_Type> findPtByName(String typeName,int page) {
		PageBean<Product_Type> pageBean = new PageBean<Product_Type>();
		//设置总页数
		pageBean.setPage(page);
		//设置当前每页显示的记录数
		int limit = 6;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = product_TypeDao.findCountPname(typeName);
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
		List<Product_Type> list=product_TypeDao.findPtByName(typeName,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public boolean insertType(Product_Type type) {
		boolean result=false;
		int i=product_TypeDao.addProductType(type);
		if(i>0){
			result=true;
		}
		return result;
	}

	@Override
	public boolean updateType(Product_Type type) {
		boolean result=false;
		try{
			product_TypeDao.updateProductType(type);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteType(Product_Type type) {
		boolean result=false;
		try{
			product_TypeDao.deleteProductType(type);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public PageBean<Product> findProductByNameAndType(String name, int type,
			int page) {
		PageBean<Product> pageBean=new PageBean<Product>();
		pageBean.setPage(page);
		int limit = 6;
		pageBean.setLimit(limit);
		int totalCount = 0;
		if(type == 0){
			totalCount=productDao.findCountPname(name);
			pageBean.setTotalCount(totalCount);
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
			int begin = (page - 1) * limit;
			List<Product> list=productDao.findByPagePname(name, begin, limit);
			pageBean.setList(list);
		}else{
			totalCount=productDao.findCountByKeyAndType(name, type);
			pageBean.setTotalCount(totalCount);
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
			int begin = (page - 1) * limit;
			List<Product> list=productDao.findProductByKeyAndType(name, type, begin, limit);
			pageBean.setList(list);
		}
		return pageBean;
	}

	@Override
	public PageBean<Product> findAllProduct(int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置总页数
		pageBean.setPage(page);
		//设置当前每页显示的记录数
		int limit = 6;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.findAllProductCount();
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
		List<Product> list=productDao.findAllProduct(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public Product findProductById(int id) {
		return productDao.findByPid(id);
	}

	@Override
	public boolean insertProduct(Product product) {
		boolean result=false;
		int i=productDao.addProduct(product);
		if(i>0){
			result=true;
		}
		return result;
	}

	@Override
	public boolean updateProduct(Product product) {
		boolean result=false;
		try{
			productDao.updateProduct(product);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteProduct(Product product) {
		boolean result=false;
		try{
			productDao.deleteProduct(product);
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Product_Type> findAllPt() {
		
		return product_TypeDao.findAllPt();
	}

	@Override
	public List<Product> findProductByTypeId(int typeID) {		
		return productDao.findProductByTypeID(typeID);
	}

	@Override
	public List<Product> findAllProduct() {
		
		return productDao.findAllProdut();
	}
	
}
