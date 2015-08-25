package com.twinstorm.zkskeleton.domain.user;

import com.twinstorm.zkskeleton.domain.common.NamedEntity;

import java.util.Set;

public class UserRole extends NamedEntity {

  private Set<Authority> authorities;

  public Set<Authority> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Set<Authority> authorities) {
    this.authorities = authorities;
  }
  
}
