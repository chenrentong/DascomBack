package com.dascom.product.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Post
{
	private Integer postid;
	private String title;
	private String content;
	private Date posttime;
	private Integer visits;
	private Integer cnumber;
	private Product_Type product_Type;
	private User user;
	private Set<Comments> comments = new HashSet<Comments>();
	public Integer getPostid()
	{
		return postid;
	}
	public void setPostid(Integer postid)
	{
		this.postid = postid;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public Date getPosttime()
	{
		return posttime;
	}
	public void setPosttime(Date posttime)
	{
		this.posttime = posttime;
	}
	public Integer getVisits()
	{
		return visits;
	}
	public void setVisits(Integer visits)
	{
		this.visits = visits;
	}
	public Integer getCnumber()
	{
		return cnumber;
	}
	public void setCnumber(Integer cnumber)
	{
		this.cnumber = cnumber;
	}
	public Product_Type getProduct_Type()
	{
		return product_Type;
	}
	public void setProduct_Type(Product_Type product_Type)
	{
		this.product_Type = product_Type;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public Set<Comments> getComments()
	{
		return comments;
	}
	public void setComments(Set<Comments> comments)
	{
		this.comments = comments;
	}
}
