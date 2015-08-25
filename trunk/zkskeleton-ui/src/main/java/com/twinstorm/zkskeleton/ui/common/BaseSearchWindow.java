package com.twinstorm.zkskeleton.ui.common;

import com.twinstorm.zkskeleton.service.shared.base.SearchCriteriaDTO;
import com.twinstorm.zkskeleton.service.shared.base.SearchService;
import com.twinstorm.zkskeleton.domain.common.BaseEntity;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

import java.io.IOException;
import java.util.List;

public abstract class BaseSearchWindow<I extends SearchService, T extends SearchCriteriaDTO, E extends BaseEntity> extends Window {
  public static final String SELECTED_ITEM = "SELECTED_ITEM";

  private I service;
  private T searchCriteria;
  private E selectedEntity;
  private List<E> searchResults;

  public BaseSearchWindow() {
    setSearchCriteria(getInitialCriteria());
  }

  protected abstract T getInitialCriteria();

  public I getService() {
    if (service == null) {
      service = (I) SpringUtil.getBean(getServiceBean());
    }

    return service;
  }

  public T getSearchCriteria() {
    return searchCriteria;
  }

  public void setSearchCriteria(T searchCriteria) {
    this.searchCriteria = searchCriteria;
  }

  public List<E> getSearchResults() {
    return searchResults;
  }

  public void setSearchResults(List<E> searchResults) {
    this.searchResults = searchResults;
  }

  public E getSelectedEntity() {
    return selectedEntity;
  }

  public void setSelectedEntity(E selectedEntity) {
    this.selectedEntity = selectedEntity;
  }

  public void view() throws IOException {
    Include inc = (Include) Path.getComponent("//toc/root/contents/xcontents");
    inc.setSrc(getPropertiesPage());

    Desktop desktop = getDesktop();
    if (desktop != null) {
      desktop.setAttribute(SELECTED_ITEM, getService().findById(getSelectedEntity().getId()));
    }
  }

  public void search() throws InterruptedException {
    setSearchResults(getService().search(searchCriteria));
  }

  protected abstract String getServiceBean();
  protected abstract String getPropertiesPage();
}
