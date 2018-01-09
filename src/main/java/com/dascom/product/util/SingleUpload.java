package com.dascom.product.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.dascom.product.controller.PartnersController;

public class SingleUpload {
	
	private  int type;	//上传类型。	1.驱动	2.固件	3.工具	4.指南	5.开发包	6.图片	7.视频	8.合作伙伴资料
	private String suffix;	//上传类型
	private String resourcePath;	//资源路径
	private  String allPath;	//总路径
	private  String result;	//结果
	private long size;	//暂时没有大小限制
	public SingleUpload(int type) {
		this.type=type;
		switch (type) {
		case 1:
			this.resourcePath="drive";
			break;
		case 2:
			this.resourcePath="firmware";
			break;
		case 3:
			this.resourcePath="tool";
			break;	
		case 4:
			this.resourcePath="guide";
			break;
		case 5:
			this.resourcePath="kit";
			break;
		case 6:
			this.resourcePath="image";
			break;
		case 7:
			this.resourcePath="video";
			break;
		case 8:
			this.resourcePath="dataSharing";
			break;
		}
	}
	
	public  String resourceUpload(HttpServletRequest request){
		String realPath=request.getSession().getServletContext().getRealPath("/");
		String path1=AppAddress.tomcatAddress(realPath);
		Date dt=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/HH-mm-ss");
		String formatDate = sdf.format(dt);  
		 // 上传文件存储目录
	     final String UPLOAD_DIRECTORY = "DASCOM/"+resourcePath+"/"+formatDate;
	     try{
				//String LocalPath = request.getServletContext().getRealPath("");
				//创建一个通用的多部分解析器  
				CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
				//判断 request 是否有文件上传,即多部分请求  
				if(multipartResolver.isMultipart(request)){ 
					//转换成多部分request    
					MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
					//取得request中的所有文件名  
					Iterator<String> iter = multiRequest.getFileNames(); 
					
					while(iter.hasNext()){
							//取得上传文件  
						MultipartFile file = multiRequest.getFile(iter.next());  
						if(file != null){  
							//取得当前上传文件的文件名称  
							String myFileName = file.getOriginalFilename();  
							System.out.println("type:"+type);
							System.out.println(resourcePath);
							System.out.println("当前上传文件的名称："+myFileName);
							//上传的类型
							String myFileSux = file.getContentType();
							String prefix=myFileName.substring(myFileName.lastIndexOf(".")+1);
							System.out.println(prefix);
							suffix=prefix;
							System.out.println("上传文件的类型："+myFileSux);
							size =file.getSize();
							if((type==6 && myFileSux.startsWith("image")) || 
									(type==7 && myFileSux.startsWith("video")) ||
										(type!=6 && type!=7 && myFileSux.startsWith("application"))){
								//如果名称不为“”,说明该文件存在，否则说明该文件不存在  
								if(myFileName.trim() !="" ){
									String path =path1+ UPLOAD_DIRECTORY;
									File localFile = new File(path);  
									if(localFile.exists()){
											file.transferTo(new File(path+"/"+myFileName));  
											allPath="/dcpath/"+UPLOAD_DIRECTORY+"/"+myFileName;
											System.out.println("allPath"+allPath);
											
									}else{
											localFile.mkdirs();
											file.transferTo(new File(path+"/"+myFileName)); 
											allPath="/dcpath/"+UPLOAD_DIRECTORY+"/"+myFileName;
											System.out.println("allPath"+allPath);
										}	
									result="上传成功";
									}		
								}else{
									result="上传格式不正确";
									Logg.logger.error("上传格式不正确");;
								}
						
							}		
						}
					}
					
				} catch(Exception e){
					result="程序出错";
					Logg.logger.error("程序出错",e);
					e.printStackTrace();
					
				}
		return result;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public Long getSize() {
		return size;
	}

	public String getAllPath() {
		return allPath;
	}

	public String getSuffix() {
		return suffix;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setSize(long size) {
		this.size = size;
	}
	
}
