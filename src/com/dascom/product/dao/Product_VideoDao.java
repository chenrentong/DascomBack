package com.dascom.product.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dascom.product.entity.Product_Type;
import com.dascom.product.entity.Product_Video;


public class Product_VideoDao 
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
	
	//根据产品的ID查询该ID的所有视频
	public List<Product_Video> findByPid(Integer pid)
	{
		String hql = "select pv from Product_Video pv join pv.product p where p.pid = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, pid);
		List<Product_Video> list=query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		if(list != null && list.size() > 0)
		{
			return list;
		}
		return null;
	}
	//根据模糊查询 查询出视频的总记录数
	public int findCountPname(String keyword)
	{
		//String hql = "select count(*) from Product_Video where pvname like ?";
		//String hql = "select count(distinct pvname) from Product_Video where pvname like ?";
		//这样可不可以
		List<Long> list =getSession().createQuery("select count(*) from Product_Video where pvname like '%"+keyword+"%'").list();  
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		if(list != null && list.size() > 0)
		{
			return list.get(0).intValue();
		}
		return 0;
	}
	//根据模糊查询 查询出所有视频教程
	public List<Product_Video> findVideoPname(String keyword, int begin,
			int limit)
	{
		String hql = "from Product_Video where pvname like '%"+keyword+"%'";
		Query query = getSession().createQuery(hql);
		query.setMaxResults(limit);
		query.setFirstResult(begin);
		List<Product_Video> list=query.list();
		//String hql = "select pv.pvpath,distinct pv.pvname from Product_Video pv where pv.pvname like ?";
		/*List<Product_Video> list = this.getHibernateTemplate().execute
				(new PageHibernateCallback<Product_Video>(hql, new Object[]{"%"+keyword+"%"}, begin, limit));*/
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		if(list != null && list.size() > 0)
		{
			return list;
		}
		return null;
	}
	//查询所有视频的总记录数
	public int findCountAll()
	{
		String hql = "select count(*) from Product_Video";
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
	//查询所有视频教程
	public List<Product_Video> findVideoAll(int begin, int limit)
	{
		String hql = "from Product_Video";
		Query query = getSession().createQuery(hql);
		/*List<Product_Video> list = this.getHibernateTemplate().execute
				(new PageHibernateCallback<Product_Video>(hql, new Object[]{}, begin, limit));*/
		query.setMaxResults(limit);
		query.setFirstResult(begin);
		List<Product_Video> list=query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		if(list != null && list.size() > 0)
		{
			return list;
		}
		return null;
	}
	//保存
	public int insert(Product_Video video){
		int result=0;
		result=(Integer) getSession().save(video);
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return result;
	}
	//修改
	public void update(Product_Video video){
		trans = getSession().beginTransaction();
		getSession().saveOrUpdate(video);
		trans.commit();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
	}
	//删除
	public void delete(Product_Video video){	
		trans = getSession().beginTransaction();
		getSession().delete(video);
		trans.commit();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
	}
	//根据id查询视频
	public Product_Video findByVid(int id){
		Product_Video product_Video=(Product_Video) getSession().get(Product_Video.class, id);
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return product_Video;
		
	}
}
