package com.dascom.product.util;
import java.io.Serializable;  
import java.sql.SQLException;  
import java.util.Collection;  
import java.util.List;  
  
  
import org.hibernate.Criteria;  
import org.hibernate.HibernateException;  
import org.hibernate.LockMode;  
import org.hibernate.Query;  
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.criterion.DetachedCriteria;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.dao.DataAccessException;  
import org.springframework.orm.hibernate3.HibernateCallback;  
import org.springframework.transaction.support.TransactionSynchronizationManager;  
  
  
  
/** 
 * Helper class that simplifies Hibernate data access code. Automatically 
 * converts HibernateExceptions into DataAccessExceptions, following the 
 * <code>org.springframework.dao</code> exception hierarchy. 
 * 
 * <p>The central method is <code>execute</code>, supporting Hibernate access code 
 * implementing the {@link HibernateCallback} interface. It provides Hibernate Session 
 * handling such that neither the HibernateCallback implementation nor the calling 
 * code needs to explicitly care about retrieving/closing Hibernate Sessions, 
 * or handling Session lifecycle exceptions. For typical single step actions, 
 * there are various convenience methods (find, load, saveOrUpdate, delete). 
 *  
 * @author <a href="mailto:<span style="font-family: Arial, Helvetica, sans-serif;">williamsun1993@gmail.com</span><span style="font-family: Arial, Helvetica, sans-serif;">">William Suen</a> </span> 
 * 
 */  
public class HibernateTemplate {  
      
    private SessionFactory sessionFactory;  
      
    public SessionFactory getSessionFactory() {  
        return sessionFactory;  
    }  
  
  
    @Autowired  
    public void setSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory;  
    }  
  
  
    private Session getCurrentSession() {  
        Object value = TransactionSynchronizationManager.getResource(getSessionFactory());  
        if (null == value) {  
            return sessionFactory.openSession();  
        } else {  
            return this.sessionFactory.getCurrentSession();              
        }  
    }  
      
    public <T> T execute(HibernateCallback<T> action) throws DataAccessException {  
        return doExecute(action, false, false);  
    }  
      
    public List executeFind(HibernateCallback<?> action) throws DataAccessException {  
        Object result = doExecute(action, false, false);  
       
        return (List) result;  
    }  
      
    /** 
     * Execute the action specified by the given action object within a Session. 
     * @param action callback object that specifies the Hibernate action 
     * @param enforceNewSession whether to enforce a new Session for this template 
     * even if there is a pre-bound transactional Session 
     * @param enforceNativeSession whether to enforce exposure of the native 
     * Hibernate Session to callback code 
     * @return a result object returned by the action, or <code>null</code> 
     * @throws org.springframework.dao.DataAccessException in case of Hibernate errors 
     */  
    protected <T> T doExecute(HibernateCallback<T> action, boolean enforceNewSession, boolean enforceNativeSession)  
            throws DataAccessException {  
       
        T result = null;  
        try {  
            result = action.doInHibernate(getCurrentSession());  
        } catch (HibernateException e) {  
            throw new IllegalArgumentException(e);  
        } catch (SQLException e) {  
            throw new IllegalArgumentException(e);  
        }  
        return result;  
    }  
      
      
    //-------------------------------------------------------------------------  
    // Convenience methods for loading individual objects  
    //-------------------------------------------------------------------------  
      
    public <T> T get(Class<T> entityClass, Serializable id) throws DataAccessException {  
        return get(entityClass, id, null);  
    }  
      
    public <T> T get(final Class<T> entityClass, final Serializable id, final LockMode lockMode)  
            throws DataAccessException {  
        if (lockMode != null) {  
            return (T) getCurrentSession().get(entityClass, id, lockMode);  
        } else {  
            return (T) getCurrentSession().get(entityClass, id);  
        }  
    }  
      
    public <T> T load(Class<T> entityClass, Serializable id) throws DataAccessException {  
        return load(entityClass, id, null);  
    }  
  
  
    public <T> T load(final Class<T> entityClass, final Serializable id, final LockMode lockMode)  
            throws DataAccessException {  
        if (lockMode != null) {  
            return (T) getCurrentSession().load(entityClass, id, lockMode);  
        } else {  
            return (T) getCurrentSession().load(entityClass, id);  
        }  
    }  
      
    //-------------------------------------------------------------------------  
    // Convenience methods for storing individual objects  
    //-------------------------------------------------------------------------  
      
    public Serializable save(final Object entity) throws DataAccessException {  
        return getCurrentSession().save(entity);  
    }  
      
    public void saveOrUpdate(final Object entity) throws DataAccessException {  
        getCurrentSession().saveOrUpdate(entity);  
    }  
      
    public void saveOrUpdateAll(final Collection entities) throws DataAccessException {  
        for (Object entity : entities) {  
            getCurrentSession().saveOrUpdate(entity);  
        }  
    }  
      
    public <T> T merge(final T entity) throws DataAccessException {  
        return (T) getCurrentSession().merge(entity);  
    }  
      
    public void delete(final Object entity) throws DataAccessException {  
        getCurrentSession().delete(entity);  
    }  
      
    public void deleteAll(final Collection entities) throws DataAccessException {  
        for (Object entity : entities) {  
            getCurrentSession().delete(entity);  
        }  
    }  
      
    //-------------------------------------------------------------------------  
    // Convenience finder methods for HQL strings  
    //-------------------------------------------------------------------------  
      
    public List find(String queryString) throws DataAccessException {  
        return find(queryString, (Object[]) null);  
    }  
      
    public List find(String queryString, Object value) throws DataAccessException {  
        return find(queryString, new Object[] {value});  
    }  
      
    public List find(final String queryString, final Object... values) throws DataAccessException {  
        Query queryObject = getCurrentSession().createQuery(queryString);  
        if (values != null) {  
            for (int i = 0; i < values.length; i++) {  
                queryObject.setParameter(i, values[i]);  
            }  
        }  
        return queryObject.list();  
    }  
      
    public List findByNamedParam(  
            final String queryString,   
            final String paramName,   
            final Object value) throws DataAccessException {  
        return findByNamedParam(queryString, new String[] {paramName}, new Object[] {value});  
    }  
      
    public List findByNamedParam(  
            final String queryString,   
            final String[] paramNames,   
            final Object[] values) throws DataAccessException {  
          
       
        Query query = getCurrentSession().createQuery(queryString);  
        if (values != null) {  
            for (int i = 0, size = values.length; i < size; i++) {  
                applyNamedParameterToQuery(query, paramNames[i], values[i]);  
            }  
        }  
        return query.list();  
    }  
      
    //-------------------------------------------------------------------------  
    // Convenience finder methods for detached criteria  
    //-------------------------------------------------------------------------  
      
    public List findByCriteria(DetachedCriteria criteria) throws DataAccessException {  
        return findByCriteria(criteria, -1, -1);  
    }  
      
    public List findByCriteria(final DetachedCriteria criteria, final int firstResult, final int maxResults) {  
        Criteria executableCriteria = criteria.getExecutableCriteria(getCurrentSession());  
        if (firstResult >= 0) {  
            executableCriteria.setFirstResult(firstResult);  
        }  
        if (maxResults > 0) {  
            executableCriteria.setMaxResults(maxResults);  
        }  
        return executableCriteria.list();  
    }  
      
    /** 
     * Apply the given name parameter to the given Query object. 
     * @param queryObject the Query object 
     * @param paramName the name of the parameter 
     * @param value the value of the parameter 
     * @throws HibernateException if thrown by the Query object 
     */  
    protected void applyNamedParameterToQuery(Query queryObject, String paramName, Object value)   
            throws HibernateException {  
      
        if (value instanceof Collection) {  
            queryObject.setParameterList(paramName, (Collection) value);  
        } else if (value instanceof Object[]) {  
            queryObject.setParameterList(paramName, (Object[]) value);  
        } else {  
            queryObject.setParameter(paramName, value);  
        }  
    }  
}  