package com.twinstorm.zkskeleton.service.user;

import com.twinstorm.zkskeleton.domain.user.CustomUser;
import com.twinstorm.zkskeleton.persistence.common.CrudDao;
import com.twinstorm.zkskeleton.persistence.user.UserDaoImpl;
import com.twinstorm.zkskeleton.service.base.BaseCrudService;
import com.twinstorm.zkskeleton.service.shared.user.UserSearchCriteriaDTO;
import com.twinstorm.zkskeleton.service.shared.user.UserService;

import java.util.List;

public class UserServiceImpl extends BaseCrudService<CustomUser> implements UserService {

  UserDaoImpl userDao;
  
  public CustomUser findByUserName(String username) {
    return userDao.findByUsername(username);
  }

  public void setUserDao(UserDaoImpl userDao) {
    this.userDao = userDao;
  }

  public List<CustomUser> search(CustomUser example) {
    return userDao.search(example);
  }

  protected CrudDao<CustomUser> getDao() {
    return userDao;
  }

  public List<CustomUser> search(UserSearchCriteriaDTO criteriaDTO) {
    return userDao.search(criteriaDTO.getUsername(), criteriaDTO.getFirstName(), criteriaDTO.getSurname());
  }

}
