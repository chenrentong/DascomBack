package com.dascom.product.entity;

import java.util.HashSet;
import java.util.Set;

public class User
{
	private Integer uid;
	private String username;
	private String password;
	private String email;
	private String phone;
	private Integer auditstate;
	private Set<Post> posts = new HashSet<Post>();
	private Set<Comments> comments = new HashSet<Comments>();
	private Set<Template> templates = new HashSet<Template>();
	public Integer getUid()
	{
		return uid;
	}
	public void setUid(Integer uid)
	{
		this.uid = uid;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public Integer getAuditstate()
	{
		return auditstate;
	}
	public void setAuditstate(Integer auditstate)
	{
		this.auditstate = auditstate;
	}
	public Set<Post> getPosts()
	{
		return posts;
	}
	public void setPosts(Set<Post> posts)
	{
		this.posts = posts;
	}
	public Set<Comments> getComments()
	{
		return comments;
	}
	public void setComments(Set<Comments> comments)
	{
		this.comments = comments;
	}
	public Set<Template> getTemplates()
	{
		return templates;
	}
	public void setTemplates(Set<Template> templates)
	{
		this.templates = templates;
	}
}
