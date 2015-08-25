package com.twinstorm.zkskeleton.service.shared.user;

import com.twinstorm.zkskeleton.service.shared.base.SearchCriteriaDTO;

public class UserSearchCriteriaDTO implements SearchCriteriaDTO {

  private String username;
  private String firstName;
  private String surname;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
