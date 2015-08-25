package com.twinstorm.zkskeleton.service.shared.base;

import com.twinstorm.zkskeleton.domain.common.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CrudService<T extends BaseEntity> {

  T findById(long id);
  void delete(T entity);
  void save(T entity);
  int count();
  List<T> getAll();
  
}
