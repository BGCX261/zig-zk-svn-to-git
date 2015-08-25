package com.twinstorm.zkskeleton.persistence.user;

import com.twinstorm.zkskeleton.persistence.BaseDomainTestCase;
import com.twinstorm.zkskeleton.domain.DomainTestHelper;
import com.twinstorm.zkskeleton.domain.user.CustomUser;

public class UserTestCase extends BaseDomainTestCase {

  UserDaoImpl dao;
  
  public void testPersist() {
    CustomUser user = DomainTestHelper.createUser();
    dao.save(user);
  }

  public void setDao(UserDaoImpl dao) {
    this.dao = dao;
  }
  
  
  
}
