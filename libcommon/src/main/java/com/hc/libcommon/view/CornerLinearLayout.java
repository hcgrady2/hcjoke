package com.hc.libcommon.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * Created by hcw  on 2020/6/15
 * 类描述：
 * all rights reserved
 */

public class CornerLinearLayout extends LinearLayout {
    public CornerLinearLayout(Context context) {
        this(context, null);
    }

    public CornerLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CornerLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CornerLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        ViewHelper.setViewOutline(this, attrs, defStyleAttr, defStyleRes);
    }
}
