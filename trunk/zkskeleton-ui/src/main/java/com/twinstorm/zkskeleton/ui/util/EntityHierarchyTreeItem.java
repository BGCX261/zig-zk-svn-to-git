package com.twinstorm.zkskeleton.ui.util;

import com.twinstorm.zkskeleton.service.shared.base.HierarchyCrudService;
import com.twinstorm.zkskeleton.domain.common.EntityHierarchy;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;

import java.util.Collection;
import java.util.List;

public abstract class EntityHierarchyTreeItem<T extends EntityHierarchy> extends Treeitem {

  public EntityHierarchyTreeItem () {
    super();
    addEventListener("onOpen", new EventListener() {
      public void onEvent(Event event) throws Exception {
        expandNode();
      }
    });
  }

  public EntityHierarchyTreeItem (T entity) {
    this();
    setValue(entity);
    setLabel(entity.getName());

    if (entity.getChildren() != null && !entity.getChildren().isEmpty()) {
      appendChild(new Treechildren());
      setOpen(false);
    }

    Treecell newCell = new Treecell("some more info");
    newCell.setParent(getTreerow());
  }

  public void expandNode() {
    Long categoryId = ((T) getValue()).getId();
    Treechildren children = getTreechildren();

    if (children.getItemCount() <= 0) {
      T nodeEntity = getService().findById(categoryId);

      List<T> children2 = nodeEntity.getChildren();
      if (nodeEntity != null && children2 != null) {
        for (T child : children2) {
          children.appendChild(createItem(child));
        }
      }
    }
  }

  public void expandToNode(T targetNode) {
    if (targetNode != null) {
      expandNode();
      for (EntityHierarchyTreeItem childItem : (Collection<EntityHierarchyTreeItem>) getTreechildren().getItems()) {
        T node = (T) childItem.getValue();
        if (node.isAncestor(targetNode)) {
          childItem.expandToNode(targetNode);
        }
      }
    }
  }

  protected abstract HierarchyCrudService<T> getService();
  protected abstract EntityHierarchyTreeItem<T> createItem(T entity);

}
