package com.twinstorm.zkskeleton.domain.user;

import org.acegisecurity.userdetails.User;
import org.acegisecurity.GrantedAuthority;

/**
 * Wrapper that adds our PropertyUser to the Acegi user object
 */
public class UserWrapper extends User {

  private CustomUser customUser;

  public UserWrapper(String username, String password, boolean isEnabled, GrantedAuthority[] authorities, CustomUser user) {
    super(username, password, isEnabled, true, true, true, authorities);
    this.setCustomUser(user);
  }

  public UserWrapper(String username, String password, boolean isEnabled, GrantedAuthority[] arrayAuths) {
    super(username, password, isEnabled, true, true, true, arrayAuths);
  }

  public CustomUser getCustomUser() {
    return customUser;
  }

  public void setCustomUser(CustomUser customUser) {
    this.customUser = customUser;
  }


}