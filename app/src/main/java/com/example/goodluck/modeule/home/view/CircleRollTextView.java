package com.example.goodluck.modeule.home.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 圆形滚动text，用于首页
 */
public class CircleRollTextView extends View {

    public CircleRollTextView(@NonNull Context context) {
        super(context);
        init();
    }

    public CircleRollTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleRollTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int h = getMeasuredHeight();
        int w = getMeasuredWidth();
        Log.d("yinp", "onMeasure: " + h);
        Log.d("yinp", "onMeasure: " + w);
    }
}