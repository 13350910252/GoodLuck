package com.example.goodluck.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PercentCircleView extends View {
    public PercentCircleView(Context context) {
        super(context);
    }

    public PercentCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PercentCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    Paint maxCirclePaint;
    Paint minCirclePaint;
    private void init(){

    }
    private void drawMaxCircle(){

    }
    private void drawMinCircle(){

    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
