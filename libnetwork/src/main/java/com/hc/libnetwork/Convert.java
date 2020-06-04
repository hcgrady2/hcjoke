package com.hc.libnetwork;

import java.lang.reflect.Type;

/**
 * Created by hcw  on 2020/6/4
 * 类描述：解析
 * all rights reserved
 */
public interface Convert<T> {
    T convert(String response, Type type);

    T convert(String response, Class claz);
}
