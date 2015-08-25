package com.twinstorm.zkskeleton.service.shared.user;

import com.twinstorm.zkskeleton.service.shared.base.CrudService;
import com.twinstorm.zkskeleton.domain.user.UserRole;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRoleService extends CrudService<UserRole> {
  String BEAN_NAME = "userRoleService";

}
