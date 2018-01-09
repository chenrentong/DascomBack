package com.dascom.product.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dascom.product.entity.Product_Type;
import com.dascom.product.entity.User;


public class Product_TypeDao {

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
	//查询所有产品类型
	public List<Product_Type> findAllPt(){
		String hql = "from Product_Type";
		Query query = getSession().createQuery(hql);
		List<Product_Type> list=query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/

		return list;
	}

	//查询所有产品类型的集合(分页)
	public List<Product_Type> findByPtAll(int begin,int limit)
	{
		String hql = "from Product_Type";
		Query query = getSession().createQuery(hql);
		query.setMaxResults(limit);
		query.setFirstResult(begin);
		List<Product_Type> list=query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return list;
	}
	//根据类型id查询打印机类型名称
	public Product_Type findByPtname(Integer ptid)
	{
		Product_Type p=(Product_Type) getSession().get(Product_Type.class, ptid);
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/

		return p;
	}
	//根据关键字查询产品类型
	public List<Product_Type> findPtByName(String typeName,int begin,int limit)
	{
		String hql = "from Product_Type where ptname like '%"+typeName+"%'";
		Query query = getSession().createQuery(hql);
		query.setMaxResults(limit);
		query.setFirstResult(begin);
		List<Product_Type> list=query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/

		return list;
	}
	//增加分类
	public int addProductType(Product_Type type){
		int count=(Integer) getSession().save(type);
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/

		return count;
	}
	//修改分类
	public void updateProductType(Product_Type type){
		trans = getSession().beginTransaction();
		getSession().saveOrUpdate(type);
		trans.commit();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/

		
	}
	//删除分类
	public void deleteProductType(Product_Type type){
		trans = getSession().beginTransaction();
		getSession().delete(type);
		trans.commit();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/

	}
	//查询所有分类的总记录数
	public int findCountAll()
	{
		String hql = "select count(*) from Product_Type";
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
	//根据模糊查询 查询出类型的总记录数
	public int findCountPname(String typeName)
	{
		List<Long> list =getSession().createQuery("select count(*) from Product_Type where ptname like '%"+typeName+"%'").list();  
		/*if(session!=null && getSession().isOpen()){
			
			getSession().close();
		}*/
		
		if(list != null && list.size() > 0)
		{
			return list.get(0).intValue();
		}
		return 0;
	}
}
