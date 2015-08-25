package com.twinstorm.zkskeleton.service.user;

import com.twinstorm.zkskeleton.persistence.common.CrudDao;
import com.twinstorm.zkskeleton.persistence.user.UserRoleDaoImpl;
import com.twinstorm.zkskeleton.domain.user.UserRole;
import com.twinstorm.zkskeleton.service.base.BaseCrudService;
import com.twinstorm.zkskeleton.service.shared.user.UserRoleService;

public class UserRoleServiceImpl extends BaseCrudService<UserRole> implements UserRoleService {

  UserRoleDaoImpl userRoleDao;
  
  public void setUserRoleDao(UserRoleDaoImpl userRoleDao) {
    this.userRoleDao = userRoleDao;
  }

  protected CrudDao<UserRole> getDao() {
    return userRoleDao;
  }

}
