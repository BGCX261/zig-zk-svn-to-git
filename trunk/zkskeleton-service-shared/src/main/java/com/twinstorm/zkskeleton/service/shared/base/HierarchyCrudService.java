package com.twinstorm.zkskeleton.service.shared.base;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.twinstorm.zkskeleton.domain.common.EntityHierarchy;

@Transactional
public interface HierarchyCrudService<T extends EntityHierarchy> extends CrudService<T> {

  public List<T> getChildren(T parent);
  public List<T> getTopLevelItems();
  
}
