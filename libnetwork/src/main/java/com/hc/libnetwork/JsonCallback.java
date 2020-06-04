package com.hc.libnetwork;

/**
 * Created by hcw  on 2020/6/4
 * 类描述：
 * all rights reserved
 */
public abstract class JsonCallback<T> {
    public void onSuccess(ApiResponse<T> response) {

    }

    public void onError(ApiResponse<T> response) {

    }

    public void onCacheSuccess(ApiResponse<T> response) {

    }
}
