package com.twinstorm.zkskeleton.persistence.common;

import com.twinstorm.zkskeleton.domain.common.EntityHierarchy;

import java.util.List;

public interface HierarchyCrudDao<I extends EntityHierarchy> extends CrudDao<I> {

   List<I> findByParent(I parent);

}