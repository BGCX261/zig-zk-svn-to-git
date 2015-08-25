package com.twinstorm.zkskeleton.persistence.user;

import com.twinstorm.zkskeleton.domain.DomainTestHelper;
import com.twinstorm.zkskeleton.domain.user.UserRole;
import com.twinstorm.zkskeleton.persistence.BaseDomainTestCase;

public class UserRoleTestCase extends BaseDomainTestCase {

  UserRoleDaoImpl dao;
  
  public void testPersist() {
    UserRole userRole = DomainTestHelper.createUserRole();
    dao.save(userRole);
  }

  public void setDao(UserRoleDaoImpl dao) {
    this.dao = dao;
  }


  
  
}
