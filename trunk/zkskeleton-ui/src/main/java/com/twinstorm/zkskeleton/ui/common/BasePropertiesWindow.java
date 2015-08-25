package com.twinstorm.zkskeleton.ui.common;

import com.twinstorm.zkskeleton.service.shared.base.SearchService;
import com.twinstorm.zkskeleton.domain.common.BaseEntity;
import com.twinstorm.zkskeleton.ui.common.BaseSearchWindow;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Window;

public abstract class BasePropertiesWindow<I extends SearchService, T extends BaseEntity> extends Window {
  private I service;
  private T selected;
  private boolean add;

  protected BasePropertiesWindow() {
    clear();
    add = true;
  }

  public boolean isAdd() {
    return add;
  }

  public I getService() {
    if (service == null) {
      service = (I) SpringUtil.getBean(getServiceBean());
    }

    return service;
  }

  public T getSelected() {
    if (getDesktop() != null && getDesktop().getAttribute(BaseSearchWindow.SELECTED_ITEM) != null) {
      this.selected = (T) getDesktop().getAttribute(BaseSearchWindow.SELECTED_ITEM);
      getDesktop().removeAttribute(BaseSearchWindow.SELECTED_ITEM);
      onSetSelected();
    }

    return selected;
  }

  public void setSelected(T selected) {
    this.selected = selected;
  }

  public void save() {
    if (validate()) {
      getService().save(getSelected());
    }
  }

  protected void onSetSelected() {
    this.add = false;
  }

  public void clear() {
    this.add = true;
  };

  protected abstract boolean validate();

  protected abstract String getServiceBean();
}
