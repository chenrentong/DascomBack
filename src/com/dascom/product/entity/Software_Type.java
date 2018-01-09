package com.dascom.product.entity;

import java.util.HashSet;
import java.util.Set;

public class Software_Type
{
	private Integer stid;
	private String stname;
	private Set<Software> softwares = new HashSet<Software>();
	public Integer getStid()
	{
		return stid;
	}
	public void setStid(Integer stid)
	{
		this.stid = stid;
	}
	public String getStname()
	{
		return stname;
	}
	public void setStname(String stname)
	{
		this.stname = stname;
	}
	public Set<Software> getSoftwares()
	{
		return softwares;
	}
	public void setSoftwares(Set<Software> softwares)
	{
		this.softwares = softwares;
	}
}
