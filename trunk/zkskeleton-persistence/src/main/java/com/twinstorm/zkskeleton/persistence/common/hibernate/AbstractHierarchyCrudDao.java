package com.twinstorm.zkskeleton.persistence.common.hibernate;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.twinstorm.zkskeleton.domain.common.EntityHierarchy;
import com.twinstorm.zkskeleton.persistence.common.HierarchyCrudDao;


public abstract class AbstractHierarchyCrudDao<I extends EntityHierarchy> extends AbstractCrudDao<I> implements HierarchyCrudDao<I> {
  
  public List<I> findByParent(I parent) {
    DetachedCriteria criteria = createCriteria();
    
    if (parent == null) {
      criteria.add(Restrictions.isNull("parent"));
    } else {
      criteria.add(Restrictions.eq("parent", parent));
    }
    
    return getHibernateTemplate().findByCriteria(criteria);
  }
  
}
