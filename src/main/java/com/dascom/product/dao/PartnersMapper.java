package com.dascom.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dascom.product.entity.Partners;


public interface PartnersMapper {

	int findCountAll();

	int findCountAllByKey(@Param("key")String key);

	List<Partners> findAllPartners(@Param("begin")int begin, @Param("limit")int limit, @Param("key")String key);

	void updatePartners(Partners partners);

	void deletePartners(Partners partners);

	Partners findPartnersById(@Param("id")int id);

	int insertPartners(Partners partners);

	
   
}