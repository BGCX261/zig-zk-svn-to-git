package com.twinstorm.zkskeleton.service.base;

import com.twinstorm.zkskeleton.service.shared.base.CrudService;
import com.twinstorm.zkskeleton.domain.common.BaseEntity;
import com.twinstorm.zkskeleton.persistence.common.CrudDao;

import java.util.List;

public abstract class BaseCrudService<T extends BaseEntity> implements CrudService<T> {

  protected abstract CrudDao<T> getDao();
  
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
