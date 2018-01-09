package com.dascom.product.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dascom.product.entity.DataSharing;


public class DataSharingDao {
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
/*	public Session getSession(){
		if(session==null || !session.isOpen()){
			sessionFactory.openSession();
		}
		session = sessionFactory.getCurrentSession();
		return session;
	}*/
	
	public List<DataSharing> findAllDataSharing(int begin,int limit){
		String hql = "from DataSharing";
		Query query = getSession().createQuery(hql);
		query.setMaxResults(limit);
		query.setFirstResult(begin);
		List<DataSharing> list=query.list();
		if(session!=null && getSession().isOpen()){
			getSession().close();
		}

		return list;
		
	}
	
	public int findCountAll()
	{
		String hql = "select count(*) from DataSharing";
		List<Long> list = getSession().createQuery(hql).list();
		if(session!=null && getSession().isOpen()){
			getSession().close();
		}
		if(list != null && list.size() > 0)
		{
			return list.get(0).intValue();
		}
		return 0;
	}
	
	public int findCountAllByKey(String key)
	{
		String hql = "select count(*) from DataSharing where name like '%"+key+"%'";
		List<Long> list = getSession().createQuery(hql).list();
		if(session!=null && getSession().isOpen()){
			getSession().close();
		}
		if(list != null && list.size() > 0)
		{
			return list.get(0).intValue();
		}
		return 0;
	}
	
	public List<DataSharing> findAllDataSharing(int begin,int limit,String key){
		String hql = "from DataSharing where name like '%"+key+"%'";
		Query query = getSession().createQuery(hql);
		query.setMaxResults(limit);
		query.setFirstResult(begin);
		List<DataSharing> list=query.list();
		if(session!=null && getSession().isOpen()){
			getSession().close();
		}
		return list;
	}
	
	//增加
	public int addDataSharing(DataSharing dataSharing){
		 int count=(Integer) getSession().save(dataSharing);
		 if(session!=null && getSession().isOpen()){
				getSession().close();
			}
		 return count;
	}
	
	//修改
	public void updateDateSharing(DataSharing dataSharing){
		trans = getSession().beginTransaction();
		getSession().saveOrUpdate(dataSharing);
		if(session!=null && getSession().isOpen()){
			getSession().close();
		}
		trans.commit();	
		
	}
	//删除
	public void deleteDateSharing(DataSharing dataSharing){
		trans = getSession().beginTransaction();
		getSession().delete(dataSharing);
		if(session!=null && getSession().isOpen()){
			getSession().close();
		}
		trans.commit();
		
		 
	}
	public DataSharing findShareInformationById(int id){
		DataSharing d=(DataSharing) getSession().get(DataSharing.class, id);
		if(session!=null && getSession().isOpen()){
			getSession().close();
		}
		return d;
		
	}
}
