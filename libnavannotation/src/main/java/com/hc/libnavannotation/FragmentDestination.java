package com.hc.libnavannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by hcw  on 2020/5/30
 * 类描述：Fragment 使用
 * all rights reserved
 */

@Target(ElementType.TYPE)
public @interface FragmentDestination {

    String pageUrl(); //页面标志

    boolean needLogin() default  false; //表示是否登陆。


    boolean asStarter() default  false; //是否是开始页面



}
