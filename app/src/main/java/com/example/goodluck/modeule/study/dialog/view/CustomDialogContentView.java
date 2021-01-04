package com.example.goodluck.modeule.study.dialog.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

public class CustomDialogContentView extends RelativeLayout {

    public CustomDialogContentView(Context context) {
        this(context, null);
    }

    public CustomDialogContentView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomDialogContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CustomDialogContentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 对事件进行拦截，不会穿透
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
