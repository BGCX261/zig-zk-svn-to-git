package com.twinstorm.zkskeleton.ui.util;

import org.apache.commons.lang.math.NumberUtils;
import org.zkoss.util.Locales;
import org.zkoss.zul.Label;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DecimalLabel extends Label {

  
  public void setValue(String value) {
    DecimalFormat df = (DecimalFormat) NumberFormat.getInstance(Locales.getCurrent());
    if (NumberUtils.isNumber(value)) {
      value = df.format(Double.valueOf(value));
    }

    super.setValue(value);
  }
}
