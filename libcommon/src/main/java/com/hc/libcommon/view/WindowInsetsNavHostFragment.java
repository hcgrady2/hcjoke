package com.hc.libcommon.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

/**
 * Created by hcw  on 2020/6/15
 * 类描述： 自适应状态栏,应该是调整了 windowInset 。
 * all rights reserved
 */
public class WindowInsetsNavHostFragment extends NavHostFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        WindowInsetsFrameLayout layout = new WindowInsetsFrameLayout(inflater.getContext());
        layout.setId(getId());
        return layout;
    }
}
