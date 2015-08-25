package com.twinstorm.zkskeleton.domain.user;

import com.twinstorm.zkskeleton.domain.common.BaseEntity;
import org.acegisecurity.context.SecurityContextHolder;

import java.util.Date;
import java.util.Set;

public class CustomUser extends BaseEntity {
  
  String username;
  String password;
  String firstName;
  String surname;
  boolean enabled = true;
  Date createdDate;
  Date lastLogin;
  
  private Set<UserRole> roles;
  
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public Set<UserRole> getRoles() {
    return roles;
  }
  public void setRoles(Set<UserRole> roles) {
    this.roles = roles;
  }
  
  
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  
  
  public boolean isEnabled() {
    return enabled;
  }
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
  public static CustomUser currentUser() {
    UserWrapper principal = (UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return principal.getCustomUser();
  }
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
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public Date getLastLogin() {
    return lastLogin;
  }
  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
  }

  
}
