package com.twinstorm.zkskeleton.ui.user;

import com.twinstorm.zkskeleton.domain.user.CustomUser;
import com.twinstorm.zkskeleton.service.shared.user.UserService;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Window;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Messagebox;

import java.util.List;

public class UserManageWindow extends Window {

  UserService clientService;

  CustomUser selected = new CustomUser();

  public void save() throws InterruptedException {
    Textbox component = getPasswordConfirm();
    if (component.getValue() == null || ! component.getValue().equals(getSelected().getPassword())) {
      Messagebox.show("The passwords provided do not match.");
      return;
    }

    getClientService().save(getSelected());
  }

  private Textbox getPasswordConfirm() {
    Textbox component = (Textbox) getFellow("password_confirm");
    return component;
  }

  public void clear() {
    selected = new CustomUser();

    AnnotateDataBinder binder = (AnnotateDataBinder) getVariable("binder", false);
    binder.loadAll();
    getPasswordConfirm().setValue("");
  }

  public List getClientList() {
    return getClientService().getAll();
  }

  public CustomUser getSelected() {
    return selected;
  }

  public void setSelected(CustomUser selected) {
    this.selected = selected;
  }

  public UserService getClientService() {
    return (UserService) SpringUtil.getBean(UserService.BEAN_NAME);
  }
}