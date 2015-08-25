package com.twinstorm.zkskeleton.ui.user;

import com.twinstorm.zkskeleton.domain.user.Authority;
import com.twinstorm.zkskeleton.domain.user.UserRole;
import com.twinstorm.zkskeleton.service.shared.user.UserRoleService;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UserRoleManageWindow extends Window {

  UserRoleService clientService;

  UserRole selected = new UserRole();

  public void save() {
    Listbox authorities = getAuthoritiesComponent();
    if (selected.getAuthorities() == null) {
      selected.setAuthorities(new HashSet<Authority>());
    }
    
    Set<Listitem> selectedItems = authorities.getSelectedItems();
    for (Listitem selectedItem : selectedItems) {
      selected.getAuthorities().add((Authority) selectedItem.getValue());
    }

    getService().save(getSelected());
  }

  private Listbox getAuthoritiesComponent() {
    return (Listbox) getFellow("authorities");
  }

  public void clear() {
    selected = new UserRole();

    AnnotateDataBinder binder = (AnnotateDataBinder) getVariable("binder", false);
    binder.loadAll();
  }

  public Set<Authority> getAllAuthorities() {
    return new HashSet<Authority>(Arrays.asList(Authority.values()));
  }

  public UserRole getSelected() {
    return selected;
  }

  public void setSelected(UserRole selectedRole) {
    this.selected = getService().findById(selectedRole.getId());

    if (selected.getAuthorities() == null) {
      selected.setAuthorities(new HashSet<Authority>());
    }

    Listbox listbox = getAuthoritiesComponent();
    for (int i=0; i < listbox.getItems().size(); i++) {
      Listitem listitem = listbox.getItemAtIndex(i);
      listitem.setSelected(selected.getAuthorities().contains(listitem.getValue()));
    }
  }

  public UserRoleService getService() {
    return (UserRoleService) SpringUtil.getBean(UserRoleService.BEAN_NAME);
  }


}