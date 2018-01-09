package com.dascom.product.service;

import java.util.List;

import com.dascom.product.entity.Partners;
import com.dascom.product.util.PageBean;

public interface PartnersService {
	
	PageBean<Partners> getAllPartners(int page);
	
	PageBean<Partners> findPartnersByKey(String key, Integer page);
	
	boolean updatePartners(Partners share);
	
	boolean deletePartners(Partners share);
	
	Partners findPartnersById(int id);
	
	public Partners findPartnersByUid(int uid);
	
	public boolean insertPartners(Partners partners);
	
	/*public List<Partners> find();*/
}
