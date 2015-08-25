package com.twinstorm.zkskeleton.persistence.common.hibernate;

import com.twinstorm.zkskeleton.persistence.common.CrudDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public abstract class AbstractCrudDao<I> extends HibernateDaoSupport implements CrudDao<I> {
  
  private static final Log log = LogFactory.getLog(AbstractCrudDao.class);
  private Class<I> persistentClass;

  protected Class<I> getPersistentClass() {
    if (persistentClass == null) {
      this.persistentClass = (Class<I>) ((ParameterizedType) getClass()
          .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    return persistentClass;
  }
  /* (non-Javadoc)
   * @see com.twinstorm.expoedge.model.common.GenericDao#findByExample(I)
   */
  @SuppressWarnings("unchecked")
  public List<I> findByExample(I instance) {
    log.debug("finding instance by example");
    try {
        List<I> results = getSession()
                .createCriteria(instance.getClass().getName())
                .add(Example.create(instance))
                .list();
        log.debug("find by example successful, result size: " + results.size());
        return results;
    } catch (RuntimeException re) {
        log.error("find by example failed", re);
        throw re;
    }
  }    
  
  /* (non-Javadoc)
   * @see com.twinstorm.expoedge.model.common.GenericDao#findById(java.io.Serializable)
   */
  public I findById(Serializable id) {
    log.debug("getting LeadingQuestion instance with id: " + id);
    try {
      I instance = (I) getHibernateTemplate().get(getPersistentClass(), id);
        return instance;
    } catch (RuntimeException re) {
        log.error("get failed", re);
        throw re;
    }
}

  /* (non-Javadoc)
   * @see com.twinstorm.expoedge.model.common.GenericDao#findAll()
   */
  @SuppressWarnings("unchecked")
  public List<I> findAll() {
    return getHibernateTemplate().find("from " + getPersistentClass().getName());
  }
  
  /* (non-Javadoc)
   * @see com.twinstorm.expoedge.model.common.GenericDao#save(I)
   */
  public I save(I transientInstance) {
    getHibernateTemplate().saveOrUpdate(transientInstance);
    return transientInstance;
  }
  
  /* (non-Javadoc)
   * @see com.twinstorm.expoedge.model.common.GenericDao#count()
   */
  public int count() {
    return ((Number) (getHibernateTemplate()
      .find("select count(*) from " + getPersistentClass().getName() + " as clazz"))
      .get(0)).intValue();
  }
  
  public void flush() {
    getHibernateTemplate().flush();
  }
  
  protected DetachedCriteria createCriteria() {
    return DetachedCriteria.forClass(getPersistentClass());
  }
  public void delete(I instance) {
    getHibernateTemplate().delete(instance);
  }

}
