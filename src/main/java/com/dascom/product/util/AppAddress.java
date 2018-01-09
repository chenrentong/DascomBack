package com.dascom.product.util;

public class AppAddress {
	/**
     *  得到tomcat根目录 
     * @param RealPath  request.getSession().getServletContext().getRealPath("/")
     * @return
     */
	public static String tomcatAddress(String RealPath){
		System.out.println("tomcat修改前的根目录是:"+RealPath);
		if(RealPath.lastIndexOf("\\")>0){
			//windows
			RealPath=RealPath.substring(0,RealPath.lastIndexOf("\\"));
			RealPath=RealPath.substring(0,RealPath.lastIndexOf("\\"));
			RealPath=RealPath.substring(0,RealPath.lastIndexOf("\\"));
			RealPath=RealPath+"\\"+"dcpath"+"\\";
		}else{
			//linux
			RealPath=RealPath.substring(0,RealPath.lastIndexOf("/"));
			RealPath=RealPath.substring(0,RealPath.lastIndexOf("/"));
			RealPath=RealPath.substring(0,RealPath.lastIndexOf("/"));
			RealPath=RealPath+"/"+"dcpath"+"/";
		}
		System.out.println("tomcat的根目录是:"+RealPath);
		return RealPath;
	}

}
