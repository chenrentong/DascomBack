package com.dascom.product.entity;

import com.dascom.product.entity.Product;

public class Product_Video
{
	private Integer pvid;
	private String pvname;
	private String pvpath;
	private String size;
	private String suffix;
	
	//产品类的外键，使用产品对象
	private Product product;
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public Integer getPvid()
	{
		return pvid;
	}
	public void setPvid(Integer pvid)
	{
		this.pvid = pvid;
	}
	public String getPvname()
	{
		return pvname;
	}
	public void setPvname(String pvname)
	{
		this.pvname = pvname;
	}
	public String getPvpath()
	{
		return pvpath;
	}
	public void setPvpath(String pvpath)
	{
		this.pvpath = pvpath;
	}
	public Product getProduct()
	{
		return product;
	}
	public void setProduct(Product product)
	{
		this.product = product;
	}
	
}
