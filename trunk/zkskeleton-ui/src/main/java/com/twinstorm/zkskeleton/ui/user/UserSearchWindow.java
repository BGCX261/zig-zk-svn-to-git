package com.twinstorm.zkskeleton.ui.user;

import com.twinstorm.zkskeleton.ui.common.BaseSearchWindow;
import com.twinstorm.zkskeleton.domain.user.CustomUser;
import com.twinstorm.zkskeleton.service.shared.user.UserSearchCriteriaDTO;
import com.twinstorm.zkskeleton.service.shared.user.UserService;

public class UserSearchWindow extends BaseSearchWindow<UserService, UserSearchCriteriaDTO, CustomUser> {
  
  protected UserSearchCriteriaDTO getInitialCriteria() {
    return new UserSearchCriteriaDTO();
  }

  protected String getServiceBean() {
    return UserService.BEAN_NAME;
  }

  protected String getPropertiesPage() {
    return "/zul/user/user-properties.zul";
  }
}
