package com.dascom.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dascom.product.entity.Software;
import com.dascom.product.entity.Software_Type;



public interface SoftwareMapper {

	Software_Type findSoftWareTypeById(@Param("id")int id);

	List<Software_Type> findAllSoftwareType();

	void delSoftware(Software software);

	void updateSoftware(Software software);

	int addSoftware(Software software);

	List<Software> findSoftwareByTypeAndKey(@Param("keyword")String keyword, @Param("type")Integer type,
			@Param("begin")int begin, @Param("limit")int limit);

	int findCountByKeyAndType(@Param("keyword")String keyword,@Param("type") Integer type);

	List<Software> findSoftwareByKey(@Param("keyword")String keyword,@Param("begin") int begin,@Param("limit") int limit);

	int findCountByKey(@Param("keyword")String keyword);

	Software getSoftwareById(@Param("id")int id);

	List<Software> getAllSoftware(@Param("begin")int begin,@Param("limit") int limit);

	int findCountAll();
  
}