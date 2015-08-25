package com.twinstorm.zkskeleton.domain.common;

import java.util.ArrayList;
import java.util.List;


public class EntityHierarchy<T extends EntityHierarchy> extends NamedEntity {
  
  private String path;
  private List<T> children;
  private T parent;

  public void addChild(T child) {
    if (children == null) {
      children = new ArrayList<T>();
    }
    
    child.setPath(getNextChildPath());
    children.add(child);
  }

  public boolean isAncestor(T node) {
    if (node.getPath().startsWith(getPath())) {
      return true;
    }
    return false;
  }
  
  private String getNextChildPath() {
    int maxChildCode = 0;
    for (T existingChild : children) {
      String[] childCodes = existingChild.getPath().split("-");
      int childCode = new Integer(childCodes[childCodes.length-1]);
      if (childCode  > maxChildCode) {
        maxChildCode = childCode;
      }
    }
    maxChildCode++;
    return getPath() + "-" + maxChildCode;
  }

  public T getParent() {
    return parent;
  }

  public void setParent(T parent) {
    this.parent = parent;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String code) {
    this.path = code;
  }
  
  public List<T> getChildren() {
    return children;
  }
  
  
  
}
