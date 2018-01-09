package com.dascom.product.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dascom.product.entity.Software;
import com.dascom.product.entity.Software_Type;


public class SoftwareDao 
{
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction trans;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		
		if(session ==null || !session.isOpen()){
			 session = sessionFactory.openSession();
		}else{
			 return session;
		}
		return session;
	}
	/*public Session getSession(){
		if(session==null || !session.isOpen()){
			sessionFactory.openSession();
		}
		session = sessionFactory.getCurrentSession();
		return session;
	}*/
	//查询所有资源
	public List<Software> getAllSoftware(int begin,int limit){
		String hql = "from Software";
		Query query = getSession().createQuery(hql);
		query.setMaxResults(limit);
		query.setFirstResult(begin);
		List<Software> softList=query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return softList;
	}
	
	//查询总资源记录数
	public int findCountAll(){
		String hql = "select count(*) from Software";
		List<Long> list = getSession().createQuery(hql).list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		if(list != null && list.size() > 0)
		{
			return list.get(0).intValue();
		}
		return 0;
	}
	
	//查询总资源类型
	public List<Software_Type> findAllSoftwareType(){
		String hql = " from Software_Type";
		List<Software_Type> list = getSession().createQuery(hql).list();	
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return list;
	}
	
	//根据id得到资源类型
	public Software_Type findSoftWareTypeById(int id){
		Software_Type type=(Software_Type) getSession().get(Software_Type.class, id);
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return type;
	}
	
	//查询分类和关键字资源
	public List<Software> findSoftwareByTypeAndKey(String keyword, Integer softwareTypeId,Integer begin,Integer limit){
		String hql = "from Software where sname  like '%"+keyword+"%' and stid = ?";
		Query query = getSession().createQuery(hql);	
		query.setInteger(0, softwareTypeId);
		query.setMaxResults(limit);
		query.setFirstResult(begin);
		List<Software> s=query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return s;
	}
	
	//查询关键字资源
	public List<Software> findSoftwareByKey(String keyword,Integer begin,Integer limit){
		String hql = "from Software where sname like '%"+keyword+"%'";
		Query query = getSession().createQuery(hql);
		query.setMaxResults(limit);
		query.setFirstResult(begin);
		List<Software> list =query.list();	
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return list;
	}
	
	//分类和关键字资源条数
	public int findCountByKeyAndType(String keyword,Integer softwareTypeId){
		String hql = "select count(*) from Software where sname  like '%"+keyword+"%' and stid = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, softwareTypeId);
		List<Long> list = query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		if(list != null && list.size() > 0)
		{
			return list.get(0).intValue();
		}
		return 0;
	}
	
	//关键字资源条数
	public int findCountByKey(String keyword){
		String hql = "select count(*) from Software where sname like '%"+keyword+"%'";
		List<Long> list = getSession().createQuery(hql).list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		if(list != null && list.size() > 0)
		{
			return list.get(0).intValue();
		}
		return 0;
	}
	
	//添加
	public int addSoftware(Software software){
		int result=0;
		result=(Integer) getSession().save(software);
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return result;
	}
	
	//修改
	public void updateSoftware(Software software){
		trans = getSession().beginTransaction();
		getSession().saveOrUpdate(software);
		trans.commit();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
	}
	
	//删除
	public void delSoftware(Software software){
		trans = getSession().beginTransaction();
		getSession().delete(software);
		trans.commit();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
	}
	
	//根据id查询资源
	public Software getSoftwareById(Integer id){
		Software software=(Software) getSession().get(Software.class, id);
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return software;
	}
}
