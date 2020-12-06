package com.util;

import com.entity.Return;
import org.springframework.http.HttpStatus;

public class RetornoUtil {

  public static Return result(HttpStatus status) {
    Return ret = new Return();
    ret.setId(status.value());
    ret.setAReturn(status.toString());

    return ret;
  }
}
