package com.hc.hcppjoke.exoplayer;

import android.view.ViewGroup;

public interface IPlayTarget {

    /**
     * 获取视频的外部容器
     * @return
     */
    ViewGroup getOwner();

    //活跃状态 视频可播放
    void onActive();

    //非活跃状态，暂停它
    void inActive();


    boolean isPlaying();
}
