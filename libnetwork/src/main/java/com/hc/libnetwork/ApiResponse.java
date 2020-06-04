package com.hc.libnetwork;

/**
 * Created by hcw  on 2020/6/4
 * 类描述： 封装网络请求返回结构
 * all rights reserved
 */
public class ApiResponse<T> {
    public boolean success;
    public int status;
    public String message;
    public T body;
}
