package com.dascom.product.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



import org.springframework.transaction.support.TransactionSynchronizationManager;

import aa.HibernateTemplate;

import com.dascom.product.entity.Partners;

public class PartnersDao {
	
	/*public List<Partners> findPartners(){
		String hql = "FROM Partners ";
		List<Partners> list = this.find(hql);
		if(list != null && list.size() > 0)
		{
			return list;
		}
		return null;
	}*/
	
	private SessionFactory sessionFactory;
	//private Transaction trans;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/*public Session getSession(){
		
		if(session ==null || !session.isOpen()){
			 session = sessionFactory.openSession();
		}else{
			 return session;
		}
		return session;
	}*/
	public Session getSession(){
		Object value = TransactionSynchronizationManager.getResource(getSessionFactory());  
		
        if (null == value) {  
        	System.out.println("等于null");
            return sessionFactory.openSession();  
        } else {  
        	System.out.println("不等于null");
            return this.sessionFactory.getCurrentSession();              
        }  
	}
	public void is(){
		if(this.sessionFactory.getCurrentSession()==null){
			System.out.println("当前线程为null");
		}
	}
	
	public List<Partners> findAllPartners(int begin,int limit){
		System.out.println("进入了分页查询");
		String hql = "from Partners";
		Query query = getSession().createQuery(hql);
		query.setMaxResults(limit);
		query.setFirstResult(begin);
		List<Partners> list=query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return list;
		
	}
	
	public int findCountAll()
	{
		String hql = "select count(*) from Partners";
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
	
	public int findCountAllByKey(String key)
	{
		String hql = "select count(*) from Partners where companyName like '%"+key+"%'";
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
	
	public List<Partners> findAllPartners(int begin,int limit,String key){
		String hql = "from Partners where companyName like '%"+key+"%'";
		Query query = getSession().createQuery(hql);
		query.setMaxResults(limit);
		query.setFirstResult(begin);
		List<Partners> list=query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return list;
	}
	//修改
	public void updatePartners(Partners partners){
		//trans = getSession().beginTransaction();
		getSession().saveOrUpdate(partners);
		//trans.commit();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
	}
	//删除
	public void deletePartners(Partners partners){
		//trans = getSession().beginTransaction();
		getSession().delete(partners);
		//trans.commit();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
	}
	public Partners findPartnersById(int id){
		Partners p=(Partners) getSession().get(Partners.class, id);
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return p;
		
	}
	//增加
	public Integer insertPartners(Partners partners){
		int count=(Integer) getSession().save(partners);
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return count;
	}
			
	public Partners findPartnersByUid(int uid){
		String hql = "from Partners where uid=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, uid);
		List<Partners> partners=query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		if(partners.size()>0){
			return partners.get(0);
		}
		
		return null;
	}
}
