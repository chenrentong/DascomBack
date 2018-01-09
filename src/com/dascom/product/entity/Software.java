package com.dascom.product.entity;


public class Software
{
	private Integer sid;
	private String sname;
	private String spath;
	private String size;
	private String suffix;
	private Software_Type software_Type;
	
	
	
	
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Integer getSid()
	{
		return sid;
	}
	public void setSid(Integer sid)
	{
		this.sid = sid;
	}
	public String getSname()
	{
		return sname;
	}
	public void setSname(String sname)
	{
		this.sname = sname;
	}
	public String getSpath()
	{
		return spath;
	}
	public void setSpath(String spath)
	{
		this.spath = spath;
	}
	public Software_Type getSoftware_Type()
	{
		return software_Type;
	}
	public void setSoftware_Type(Software_Type software_Type)
	{
		this.software_Type = software_Type;
	}
	@Override
	public String toString() {
		return "Software [sid=" + sid + ", sname=" + sname + ", spath=" + spath
				+ ", size=" + size + ", suffix=" + suffix + ", software_Type="
				+ software_Type + "]";
	}
	
	
}
