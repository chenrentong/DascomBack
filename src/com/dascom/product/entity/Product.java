package com.dascom.product.entity;

import java.util.ArrayList;
import java.util.List;


public class Product
{
	private Integer pid;
	private String pname;
	private String pimage;
	//产品类型类的外键，使用产品类型对象
	private Integer ptid;
	private Product_Type product_Type;
	private String pdriveid;
	private String pfirmwareid;
	private String ptoolid;
	private String pguideid;
	private String kitid;
	private String softid;
	private String videoid;
	//介绍
	private String pintroduce;

	//资源集合
	private List<Software> sdriveList=new ArrayList<Software>();
	private List<Software> sfirmwareList=new ArrayList<Software>();
	private List<Software> stoolList=new ArrayList<Software>();
	private List<Software> sguideList=new ArrayList<Software>();
	private List<Software> skitList=new ArrayList<Software>();
	private List<Software> softList=new ArrayList<Software>();
	private List<Product_Video> videoList=new ArrayList<Product_Video>();
	
	public Integer getPid()
	{
		return pid;
	}
	public void setPid(Integer pid)
	{
		this.pid = pid;
	}
	public String getPname()
	{
		return pname;
	}
	public void setPname(String pname)
	{
		this.pname = pname;
	}
	public String getPimage()
	{
		return pimage;
	}
	public void setPimage(String pimage)
	{
		this.pimage = pimage;
	}
	public Product_Type getProduct_Type()
	{
		return product_Type;
	}
	public void setProduct_Type(Product_Type product_Type)
	{
		this.product_Type = product_Type;
	}
	public String getPintroduce()
	{
		return pintroduce;
	}
	public void setPintroduce(String pintroduce)
	{
		this.pintroduce = pintroduce;
	}
	public String getPdriveid() {
		return pdriveid;
	}
	public void setPdriveid(String pdriveid) {
		this.pdriveid = pdriveid;
	}
	public String getPfirmwareid() {
		return pfirmwareid;
	}
	public void setPfirmwareid(String pfirmwareid) {
		this.pfirmwareid = pfirmwareid;
	}
	public String getPtoolid() {
		return ptoolid;
	}
	public void setPtoolid(String ptoolid) {
		this.ptoolid = ptoolid;
	}
	public String getPguideid() {
		return pguideid;
	}
	public void setPguideid(String pguideid) {
		this.pguideid = pguideid;
	}
	public String getKitid() {
		return kitid;
	}
	public void setKitid(String kitid) {
		this.kitid = kitid;
	}
	public String getSoftid() {
		return softid;
	}
	public void setSoftid(String softid) {
		this.softid = softid;
	}
	
	public String getVideoid() {
		return videoid;
	}
	public void setVideoid(String videoid) {
		this.videoid = videoid;
	}
	public List<Product_Video> getVideoList() {
		return videoList;
	}
	public void setVideoList(List<Product_Video> videoList) {
		this.videoList = videoList;
	}
	public List<Software> getSdriveList() {
		return sdriveList;
	}
	public void setSdriveList(List<Software> sdriveList) {
		this.sdriveList = sdriveList;
	}
	public List<Software> getSfirmwareList() {
		return sfirmwareList;
	}
	public void setSfirmwareList(List<Software> sfirmwareList) {
		this.sfirmwareList = sfirmwareList;
	}
	public List<Software> getStoolList() {
		return stoolList;
	}
	public void setStoolList(List<Software> stoolList) {
		this.stoolList = stoolList;
	}
	public List<Software> getSguideList() {
		return sguideList;
	}
	public void setSguideList(List<Software> sguideList) {
		this.sguideList = sguideList;
	}
	public List<Software> getSkitList() {
		return skitList;
	}
	public void setSkitList(List<Software> skitList) {
		this.skitList = skitList;
	}
	public List<Software> getSoftList() {
		return softList;
	}
	public void setSoftList(List<Software> softList) {
		this.softList = softList;
	}
	public Integer getPtid() {
		return ptid;
	}
	public void setPtid(Integer ptid) {
		this.ptid = ptid;
	}
	
}
