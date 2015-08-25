package com.twinstorm.zkskeleton.ui.util;

import org.zkoss.zkplus.databind.SelectedComboitemConverter;
import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Comboitem;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class ComboboxWorkaroundTypeConvertor extends SelectedComboitemConverter {

  public Object coerceToUi(Object val, Component comp) {
    final Combobox cbbox = (Combobox) comp;
      if (val != null) {
        final ListModel xmodel = cbbox.getModel();
        if (xmodel instanceof BindingListModel) {
          final BindingListModel model = (BindingListModel) xmodel;
          int index = model.indexOf(val);
          if (index >= 0 && cbbox.getItems().size() > index) {
            final Comboitem item = (Comboitem) cbbox.getItemAtIndex(index);
            final int selIndex = cbbox.getSelectedIndex();

            //We need this to support load-when:onSelect when first load
          //the page in (so it is called only once).
            if (item != null && selIndex != index) { // bug 1647817, avoid endless-loop
              Set items = new HashSet();
              items.add(item);
              Events.postEvent(new SelectEvent("onSelect", cbbox, items, item));
            }
            return item;
          }
        } else if (xmodel == null) { //no model case, assume Comboitem.value to be used with selectedItem
          //iterate to find the selected item assume the value (select mold)
          for (final Iterator it = cbbox.getItems().iterator(); it.hasNext();) {
            final Comboitem li = (Comboitem) it.next();
            if (val.equals(li.getValue())) {
              return li;
            }
          }
        } else {
          throw new UiException("model of the databind combobox "+cbbox+" must be an instanceof of org.zkoss.zkplus.databind.BindingListModel." + xmodel);
        }
      }
      return null;
  }
}
