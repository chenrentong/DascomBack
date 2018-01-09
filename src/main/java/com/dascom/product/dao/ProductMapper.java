package com.dascom.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dascom.product.entity.Product;


public interface ProductMapper {

	int findCountPname(@Param("key") String name);

	List<Product> findByPagePname(@Param("key")String name, @Param("begin")int begin, @Param("limit")int limit);

	int findCountByKeyAndType(@Param("key")String name,@Param("type") int type);

	List<Product> findProductByKeyAndType(@Param("key")String name, @Param("type")int type,@Param("begin") int begin,
			@Param("limit")int limit);

	int findAllProductCount();

	List<Product> findAllProduct(@Param("begin")int begin, @Param("limit")int limit);

	Product findByPid(@Param("id")int id);

	int addProduct(Product product);

	void deleteProduct(Product product);

	List<Product> findProductByTypeID(@Param("typeID")int typeID);

	List<Product> findAllProdut();

	void updateProduct(Product product);
    
}