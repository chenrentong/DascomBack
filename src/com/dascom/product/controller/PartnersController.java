package com.dascom.product.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dascom.product.entity.DataSharing;
import com.dascom.product.entity.Partners;
import com.dascom.product.entity.User;
import com.dascom.product.service.DataSharingService;
import com.dascom.product.service.PartnersService;
import com.dascom.product.util.Logg;
import com.dascom.product.util.PageBean;
import com.dascom.product.util.ProductUtil;
import com.dascom.product.util.SingleUpload;

@Controller
public class PartnersController {
	@Autowired
	private DataSharingService dataSharingService;
	@Autowired
	private PartnersService partnersService;

	
	@RequestMapping("partnersList")
	public String partnersList(HttpServletRequest request ){
		ProductUtil.setClean(request);
		String page=request.getParameter("page");
		if(page==null){
			page="1";
		}
		//partnersService.find();
		PageBean<Partners> pageBean=partnersService.getAllPartners(Integer.parseInt(page));
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("keyword", "");
		return "Partners/partnersList";
	}
	
	@RequestMapping("partnersByKey")
	public String partnersByKey(HttpServletRequest request,@RequestParam Integer page,
			@RequestParam String key  ){
		PageBean<Partners> pageBean=partnersService.findPartnersByKey(key,page);
		request.setAttribute("key", key);
		request.setAttribute("pageBean", pageBean);
		return "Partners/partnersList";
	}
	
	@ResponseBody
	@RequestMapping("partnersDel")
	public String partnersDel(HttpServletRequest request,String id){
		String[] ids=id.split(",");	//批量删除
		try{
			for(String id1:ids){
				Integer nId=Integer.parseInt(id1.trim());
				Partners share=partnersService.findPartnersById(nId);
				partnersService.deletePartners(share);
			}
		}catch(Exception e){
			Logg.logger.error("程序出错",e);
			e.printStackTrace();
			return null;
		}
		return "200";
		
	}
	
	@RequestMapping("partnersInfo")
	public String partnersInfo(HttpServletRequest request,@RequestParam String id ){
		Partners  share=partnersService.findPartnersById(Integer.parseInt(id));
		request.setAttribute("partners", share);
		return "Partners/partnersInfo";
	}
	
	@RequestMapping("partnersEdit")
	public String partnersEdit(HttpServletRequest request,@RequestParam String id ){
		Partners  share=partnersService.findPartnersById(Integer.parseInt(id));
		request.setAttribute("partners", share);
		return "Partners/partnersEdit";
	}
	
	@RequestMapping("partnersSubmit/edit")
	public String partnersSubmitEdit(HttpServletRequest request,@RequestParam String id, String rank,
			String permission,String status){
		Partners  share=partnersService.findPartnersById(Integer.parseInt(id));
		share.setRank(rank);
		//share.setCode(code);
		share.setPermission(permission);
		share.setStatus(status);
		boolean result=partnersService.updatePartners(share);
		if(result){
			return  "Result/200";
		}
		return "Result/444";
	}	
	
	@RequestMapping("shareInformationList")
	public String shareInformationList(HttpServletRequest request ){
		ProductUtil.setClean(request);
		String page=request.getParameter("page");
		if(page==null){
			page="1";
		}
		PageBean<DataSharing> pageBean=dataSharingService.getAllDataSharing(Integer.parseInt(page));
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("keyword", "");
		return "Partners/shareInformationList";
	}
	
	@RequestMapping("shareInformationByKey")
	public String shareInformationByKey(HttpServletRequest request,@RequestParam Integer page,
			@RequestParam String key ){
		PageBean<DataSharing> pageBean=dataSharingService.findShareInformationByKey(key,page);
		request.setAttribute("key", key);
		request.setAttribute("pageBean", pageBean);
		return "Partners/shareInformationList";
	}	
	
	@RequestMapping("shareInformationAdd")
	public String shareInformationAdd(HttpServletRequest request){
		return "Partners/shareInformationAdd";
	}	
	
	@RequestMapping("shareInformationEdit")
	public String shareInformationEdit(HttpServletRequest request,@RequestParam String id){
		DataSharing  share=dataSharingService.findShareInformationById(Integer.parseInt(id));
		request.setAttribute("share", share);
		return "Partners/shareInformationEdit";
	}
	
	@RequestMapping("shareInfoSubmit/add")
	public String shareInfoSubmitAdd(HttpServletRequest request,@RequestParam String name,@RequestParam String permission){
		SingleUpload upload=new SingleUpload(8);
		DataSharing share=new DataSharing();
		share.setName(name);		
		share.setTime(new Date());
		share.setPermission(permission);
		String res=upload.resourceUpload(request);
		if(upload.getAllPath() == null ){
			request.setAttribute("result", res);
			return "Result/401";
		}else{
			share.setUrl(upload.getAllPath());
			share.setSuffix(upload.getSuffix());
			share.setSize(upload.getSize().toString());
		}
		boolean result=dataSharingService.insertDataSharing(share);
		if(result){			
			return  "Result/200";
		}
		return "Result/444";
	}	
	
	@RequestMapping("shareInfoSubmit/edit")
	public String shareInfoSubmitEdit(HttpServletRequest request,@RequestParam String name,String id, String permission){
		SingleUpload upload=new SingleUpload(8);
		DataSharing  share=dataSharingService.findShareInformationById(Integer.parseInt(id));
		share.setName(name);
		share.setPermission(permission);
		upload.resourceUpload(request);
		if(upload.getAllPath()!=null && upload.getAllPath() != ""){
			share.setUrl(upload.getAllPath());
			share.setSuffix(upload.getSuffix());
			share.setSize(upload.getSize().toString());
		}else{
			share.setUrl(share.getUrl());
			share.setSuffix(share.getSuffix());
			share.setSize(share.getSize());
		}
		boolean result=dataSharingService.updateDataSharing(share);
		if(result){
			return  "Result/200";
		}
		return "Result/444";
	}	
	@ResponseBody
	@RequestMapping("shareInfoDel")
	public String shareInfoSubmitDel(HttpServletRequest request,String id){
		String[] ids=id.split(",");	//批量删除
		try{
			for(String id1:ids){
				Integer nId=Integer.parseInt(id1.trim());
				DataSharing share=dataSharingService.findShareInformationById(nId);
				dataSharingService.deleteDataSharing(share);
			}
		}catch(Exception e){
			Logg.logger.error("程序出错",e);
			e.printStackTrace();
			return null;
		}
		return "200";
		
	}
	
}
