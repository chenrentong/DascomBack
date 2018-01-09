package com.dascom.product.entity;

import java.util.Date;

public class Template
{
	private Integer tid;
	private String tname;
	private String tpath;
	private Date ttime;
	//配置用户集合
	private User user;
	public Integer getTid()
	{
		return tid;
	}
	public void setTid(Integer tid)
	{
		this.tid = tid;
	}
	public String getTname()
	{
		return tname;
	}
	public void setTname(String tname)
	{
		this.tname = tname;
	}
	public String getTpath()
	{
		return tpath;
	}
	public void setTpath(String tpath)
	{
		this.tpath = tpath;
	}
	public Date getTtime()
	{
		return ttime;
	}
	public void setTtime(Date ttime)
	{
		this.ttime = ttime;
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
