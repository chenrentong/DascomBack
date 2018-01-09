package com.dascom.product.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.dascom.product.entity.*;
import com.dascom.product.service.ProductService;
import com.dascom.product.service.ResourceService;
import com.dascom.product.util.Logg;
import com.dascom.product.util.PageBean;
import com.dascom.product.util.ProductUtil;
import com.dascom.product.util.SingleUpload;

@Controller
public class ResourceController {
	@Autowired
	private ResourceService resourceServiceImpl;
	@Autowired 
	private ProductService productService;
	

	//跳转到下载列表
	@RequestMapping("downloadList")
	public String downloadList(HttpServletRequest request ){
		
		ProductUtil.setClean(request);
		String page=request.getParameter("page");
		if(page==null){
			page="1";
		}
		PageBean<Software> pageBean=resourceServiceImpl.findSoftwareAll(Integer.parseInt(page));
		List<Software_Type> type=resourceServiceImpl.findAllSoftwareType();
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("key", "");
		request.setAttribute("type1", 0);
		request.setAttribute("typeList", type);
		return "Resource/downloadList";
	}
	//跳转到下载模糊查询
	@RequestMapping("downloadListByKey")
	public String downloadListByKey(HttpServletRequest request,@RequestParam Integer page,
			@RequestParam String key ,@RequestParam Integer type){
		PageBean<Software> pageBean=resourceServiceImpl.findSoftwareByTypeAndName(key,type,page);
		List<Software_Type> Stype=resourceServiceImpl.findAllSoftwareType();
		request.setAttribute("type1", type);
		request.setAttribute("key", key);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("typeList", Stype);
		return "Resource/downloadList";
	}
	//跳转到下载添加
	@RequestMapping("downloadAdd")
	public String downloadAdd(HttpServletRequest request ){
		List<Software_Type> type=resourceServiceImpl.findAllSoftwareType();
		request.setAttribute("typeList", type);
		return "Resource/downloadAdd";
	}
	//跳转到下载提交添加
	@RequestMapping("downloadSubmit/add")
	public String downloadSubmitAdd(HttpServletRequest request ,@RequestParam String name,@RequestParam Integer type){
		Software software=new Software();
		software.setSname(name);
		software.setSoftware_Type(resourceServiceImpl.findSoftwareTypeById(type));
		SingleUpload upload=new SingleUpload(type);
		String res=upload.resourceUpload(request);
		if(upload.getAllPath()==null ){
			request.setAttribute("result", res);
			return "Result/401";
		}else{
			software.setSpath(upload.getAllPath());
			software.setSuffix(upload.getSuffix());
			software.setSize(upload.getSize().toString());
		}
		boolean result=resourceServiceImpl.insertSoftware(software);
		if(result){
			return "Result/200";
		}
		return "Result/444";
	}
	//跳转到下载提交修改
	@RequestMapping("downloadSubmit/edit")
	public String downloadSubmitEdit(HttpServletRequest request,@RequestParam String name,
			@RequestParam Integer id ,@RequestParam Integer type){
		Software software=resourceServiceImpl.findSoftById(id);
		software.setSname(name);
		software.setSoftware_Type(resourceServiceImpl.findSoftwareTypeById(type));
		SingleUpload upload=new SingleUpload(type);
		upload.resourceUpload(request);
		if(upload.getAllPath()!=null && upload.getAllPath().trim() !=""){
			software.setSpath(upload.getAllPath());
			software.setSize(upload.getSize().toString());
			software.setSuffix(upload.getSuffix());
		}else{
			software.setSpath(software.getSpath());
			software.setSize(software.getSize());
			software.setSuffix(software.getSuffix());
		}
		boolean result=resourceServiceImpl.updateSoftware(software);
		System.out.println(software);
		request.setAttribute("type1", software.getSoftware_Type().getStid());
		List<Software_Type> typeList=resourceServiceImpl.findAllSoftwareType();
		request.setAttribute("typeList", typeList);
		request.setAttribute("software", software);
		if(result){
			return  "Result/200";
		}
		return "Result/444";
		
	}
	//跳转到下载修改
	@RequestMapping("downloadEdit")
	public String downloadEdit(HttpServletRequest request,@RequestParam String id  ){
		Software software =resourceServiceImpl.findSoftById(Integer.parseInt(id));
		List<Software_Type> type=resourceServiceImpl.findAllSoftwareType();
		request.setAttribute("type1", software.getSoftware_Type().getStid());
		request.setAttribute("typeList", type);
		request.setAttribute("software", software);
		return "Resource/downloadEdit";
	}
	//跳转到下载删除
	@ResponseBody
	@RequestMapping("downloadDel")
	public String downloadDel(HttpServletRequest request,@RequestParam String id ){
		String[] ids=id.split(",");	//批量删除
		try{
			for(String id1:ids){
				//判断
				Integer nId=Integer.parseInt(id1.trim());
				Software software=resourceServiceImpl.findSoftById(nId);
				List<Product> productList=productService.findAllProduct();
				switch (software.getSoftware_Type().getStid()) {
				case 1:
					for(Product p:productList){
						String sdriveIds=p.getPdriveid();
						if(sdriveIds!=null){
							String[] sList=sdriveIds.split(",");
							for(String s:sList){
								if(s.equals(id1)){
									return null;
								}
							}
						}	
					}	
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
					
					break;
				case 5:
					
					break;
				}
				resourceServiceImpl.deleteSoftware(software);
			}
		}catch(Exception e){
			Logg.logger.error("程序出错",e);
			e.printStackTrace();
			
			return null;
		}
		return "200";
	}
	
	//跳转到视频列表
	@RequestMapping("videoList")
	public String videoList(HttpServletRequest request ){
		ProductUtil.setClean(request);
		//分页视频
		String page=request.getParameter("page");
		if(page==null){
			page="1";
		}
		PageBean<Product_Video> pageBean=resourceServiceImpl.findVideoAll(Integer.parseInt(page));
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("keyword", "");
		return "Resource/videoList";
	}
	//跳转到视频列表模糊查询
	@RequestMapping("videoListKey")
	public String videoListKey(HttpServletRequest request,@RequestParam String key,@RequestParam Integer page){
		//分页视频		
		PageBean<Product_Video> pageBean=resourceServiceImpl.findVideoPname(key, page);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("key",key);
		return "Resource/videoList";
	}
	//跳转到视频添加
	@RequestMapping("videoAdd")
	public String videoAdd(HttpServletRequest request){
		return "Resource/videoAdd";
	}
	//跳转到视频提交
	@RequestMapping("videoSubmit/edit")
	public String videoSubmitEdit(HttpServletRequest request, @RequestParam String name,
			@RequestParam Integer pvid){
			SingleUpload upload=new SingleUpload(7);
			Product_Video video=resourceServiceImpl.findByVid(pvid);
			upload.resourceUpload(request);
			if(upload.getAllPath()!=null && upload.getAllPath()!=""){
				video.setPvpath(upload.getAllPath());
				video.setSize(upload.getSize().toString());
				video.setSuffix(upload.getSuffix());
			}else{
				video.setPvpath(video.getPvpath());
				video.setSize(video.getSize());
				video.setSuffix(video.getSuffix());
			}
			video.setPvname(name);
			boolean result=resourceServiceImpl.updateVideo(video);
			request.setAttribute("video", video);
			if(result){
				return  "Result/200";
			}
			return "Result/444";
	}
	//跳转到视频提交添加
	@RequestMapping("videoSubmit/add")
	public String videoSubmitAdd(HttpServletRequest request, @RequestParam String name){
		SingleUpload upload=new SingleUpload(7);
			Product_Video video=new Product_Video();
			String res=upload.resourceUpload(request);
			if(upload.getAllPath()==null ){
				request.setAttribute("result", res);
				return "Result/401";
			}else{
				video.setPvpath(upload.getAllPath());
				video.setSize(upload.getSize().toString());
				video.setSuffix(upload.getSuffix());
			}
			video.setPvname(name);
			boolean result=resourceServiceImpl.insertVideo(video);
			if(result){
				return  "Result/200";
			}
			return "Result/444";
		
	}
	//跳转到视频修改
	@RequestMapping("videoEdit")
	public String videoEdit(HttpServletRequest request,@RequestParam String id ){
		Product_Video video=resourceServiceImpl.findByVid(Integer.parseInt(id));
		request.setAttribute("video", video);
		return "Resource/videoEdit";
	}
	//视频删除
	@ResponseBody
	@RequestMapping("videoDel")
	public String videoDel(HttpServletRequest request,@RequestParam String id ){
		String[] ids=id.split(",");	//批量删除
		try{
			for(String id1:ids){
				//判断
				List<Product> productList=productService.findAllProduct();
				for(Product p:productList){
					String videoIds=p.getVideoid();
					if(videoIds!=null){
						String[] vIds=videoIds.split(",");
						for(String vid:vIds){
							if(vid.equals(id1)){
								return null;
							}
						}
					}	
				}				
				Integer nId=Integer.parseInt(id1.trim());
				Product_Video video=resourceServiceImpl.findByVid(nId);
				resourceServiceImpl.deleteVideoById(video);					
			}
		}catch(Exception e){
			Logg.logger.error("程序出错",e);
			e.printStackTrace();
			return null;
		}
		return "200";
	}
}
