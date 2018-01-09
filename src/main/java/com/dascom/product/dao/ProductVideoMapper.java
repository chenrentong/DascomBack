package com.dascom.product.dao;

import java.util.List;

import javax.ws.rs.Path;

import org.apache.ibatis.annotations.Param;

import com.dascom.product.entity.Product_Video;


public interface ProductVideoMapper {

	Product_Video findByVid(@Param("vid")int vid);

	void delete(Product_Video product_Video);

	int insert(Product_Video product_Video);

	void update(Product_Video product_Video);

	List<Product_Video> findVideoAll(@Param("begin")int begin, @Param("limit")int limit);

	int findCountAll();

	List<Product_Video> findVideoPname(@Param("keyword")String keyword, @Param("begin")int begin, @Param("limit")int limit);

	int findCountPname(@Param("keyword")String keyword);
   
}