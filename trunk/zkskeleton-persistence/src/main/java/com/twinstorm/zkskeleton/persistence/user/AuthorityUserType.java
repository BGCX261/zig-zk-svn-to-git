package com.twinstorm.zkskeleton.persistence.user;

import com.twinstorm.zkskeleton.persistence.common.hibernate.EnumUserType;
import com.twinstorm.zkskeleton.domain.user.Authority;

public class AuthorityUserType extends EnumUserType<Authority>{
  public AuthorityUserType() {
    super(Authority.class);
  }
}
