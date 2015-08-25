package com.twinstorm.zkskeleton.service.shared.user;

import com.twinstorm.zkskeleton.service.shared.base.SearchService;
import com.twinstorm.zkskeleton.domain.user.CustomUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserService extends SearchService<CustomUser, UserSearchCriteriaDTO> {
  String BEAN_NAME = "userService";

  public CustomUser findByUserName(String username);
  public void save(CustomUser user);
  public List<CustomUser> search(CustomUser example);
  public void delete(CustomUser user);
  
}
