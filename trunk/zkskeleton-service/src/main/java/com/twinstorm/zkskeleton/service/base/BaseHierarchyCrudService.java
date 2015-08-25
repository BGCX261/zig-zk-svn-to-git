package com.twinstorm.zkskeleton.service.base;

import java.util.List;

import com.twinstorm.zkskeleton.service.shared.base.HierarchyCrudService;
import com.twinstorm.zkskeleton.domain.common.EntityHierarchy;
import com.twinstorm.zkskeleton.persistence.common.hibernate.AbstractHierarchyCrudDao;

public abstract class BaseHierarchyCrudService<T extends EntityHierarchy> implements HierarchyCrudService<T> {

  protected abstract AbstractHierarchyCrudDao<T> getDao();
  
  public List<T> getChildren(T parent) {
    return getDao().findByParent(parent);
  }

  public List<T> getTopLevelItems() {
    return getChildren(null);
  }

  public void delete(T entity) {
    getDao().delete(entity);
  }

  public T findById(long id) {
    return getDao().findById(id);
  }

  public void save(T entity) {
    getDao().save(entity);
  }

  public int count() {
    return getDao().count();
  }

  public List<T> getAll() {
    return getDao().findAll();
  } 
  
  
  
}
