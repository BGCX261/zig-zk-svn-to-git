package com.twinstorm.zkskeleton.domain;

import com.twinstorm.zkskeleton.domain.user.Authority;
import com.twinstorm.zkskeleton.domain.user.CustomUser;
import com.twinstorm.zkskeleton.domain.user.UserRole;

import java.util.*;

public class DomainTestHelper {

  public static CustomUser createUser() {
    CustomUser user = new CustomUser();
    user.setCreatedDate(new Date());
    user.setEnabled(true);
    user.setFirstName("piet");
    user.setSurname("pompies");
    user.setUsername("piet");
    user.setPassword("password");
    return user;
  }

  public static UserRole createUserRole() {
    UserRole role = new UserRole();
    role.setAuthorities(new HashSet<Authority>(Arrays.asList(Authority.values())));
    role.setName("allAuthorities");
    return role;
  }

}
