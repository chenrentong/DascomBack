package com.dascom.product.entity;

import java.util.Date;


public class Comments
{
	private Integer cid;
	private String ccontent;
	private Date ctime;
	private Post post;
	private User user;
	public Integer getCid()
	{
		return cid;
	}
	public void setCid(Integer cid)
	{
		this.cid = cid;
	}
	public String getCcontent()
	{
		return ccontent;
	}
	public void setCcontent(String ccontent)
	{
		this.ccontent = ccontent;
	}
	public Date getCtime()
	{
		return ctime;
	}
	public void setCtime(Date ctime)
	{
		this.ctime = ctime;
	}
	public Post getPost()
	{
		return post;
	}
	public void setPost(Post post)
	{
		this.post = post;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
}
