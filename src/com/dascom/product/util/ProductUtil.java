package com.dascom.product.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.dascom.product.entity.Product;
import com.dascom.product.entity.Product_Video;
import com.dascom.product.entity.Software;

public class ProductUtil {
	
	public static void setClean(HttpServletRequest request){
		Product product=(Product) request.getSession().getAttribute("product");
		if(product != null){
			product.getVideoList().clear();
			product.getSdriveList().clear();
			product.getSfirmwareList().clear();
			product.getSguideList().clear();
			product.getSkitList().clear();
			product.getStoolList().clear();
		}
	}
	
	public static String listToString(List<Software> list, char separator) {  
		StringBuilder sb = new StringBuilder();    
		for (int i = 0; i < list.size(); i++) {       
			if (i == list.size() - 1) {           
				sb.append(list.get(i).getSid());       
				} else {         
					sb.append(list.get(i).getSid());           
					sb.append(separator);       
					}   
			}    
		return sb.toString();

		}
	
	public static String listToStringVideo(List<Product_Video> list, char separator) {  
		StringBuilder sb = new StringBuilder();    
		for (int i = 0; i < list.size(); i++) {       
			if (i == list.size() - 1) {           
				sb.append(list.get(i).getPvid());       
				} else {         
					sb.append(list.get(i).getPvid());           
					sb.append(separator);       
					}   
			}    
		return sb.toString();

		}
}
