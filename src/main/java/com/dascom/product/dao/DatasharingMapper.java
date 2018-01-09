package com.dascom.product.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dascom.product.entity.DataSharing;

public interface DatasharingMapper {
	/**
	 * 查总数
	 * @return
	 */
	int findCountAll();

	/**
	 * 查总数，根据关键字
	 * @param begin
	 * @param limit
	 * @return
	 */
	int findCountAllByKey(@Param("key")String key);
	/**
	 * 分页查询，根据关键字
	 * @param begin
	 * @param limit
	 * @return
	 */
	List<DataSharing> findAllDataSharing(@Param("begin")int begin,@Param("limit") int limit, @Param("key")String key);
	/**
	 * 增加
	 * @param begin
	 * @param limit
	 * @return
	 */
	int addDataSharing(DataSharing share);
	/**
	 * 修改
	 * @param begin
	 * @param limit
	 * @return
	 */
	void updateDateSharing(DataSharing share);
	/**
	 * 删除
	 * @param begin
	 * @param limit
	 * @return
	 */
	void deleteDateSharing(DataSharing share);
	/**
	 * 根据关键字查询
	 * @param begin
	 * @param limit
	 * @return
	 */
	DataSharing findShareInformationById(@Param("id")int id);

}
