package com.dascom.product.entity;

import java.util.HashSet;
import java.util.Set;


public class Product_Type
{
	private Integer ptid;
	private String ptname;
	private String ptimage;
	//配置产品集合
	private Set<Product> products = new HashSet<Product>();
	private Set<Post> posts = new HashSet<Post>();
	public Integer getPtid()
	{
		return ptid;
	}
	public void setPtid(Integer ptid)
	{
		this.ptid = ptid;
	}
	public String getPtname()
	{
		return ptname;
	}
	public void setPtname(String ptname)
	{
		this.ptname = ptname;
	}
	public String getPtimage()
	{
		return ptimage;
	}
	public void setPtimage(String ptimage)
	{
		this.ptimage = ptimage;
	}
	public Set<Product> getProducts()
	{
		return products;
	}
	public void setProducts(Set<Product> products)
	{
		this.products = products;
	}
	public Set<Post> getPosts()
	{
		return posts;
	}
	public void setPosts(Set<Post> posts)
	{
		this.posts = posts;
	}
}
