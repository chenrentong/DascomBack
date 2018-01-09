package com.dascom.product.util;


import org.apache.log4j.Logger;
/**
 * slf4j日志写入
   * 
 */

public class Logg {
		
	 public static	Logger logger=Logger.getLogger(Logg.class);
	
	public static void writeErrorLog(String msg){
		//if(logg == null)logg = getInstance();
		logger.error(msg);
	}
	
	public static  void writeWarnLog(String msg){
		//if(logg == null)logg = getInstance();
		logger.warn(msg);
	}
	
	public  static void writeInfoLog(String msg){
		//if(logg == null)logg = getInstance();
		logger.info(msg);
	}
	
	public static  void writeDebugLog(String msg){
		//if(logg == null)logg = getInstance();
		logger.debug(msg);
	}
	
	public static  void writeTraceLog(String msg){
		//if(logg == null)logg = getInstance();
		logger.trace(msg);
	}
	
	public  void writeException(Exception e){
		//if(logg == null)logg = getInstance();
		logger.error(e.getMessage(), e);
	}
	
}
