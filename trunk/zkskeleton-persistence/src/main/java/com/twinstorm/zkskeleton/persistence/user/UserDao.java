package com.twinstorm.zkskeleton.persistence.user;

import com.twinstorm.zkskeleton.persistence.common.CrudDao;
import com.twinstorm.zkskeleton.domain.user.CustomUser;

import java.util.List;

public interface UserDao extends CrudDao<CustomUser> {
  CustomUser findByUsername(String name);

  List<CustomUser> search(CustomUser example);

  List<CustomUser> search(String username, String firstName, String surname);
}
