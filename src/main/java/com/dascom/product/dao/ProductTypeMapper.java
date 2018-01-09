package com.dascom.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dascom.product.entity.Product_Type;


public interface ProductTypeMapper {

	int findCountAll();

	List<Product_Type> findByPtAll(@Param("begin")int begin,@Param("limit") int limit);

	Product_Type findByPtname(@Param("ptid")Integer ptid);

	int findCountPname(@Param("typeName")String typeName);

	List<Product_Type> findPtByName(@Param("typeName")String typeName, @Param("begin")int begin, @Param("limit")int limit);

	int addProductType(Product_Type type);

	void updateProductType(Product_Type type);


	void deleteProductType(Product_Type type);

	List<Product_Type> findAllPt();
   
}