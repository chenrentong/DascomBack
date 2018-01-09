package com.dascom.product.service;

import java.util.List;

import com.dascom.product.entity.Product;
import com.dascom.product.entity.Product_Type;
import com.dascom.product.util.PageBean;

public interface ProductService {
	//查询所有分类
	List<Product_Type> findAllPt();
	//查询所有分类
	PageBean<Product_Type> findByPtAll(int page);
	//根据id查找分类
	Product_Type findByPtaname(Integer ptid);
	//关键字查询分类
	PageBean<Product_Type> findPtByName(String typeName,int page);
	//添加分类
	boolean insertType(Product_Type type);
	//修改分类
	boolean updateType(Product_Type type);
	//删除分类
	boolean deleteType(Product_Type type);
	
	//根据搜索名称和类型查询资源
	PageBean<Product> findProductByNameAndType(String name,int type,int page);
	
	//查询所有产品
	PageBean<Product> findAllProduct(int page);
	
	//根据id查询产品
	Product findProductById(int id);
	
	//添加产品
	boolean insertProduct(Product product);
	
	//修改产品
	boolean updateProduct(Product product);
	
	//删除产品
	boolean deleteProduct(Product product);
	
	//根据分类查询商品
	List<Product> findProductByTypeId(int typeID);
	
	//查询所有的产品
	List<Product> findAllProduct();
}
