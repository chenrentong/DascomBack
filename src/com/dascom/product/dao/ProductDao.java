package com.dascom.product.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.dascom.product.entity.Product;
import com.dascom.product.entity.Product_Type;

public class ProductDao 
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
	
	
    //根据模糊查询查询出该产品的总数
	public int findCountPname(String pname)
	{
		String hql = "SELECT COUNT(*) FROM Product WHERE pname LIKE ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, "%"+pname+"%");
		List<Long> list=query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		if(list != null && list.size() > 0)
		{
			return list.get(0).intValue();
		}
		return 0;
	}
	
	//根据产品类型id查询查询出该产品的总数
	public int findCountPtid(Integer ptid)
	{
		String hql = "select count(*) from Product p where p.product_Type.ptid=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, ptid);
		List<Long> list=query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		if(list != null && list.size() > 0)
		{
			return list.get(0).intValue();
		}
		return 0;
	}
	//根据产品名称模糊查询产品信息(带分页)
	public List<Product> findByPagePname(String pname,int begin,int limit)
	{
		String hql = "FROM Product WHERE pname LIKE ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, "%"+pname+"%");
		query.setFirstResult(begin);
		query.setMaxResults(limit);
		List<Product> list = query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		if(list != null && list.size() > 0)
		{
			return list;
		}
		return null;
	}
	
	//根据产品类型id查询产品信息(带分页)
	public List<Product> findByPagePtid(Integer ptid, int begin, int limit)
	{
		String hql = "select p from Product p join p.product_Type pt where pt.ptid = ?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, ptid);
		query.setFirstResult(begin);
		query.setMaxResults(limit);
		List<Product> list = query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		if(list != null && list.size() > 0)
		{
			return list;
		}
		return null;
	}
	
	//根据产品id进行查询
	public Product findByPid(Integer pid)
	{
		Product p=(Product) this.getSession().get(Product.class, pid);
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return p;
	}
	
	//查询所有的产品（带分页）
	public List<Product> findAllProduct(int begin,int limit){
		String hql = "FROM Product ";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(begin);
		query.setMaxResults(limit);
		List<Product> list=query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return list;
		
	}
	
	//查询所有产品条数
	public int findAllProductCount(){
		String hql = "select count(*) from Product";
		Query query = getSession().createQuery(hql);
		List<Long> list=query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		if(list != null && list.size() > 0)
		{
			return list.get(0).intValue();
		}
		return 0;
		
	}
	
	//根据类型id和关键字查询产品信息
	public List<Product> findProductByKeyAndType(String keyword,Integer type,Integer begin,Integer limit){
		String hql = "from Product where pname  like '%"+keyword+"%' and ptid = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, type);
		query.setFirstResult(begin);
		query.setMaxResults(limit);
		List<Product> p=query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return p;
		
	}
	//根据分类和关键字查询产品条数
	public int findCountByKeyAndType(String keyword,Integer type){
		String hql = "select count(*) from Product where pname  like '%"+keyword+"%' and ptid = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, type);
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
	//增加
	public int addProduct(Product p){
		int count=(Integer) getSession().save(p);
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return count;
	}
	//修改
	public void updateProduct(Product p){
		trans = getSession().beginTransaction();
		getSession().saveOrUpdate(p);
		trans.commit();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		
	}
	//删除
	public void deleteProduct(Product p){
		trans = getSession().beginTransaction();
		getSession().delete(p);
		trans.commit();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
	}
	//根据分类id查询商品
	public  List<Product> findProductByTypeID(int typeId){
		String hql = "from Product where ptid=?  ";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, typeId);
		List<Product> p=query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return p;
	}
	//查询所有的产品
	public List<Product> findAllProdut(){
		String hql = "FROM Product ";
		Query query = getSession().createQuery(hql);
		List<Product> list=query.list();
		/*if(session!=null && getSession().isOpen()){
			getSession().close();
		}*/
		return list;
	}
}
