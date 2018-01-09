package com.dascom.product.service;

import com.dascom.product.entity.DataSharing;
import com.dascom.product.util.PageBean;

public interface DataSharingService {
	PageBean<DataSharing> getAllDataSharing(int page);

	PageBean<DataSharing> findShareInformationByKey(String key, Integer page);

	boolean insertDataSharing(DataSharing share);
	
	boolean updateDataSharing(DataSharing share);
	
	boolean deleteDataSharing(DataSharing share);
	
	DataSharing findShareInformationById(int id);
}
