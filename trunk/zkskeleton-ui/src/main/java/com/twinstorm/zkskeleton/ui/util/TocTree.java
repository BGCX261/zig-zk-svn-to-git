package com.twinstorm.zkskeleton.ui.util;

import org.zkoss.zk.ui.Path;
import org.zkoss.zul.Include;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;

public class TocTree extends Tree {
  
  public void onSelect() {
    Treeitem item = getSelectedItem();
    
    if (item != null) {
      Include inc = (Include) Path.getComponent("/root/contents/xcontents");
      inc.setSrc((String)item.getValue());
    }
  }

}
