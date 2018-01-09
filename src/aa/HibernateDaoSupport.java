package aa;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.springframework.beans.factory.annotation.Autowired;  
  
/** 
 * <p> 
 * 重新实现HibernateDaoSupport 
 * </p> 
 * @author <a href="mailto:williamsun1993@gmail.com">William Suen</a>  
 */  
public class HibernateDaoSupport {  
      
    private HibernateTemplate hibernateTemplate;  
  
    public HibernateTemplate getHibernateTemplate() {  
        return hibernateTemplate;  
    }  
      
    @Autowired  
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {  
        this.hibernateTemplate = hibernateTemplate;  
    }  
      
    public SessionFactory getSessionFactory() {  
        return hibernateTemplate.getSessionFactory();  
    }  
      
    public Session getSession() {  
        return getSessionFactory().getCurrentSession();  
    }  
}  
