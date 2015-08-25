package com.twinstorm.zkskeleton.service.shared.base;

import com.twinstorm.zkskeleton.domain.common.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SearchService<T extends BaseEntity, I extends SearchCriteriaDTO> extends CrudService<T> {

  public List<T> search(I criteriaDTO);

}