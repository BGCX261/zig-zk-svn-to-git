package com.twinstorm.zkskeleton.ui.user;

import com.twinstorm.zkskeleton.ui.common.BasePropertiesWindow;
import com.twinstorm.zkskeleton.domain.user.CustomUser;
import com.twinstorm.zkskeleton.domain.user.UserRole;
import com.twinstorm.zkskeleton.service.shared.user.UserRoleService;
import com.twinstorm.zkskeleton.service.shared.user.UserService;
import org.apache.commons.lang.StringUtils;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.*;
import org.acegisecurity.providers.encoding.Md5PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserPropertiesWindow extends BasePropertiesWindow<UserService, CustomUser> implements AfterCompose {

  public void clear() {
    super.clear();
    setSelected(new CustomUser());
  }

  public void save() {
    getSelected().setRoles(new HashSet<UserRole>());

    Set<Listitem> listbox = (Set<Listitem>) getRolesComponent().getSelectedItems();
    for (Listitem listitem : listbox) {
      getSelected().getRoles().add((UserRole) listitem.getValue());
    }

    super.save();
  }

  private boolean isPasswordsMatch() {
    String password = getPassword().getValue();
    String confirm = getPasswordConfirm().getValue();

    boolean passwordBlank = StringUtils.isBlank(password);
    
    boolean valid;
    if (isAdd()) {
      valid = ( ! passwordBlank && password.equals(confirm));
    } else {
      valid = passwordBlank || password.equals(confirm);
    }

    if (valid) {
      getSelected().setPassword(new Md5PasswordEncoder().encodePassword(password, null));
    }

    return valid;
  }

  protected boolean validate() {
    boolean valid = true;

    if ( ! isPasswordsMatch()) {
      try {
        Messagebox.show("The passwords provided do not match.");
      } catch (InterruptedException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
      return false;
    }

    return valid;
  }

  private Listbox getRolesComponent() {
    return (Listbox) getFellow("roles");
  }

  private Textbox getPasswordConfirm() {
    return (Textbox) getFellow("password_confirm");
  }

  private Textbox getPassword() {
    return (Textbox) getFellow("password");
  }

  protected String getServiceBean() {
    return UserService.BEAN_NAME;
  }


  public void afterCompose() {
    UserRoleService userRoleService = (UserRoleService) SpringUtil.getBean(UserRoleService.BEAN_NAME);
    List<UserRole> roleList = userRoleService.getAll();

    Listbox listbox = new Listbox();
    listbox.setId("roles");
    listbox.setCheckmark(true);
    listbox.setMultiple(true);
    listbox.setRows(8);

    for (UserRole role : roleList) {
      Listitem item = new Listitem(role.getName(), role);
      listbox.appendChild(item);

      if (getSelected().getRoles() != null && getSelected().getRoles().contains(role)) {
        item.setSelected(true);
      }
      listbox.appendChild(item);
    }

    Grid grid = (Grid) getFellow("gb");
    Row row = new Row();
    row.setValign("top");
    grid.getRows().appendChild(row);
    row.appendChild(new Label("Roles"));
    row.appendChild(listbox);
  }
}