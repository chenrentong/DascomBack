package com.dascom.product.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dascom.product.entity.Product;
import com.dascom.product.entity.Product_Type;
import com.dascom.product.entity.Product_Video;
import com.dascom.product.entity.Software;
import com.dascom.product.service.ProductService;
import com.dascom.product.service.ResourceService;
import com.dascom.product.util.Logg;
import com.dascom.product.util.PageBean;
import com.dascom.product.util.ProductUtil;
import com.dascom.product.util.SingleUpload;

@Controller
public class ProductController {
	@Autowired 
	private ProductService productService;
	@Autowired
	private ResourceService resourceServiceImpl;

	
	Product product=new Product();
	
	//跳转到产品分类列表
	@RequestMapping("classifyList")
	public String classifyList(HttpServletRequest request ){
		ProductUtil.setClean(request);
		String page=request.getParameter("page");
		if(page==null){
			page="1";
		}
		PageBean<Product_Type> type=productService.findByPtAll(Integer.parseInt(page));
		request.setAttribute("pageBean", type);
		request.setAttribute("keyword", "");
		return "Product/classifyList";
	}
	//跳转到产品模糊查询
	@RequestMapping("classifyLike")
	public String classifyLike(HttpServletRequest request,@RequestParam String key,@RequestParam Integer page){
		PageBean<Product_Type> type=productService.findPtByName(key,page);
		request.setAttribute("pageBean", type);
		request.setAttribute("keyword", key);
		return "Product/classifyList";
	}
	//跳转到产品分类添加
	@RequestMapping("classifyAdd")
	public String classifyAdd(HttpServletRequest request ){
		return "Product/classifyAdd";
	}
	//分类添加提交
	@RequestMapping("classifySubmit/add")
	public String classifySubmitAdd(HttpServletRequest request ,@RequestParam String name){
		SingleUpload upload=new SingleUpload(6);
		Product_Type type=new Product_Type();
		type.setPtname(name);
		String res=upload.resourceUpload(request);
		if(upload.getAllPath() == null ){
			request.setAttribute("result", res);
			return "Result/401";
		}else{
			type.setPtimage(upload.getAllPath());
		}
		boolean result=productService.insertType(type);
		if(result){
			
			return  "Result/200";
		}
		return "Result/444";
		
	}
	//跳转到产品分类修改
	@RequestMapping("classifyEdit")
	public String classifyEdit(HttpServletRequest request,@RequestParam String id){
		Product_Type type=productService.findByPtaname(Integer.parseInt(id));
		request.setAttribute("type", type);
		return "Product/classifyEdit";
	}
	//分类修改提交
	@RequestMapping("classifySubmit/edit")
	public String classifySubmitEdit(HttpServletRequest request ,@RequestParam String name,String id,String image){
		SingleUpload upload=new SingleUpload(6);
		Product_Type type=productService.findByPtaname(Integer.parseInt(id));
		type.setPtname(name);
		type.setPtimage(image);
		upload.resourceUpload(request);
		if(upload.getAllPath()!=null ){
			type.setPtimage(upload.getAllPath());
		}else{
			type.setPtimage(type.getPtimage());
		}
		boolean result=productService.updateType(type);
		request.setAttribute("type", type);
		if(result){
			return  "Result/200";
		}
		return "Result/444";
		
	}
	//产品分类删除
	@ResponseBody
	@RequestMapping("classifyDel")
	public String classifyDel(HttpServletRequest request,@RequestParam String id){
		String[] ids=id.split(",");	//批量删除
		try{
			for(String id1:ids){
				//查看id有没有产品
				List<Product> productList=productService.findProductByTypeId(Integer.parseInt(id1));
				if(productList.size()==0){
					Integer nId=Integer.parseInt(id1.trim());
					Product_Type type=productService.findByPtaname(nId);
					productService.deleteType(type);
				}else{
					return null;
				}	
			}
		}catch(Exception e){
			Logg.logger.error("程序出错",e);
			e.printStackTrace();
		}
		return "200";
	}
		
	//跳转到产品列表
	@RequestMapping("productList")
	public String productList(HttpServletRequest request ){
		ProductUtil.setClean(request);
		String page=request.getParameter("page");
		if(page==null){
			page="1";
		}
		PageBean<Product> product=productService.findAllProduct(Integer.parseInt(page));
		List<Product_Type> type=productService.findAllPt();
		request.setAttribute("typeList", type);
		request.setAttribute("pageBean", product);
		request.setAttribute("type1", 0);
		request.setAttribute("key", "");
		return "Product/productList";
	}
	//跳转到产品搜索
	@RequestMapping("productListByKeyAndType")
	public String productListByKeyAndType(HttpServletRequest request,@RequestParam Integer type,
			@RequestParam Integer page,@RequestParam String key ){
		PageBean<Product> product=productService.findProductByNameAndType(key, type, page);
		List<Product_Type> typeList=productService.findAllPt();
		request.setAttribute("typeList", typeList);
		request.setAttribute("pageBean", product);
		request.setAttribute("type1", type);
		request.setAttribute("key", key);
		return "Product/productList";
	}
	//跳转到产品添加
	@RequestMapping("productAdd")
	public String productAdd(HttpServletRequest request ){
		List<Product_Type> typeList=productService.findAllPt();
		request.setAttribute("typeList", typeList);
		request.getSession().setAttribute("product", product);
		return "Product/productAdd";
	}
	//跳转到产品修改
	@RequestMapping("productEdit")
	public String productEdit(HttpServletRequest request ){
		
		String biaoji=(String) request.getSession().getAttribute("biaoji");
		List<Product_Type> typeList=productService.findAllPt();
		request.setAttribute("typeList", typeList);
		Integer id=Integer.parseInt(request.getParameter("id").trim());
		Product product=productService.findProductById(id);
		Product Sessionp=(Product) request.getSession().getAttribute("product");
		if(biaoji==null){
			ProductUtil.setClean(request);
		}
		String kitId=product.getKitid();
		if(kitId!=null && !kitId.equals("")){

			String[] kitIds=kitId.split(",");
			if(biaoji==null){
				product.getSkitList().clear();
				for(String s:kitIds){
					Software soft=resourceServiceImpl.findSoftById(Integer.parseInt(s));
					product.getSkitList().add(soft);
				}
			}else{
				product.setSkitList(Sessionp.getSkitList());
			}
		}else if(Sessionp!=null && Sessionp.getSkitList().size()>0){
			ArrayList<Software> a=(ArrayList<Software>) Sessionp.getSkitList();	
			product.setSkitList((List<Software>) a.clone());
			//缓冲清空
			Sessionp.getSkitList().clear();
		}
		String driveId=product.getPdriveid();
		System.out.println("driveId:"+driveId);
		if(driveId!=null && !driveId.equals("")){
			System.out.println("驱动有");
			String[] driveIds=driveId.split(",");
			if(biaoji==null){		
				product.getSdriveList().clear();
				for(String s:driveIds){
					Software soft=resourceServiceImpl.findSoftById(Integer.parseInt(s));
					product.getSdriveList().add(soft);
				}
			}else{
				System.out.println("驱动有，session有");
				product.setSdriveList(Sessionp.getSdriveList());

			}
		}else if(Sessionp!=null && Sessionp.getSdriveList().size()>0){
			System.out.println();
			System.out.println("驱动没有，session有");
			System.out.println(product.getSdriveList().size());
			System.out.println(Sessionp.getSdriveList().size());
			ArrayList<Software> a=(ArrayList<Software>) Sessionp.getSdriveList();	
			product.setSdriveList((List<Software>) a.clone());	
			a.clear();
			System.out.println("长度session"+a.size());
			System.out.println("长度"+product.getSdriveList().size());
		}
		String firmwareId=product.getPfirmwareid();
		if(firmwareId!=null && !firmwareId.equals("")){
			String[] firmwareIds=firmwareId.split(",");
			if(biaoji==null){
				product.getSfirmwareList().clear();
				for(String s:firmwareIds){
					Software soft=resourceServiceImpl.findSoftById(Integer.parseInt(s));
					product.getSfirmwareList().add(soft);
				}
			}else{
				product.setSfirmwareList(Sessionp.getSfirmwareList());
			}
		}else if(Sessionp!=null && Sessionp.getSfirmwareList().size()>0){
			ArrayList<Software> a=(ArrayList<Software>) Sessionp.getSfirmwareList();	
			product.setSfirmwareList((List<Software>) a.clone());
			Sessionp.getSfirmwareList().clear();
		}
		String guideId=product.getPguideid();
		if(guideId!=null && !guideId.equals("")){
			String[] guideIds=guideId.split(",");
			if(biaoji==null){
				product.getSguideList().clear();
				for(String s:guideIds){
					Software soft=resourceServiceImpl.findSoftById(Integer.parseInt(s));
					product.getSguideList().add(soft);
				}
			}else{
				product.setSguideList(Sessionp.getSguideList());
			}
		}else if(Sessionp!=null && Sessionp.getSguideList().size()>0){
			ArrayList<Software> a=(ArrayList<Software>) Sessionp.getSguideList();	
			product.setSguideList((List<Software>) a.clone());
			Sessionp.getSguideList().clear();
		}
		String toolId=product.getPtoolid();
		if(toolId!=null && !toolId.equals("")){
			String[] toolIds=toolId.split(",");
			if(biaoji==null){
				product.getStoolList().clear();
				for(String s:toolIds){
					Software soft=resourceServiceImpl.findSoftById(Integer.parseInt(s));
					product.getStoolList().add(soft);
				}	
			}else{

				product.setStoolList(Sessionp.getStoolList());
			}
		}else if(Sessionp!=null && Sessionp.getStoolList().size()>0){
			ArrayList<Software> a=(ArrayList<Software>) Sessionp.getStoolList();	
			product.setStoolList((List<Software>) a.clone());
			Sessionp.getStoolList().clear();
		}
		String videoId=product.getVideoid();
		if(videoId!=null && !videoId.equals("")){
			String[] videoIds=videoId.split(",");
			if(biaoji==null){
				product.getVideoList().clear();
				for(String s:videoIds){
					Product_Video e=resourceServiceImpl.findByVid(Integer.parseInt(s));
					product.getVideoList().add(e);
				}
			}else{
				
				product.setVideoList(Sessionp.getVideoList());
			}
		}else if(Sessionp!=null && Sessionp.getVideoList().size()>0){
			ArrayList<Product_Video> a=(ArrayList<Product_Video>) Sessionp.getVideoList();	
			product.setVideoList((List<Product_Video>) a.clone());
			Sessionp.getVideoList().clear();
		}
		request.setAttribute("type1", product.getProduct_Type().getPtid());
		request.getSession().setAttribute("product", product);
		System.out.println("最后"+product.getSdriveList().size());
		request.getSession().setAttribute("biaoji", null);
		return "Product/productEdit";
	}
	//跳转到产品选择
	@RequestMapping("choiceRes")
	public String choiceRes(HttpServletRequest request ){
		//得到类型
		String type=request.getParameter("type");
		String page=request.getParameter("page");
		int i=Integer.parseInt(type.trim());
		switch (i) {
		case 0:
			//视频
			//分页视频
			PageBean<Product_Video> video=resourceServiceImpl.findVideoAll(Integer.parseInt(page));
			request.setAttribute("pageBean", video);
			break;
		case 1:
			//驱动
			PageBean<Software> pageBean=resourceServiceImpl.findSoftwareByTypeAndName("",i,Integer.parseInt(page));
			request.setAttribute("pageBean", pageBean);
			break;
		case 2:
			//固件
			PageBean<Software> pageBean1=resourceServiceImpl.findSoftwareByTypeAndName("",i,Integer.parseInt(page));
			request.setAttribute("pageBean", pageBean1);
			break;
		case 3:
			//工具
			PageBean<Software> pageBean2=resourceServiceImpl.findSoftwareByTypeAndName("",i,Integer.parseInt(page));
			request.setAttribute("pageBean", pageBean2);
			break;
		case 4:
			//指南
			PageBean<Software> pageBean3=resourceServiceImpl.findSoftwareByTypeAndName("",i,Integer.parseInt(page));
			request.setAttribute("pageBean", pageBean3);
			break;
		case 5:
			//二次开发包
			PageBean<Software> pageBean4=resourceServiceImpl.findSoftwareByTypeAndName("",i,Integer.parseInt(page));
			request.setAttribute("pageBean", pageBean4);
			break;
		}
		request.getSession().setAttribute("biaoji", "biaoji");
		request.setAttribute("type", i);
		if(i==0){
			return "Product/choiceResVidio";
		}
		
		return "Product/choiceRes";
	}
	//跳转到产品选择明细(下载资源)
	@RequestMapping("choiceResDetail")
	public String choiceResDetail(HttpServletRequest request ){
		String type=request.getParameter("type");
		request.setAttribute("type", type);
		request.getSession().setAttribute("biaoji", "biaoji");
		return "Product/choiceResDetail";
	}
	
	//跳转到产品选择明细(视频)
	@RequestMapping("choiceResVidioDetail")
	public String choiceResVidioDetail(HttpServletRequest request ){
		request.getSession().setAttribute("biaoji", "biaoji");
		return "Product/choiceResVidioDetail";
	}
	
	//跳转到产品提交添加
	@RequestMapping("productSumbbitAdd")
	public String productSumbbitAdd(HttpServletRequest request){
		SingleUpload upload=new SingleUpload(6);
		Product product=new Product();
		//产品名称
		String name=request.getParameter("name");
		product.setPname(name);
		//产品类型
		String type=request.getParameter("type");
		product.setProduct_Type(productService.findByPtaname(Integer.parseInt(type.trim())));
		//产品描述
		String description=request.getParameter("description");
		product.setPintroduce(description);
		//产品图片
		String res=upload.resourceUpload(request);
		if(upload.getAllPath() == null ){
			request.setAttribute("result", res);
			return "Result/401";
		}else{
			product.setPimage(upload.getAllPath());
		}
		//产品资源（页面选择的）
		Product Sessionp=(Product) request.getSession().getAttribute("product");
		product.setPdriveid(ProductUtil.listToString(Sessionp.getSdriveList(),','));
		product.setPfirmwareid(ProductUtil.listToString(Sessionp.getSfirmwareList(), ','));
		product.setPguideid(ProductUtil.listToString(Sessionp.getSguideList(), ','));
		product.setPtoolid(ProductUtil.listToString(Sessionp.getStoolList(), ','));
		product.setKitid(ProductUtil.listToString(Sessionp.getSkitList(),','));
		product.setVideoid(ProductUtil.listToStringVideo(Sessionp.getVideoList(),','));
		boolean result=productService.insertProduct(product);
		if(result){
			return  "Result/200";
		}
		return "Result/444";	
	}
	//跳转到产品提交修改
	@RequestMapping("productSumbbitEdit")
	public String productSumbbitEdit(HttpServletRequest request){
		SingleUpload upload=new SingleUpload(6);
		Integer id=Integer.parseInt(request.getParameter("id").trim());
		Product product=productService.findProductById(id);
		//产品名称
		String name=request.getParameter("name");
		product.setPname(name);
		//产品类型
		String type=request.getParameter("type");
		product.setProduct_Type(productService.findByPtaname(Integer.parseInt(type.trim())));
		//产品描述
		String description=request.getParameter("description");
		product.setPintroduce(description);
		//产品图片
		upload.resourceUpload(request);
		if(upload.getAllPath() == null ){
			product.setPimage(product.getPimage());
		}else{
			product.setPimage(upload.getAllPath());
		}
		//在数据库查出该产品各种资源（加上页面选择的资源）
		Product Sessionp=(Product) request.getSession().getAttribute("product");
		product.setPdriveid(ProductUtil.listToString(Sessionp.getSdriveList(),','));
		product.setPfirmwareid(ProductUtil.listToString(Sessionp.getSfirmwareList(), ','));
		product.setPguideid(ProductUtil.listToString(Sessionp.getSguideList(), ','));
		product.setKitid(ProductUtil.listToString(Sessionp.getSkitList(),','));
		product.setPtoolid(ProductUtil.listToString(Sessionp.getStoolList(), ','));
		product.setVideoid(ProductUtil.listToStringVideo(Sessionp.getVideoList(),','));
		
		boolean result=productService.updateProduct(product);
		if(result){
			return  "Result/200";
		}
		return "Result/444";	
	}
	//产品删除
	@ResponseBody
	@RequestMapping("productDel")
	public String productDel(HttpServletRequest request ,@RequestParam String id){
		String[] ids=id.split(",");	//批量删除
		try{
			for(String id1:ids){
				Integer nId=Integer.parseInt(id1.trim());
				Product product=productService.findProductById(nId);
				productService.deleteProduct(product);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return "200";
	}
	//产品选择（下载资源）
	@RequestMapping("choiceResByIds")
	public String choiceResByIds(HttpServletRequest request ){
		System.out.println("进入了session添加");
		Product product=(Product) request.getSession().getAttribute("product");
		String[] batch=request.getParameterValues("batch");
		if(batch==null){
			request.setAttribute("result", "你没有选择需要添加的资源");
			return "Result/444";
		}
		String typeString=request.getParameter("type").trim();
		int i=Integer.parseInt(typeString.trim());
		switch (i) {
		case 1:
			//驱动
			List<Software> softSet=product.getSdriveList();
			if(softSet.size()==0){
				for(String id:batch){
					//把资源设置到产品上，没保存数据库
					Software soft=resourceServiceImpl.findSoftById(Integer.parseInt(id.trim()));
					softSet.add(soft);
				}
			}else{
				for(String id:batch){
					boolean result=false;
					Software soft=resourceServiceImpl.findSoftById(Integer.parseInt(id.trim()));
					int j=Integer.parseInt(id.trim());
					for(Software v:softSet){
						if(result=(v.getSid()==j)){
							break;
						}
					}
					if(!result){
						softSet.add(soft);
					}
				}	
			}
			break;
		case 2:
			//固件
			List<Software> softSet1=product.getSfirmwareList();
			if(softSet1.size()==0){
				for(String id:batch){
					//把资源设置到产品上，没保存数据库
					Software soft=resourceServiceImpl.findSoftById(Integer.parseInt(id.trim()));
					softSet1.add(soft);
				}
			}else{
				for(String id:batch){
					boolean result=false;
					Software soft=resourceServiceImpl.findSoftById(Integer.parseInt(id.trim()));
					int j=Integer.parseInt(id.trim());
					for(Software v:softSet1){
						if(result=(v.getSid()==j)){
							break;
						}
					}
					if(!result){
						softSet1.add(soft);
					}
				}	
			}
			break;
		case 3:
			//工具
			List<Software> softSet2=product.getStoolList();
			if(softSet2.size()==0){
				for(String id:batch){
					//把资源设置到产品上，没保存数据库
					Software soft=resourceServiceImpl.findSoftById(Integer.parseInt(id.trim()));
					softSet2.add(soft);
				}
			}else{
				for(String id:batch){
					boolean result=false;
					Software soft=resourceServiceImpl.findSoftById(Integer.parseInt(id.trim()));
					int j=Integer.parseInt(id.trim());
					for(Software v:softSet2){
						if(result=(v.getSid()==j)){
							break;
						}
					}
					if(!result){
						softSet2.add(soft);
					}
				}	
			}
			break;
		case 4:
			//指南
			List<Software> softSet3=product.getSguideList();
			if(softSet3.size()==0){
				for(String id:batch){
					//把资源设置到产品上，没保存数据库
					Software soft=resourceServiceImpl.findSoftById(Integer.parseInt(id.trim()));
					softSet3.add(soft);
				}
			}else{
				for(String id:batch){
					boolean result=false;
					Software soft=resourceServiceImpl.findSoftById(Integer.parseInt(id.trim()));
					int j=Integer.parseInt(id.trim());
					for(Software v:softSet3){
						if(result=(v.getSid()==j)){
							break;
						}
					}
					if(!result){
						softSet3.add(soft);
					}
				}	
			}
			break;
		case 5:
			//二次开发包
			List<Software> softSet4=product.getSkitList();
			if(softSet4.size()==0){
				for(String id:batch){
					//把资源设置到产品上，没保存数据库
					Software soft=resourceServiceImpl.findSoftById(Integer.parseInt(id.trim()));
					softSet4.add(soft);
				}
			}else{
				for(String id:batch){
					boolean result=false;
					Software soft=resourceServiceImpl.findSoftById(Integer.parseInt(id.trim()));
					int j=Integer.parseInt(id.trim());
					for(Software v:softSet4){
						if(result=(v.getSid()==j)){
							break;
						}
					}
					if(!result){
						softSet4.add(soft);
					}
				}	
			}
			break;
		}
		request.getSession().setAttribute("biaoji", "biaoji");
		request.setAttribute("type",i);
		return "Result/0";
		
	}
	//产品选择（下载视频）
	@RequestMapping("choiceResVidioByIds")
	public String choiceResVidioByIds(HttpServletRequest request ){
		Product product=(Product) request.getSession().getAttribute("product");
		String[] batch=request.getParameterValues("batch");
		if(batch==null){
			request.setAttribute("result", "你没有选择需要添加的资源");
			return "Result/444";
		}
		List<Product_Video> vidioSet=product.getVideoList();
		if(vidioSet.size()==0){
			for(String id:batch){
				//把资源设置到产品上，没保存数据库
				Product_Video vidio=resourceServiceImpl.findByVid(Integer.parseInt(id));
				vidioSet.add(vidio);
			}
		}else{
			for(String id:batch){
				boolean result=false;
				Product_Video vidio=resourceServiceImpl.findByVid(Integer.parseInt(id));
				int j=Integer.parseInt(id);
				for(Product_Video v:vidioSet){
					if(result=(v.getPvid()==j)){
						break;
					}
				}
				if(!result){
					vidioSet.add(vidio);
				}
			}	
		}
		request.getSession().setAttribute("biaoji", "biaoji");
		request.setAttribute("type",0);
		request.setAttribute("page", 1);
		return "Result/0";
	}
	//产品选择删除（下载视频）
	@RequestMapping("delchoiceResVidio")
	public String delchoiceResVidio(HttpServletRequest request ){
		Product product=(Product) request.getSession().getAttribute("product");
		List<Product_Video> vidioSet=product.getVideoList();
		String id=request.getParameter("id");
		int i=Integer.parseInt(id.trim());
		Product_Video pv=null;
		for(Product_Video v:vidioSet){
			if(v.getPvid()==i){
				pv=v;
				break;
			}
		}
		if(pv!=null){
			vidioSet.remove(pv);
			pv=null;
		}
		request.getSession().setAttribute("biaoji", "biaoji");
		return choiceResVidioDetail(request);
	}
	//产品选择删除（下载资源）
	@RequestMapping("delchoiceRes")
	public String delchoiceRes(HttpServletRequest request ){
		Product product=(Product) request.getSession().getAttribute("product");
		String typeString=request.getParameter("type").trim();
		String id=request.getParameter("id");
		int i=Integer.parseInt(typeString.trim());
		Software s=null;
		switch (i) {
		case 1:
			//驱动
			List<Software> softSet=product.getSdriveList();
			//把资源设置到产品上，没保存数据库
			for(Software v:softSet){
				if(v.getSid()==Integer.parseInt(id.trim())){
					s=v;
					break;
				}
			}
			if(s!=null){
				softSet.remove(s);
				s=null;
			}
			break;
		case 2:
			//固件
			List<Software> softSet1=product.getSfirmwareList();
			//把资源设置到产品上，没保存数据库
			for(Software v:softSet1){
				if(v.getSid()==Integer.parseInt(id.trim())){
					s=v;
					break;
				}
			}
			if(s!=null){
				softSet1.remove(s);
				s=null;
			}
			break;
		case 3:
			//工具
			List<Software> softSet2=product.getStoolList();
			//把资源设置到产品上，没保存数据库
			for(Software v:softSet2){
				if(v.getSid()==Integer.parseInt(id.trim())){
					s=v;
					break;
				}
			}
			if(s!=null){
				softSet2.remove(s);
				s=null;
			}
			break;
		case 4:
			//指南
			List<Software> softSet3=product.getSguideList();
			//把资源设置到产品上，没保存数据库
			for(Software v:softSet3){
				if(v.getSid()==Integer.parseInt(id.trim())){
					s=v;
					break;
				}
			}
			if(s!=null){
				softSet3.remove(s);
				s=null;
			}
			break;
		case 5:
			//二次开发包
			List<Software> softSet4=product.getSkitList();
			//把资源设置到产品上，没保存数据库
			for(Software v:softSet4){
				if(v.getSid()==Integer.parseInt(id.trim())){
					s=v;
					break;
				}
			}
			if(s!=null){
				softSet4.remove(s);
				s=null;
			}
			break;
		}
		request.getSession().setAttribute("biaoji", "biaoji");
		return choiceResDetail(request);
	}
}
