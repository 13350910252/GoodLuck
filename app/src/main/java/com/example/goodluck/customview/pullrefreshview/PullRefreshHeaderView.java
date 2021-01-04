package com.example.goodluck.customview.pullrefreshview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.example.goodluck.utils.AppUtils;


/**
 * 用作下拉刷新的顶部动画效果
 */
public class PullRefreshHeaderView extends View {

    public PullRefreshHeaderView(Context context) {
        super(context);
        init();
    }

    public PullRefreshHeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullRefreshHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PullRefreshHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private float screenEnterWidth;
    private float screenEnterHeight;
    //可下拉的高度
    private float dragHeight;
    //钟表头部的基础信息
    private Path headerPath;
    private Paint headerPaint;
    //钟表圆形部分
    private Paint circlePaint;
    private float circlePointX;
    private float circlePointY;
    private float circleRadius = 36f;
    //钟表中扇形部分
    private Paint arcPaint;
    private Paint arcPaintW;
    private RectF rectF;

    private float progress;
    //确定钟表头的位置
    private float dx = AppUtils.dpToPx(getContext(), 6);
    private float dy = AppUtils.dpToPx(getContext(), 4);
    private float arcRadius = AppUtils.dpToPx(getContext(), 8);

    private int widthMode;
    private int heightMode;
    private int measureWidth, measureHeight;

    /**
     * 初始化一些数据
     */
    private void init() {
        dragHeight = AppUtils.dpToPx(getContext(), 60);
        headerPath = new Path();
        headerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        headerPaint.setStyle(Paint.Style.FILL);
        headerPaint.setColor(0x99ff0000);

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setDither(true);
        circlePaint.setColor(0x99ff0000);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(8);

        //钟表中扇形部分
        arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setDither(true);
        arcPaint.setStyle(Paint.Style.FILL);
        arcPaint.setColor(0x99ff0000);
        rectF = new RectF();
        arcPaintW = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcPaintW.setStyle(Paint.Style.FILL);
        arcPaintW.setColor(0x55ff0000);

//        startArcAnimator();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthMode = MeasureSpec.getMode(widthMeasureSpec);
        heightMode = MeasureSpec.getMode(widthMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            //确切的
            measureWidth = (int) AppUtils.getWidthPixels(getContext());
        } else if (widthMode == MeasureSpec.AT_MOST) {
            measureWidth = (int) AppUtils.getWidthPixels(getContext());
        } else {
            measureWidth = (int) AppUtils.getWidthPixels(getContext());
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            measureHeight = (int) dragHeight;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            measureHeight = (int) dragHeight;
        } else {
            measureHeight = (int) dragHeight;
        }

        setMeasuredDimension(measureWidth, measureHeight);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        screenEnterWidth = getWidth() >> 1;
        screenEnterHeight = getHeight() >> 1;

        headerPath.reset();
        headerPath.moveTo(screenEnterWidth, screenEnterHeight - circleRadius - dy);
        headerPath.lineTo(screenEnterWidth + dx, screenEnterHeight - circleRadius - dy);
        headerPath.lineTo(screenEnterWidth + dx, screenEnterHeight - circleRadius - 2 * dy);
        headerPath.lineTo(screenEnterWidth + 2 * dx, screenEnterHeight - circleRadius - 2 * dy);
        headerPath.lineTo(screenEnterWidth + 2 * dx, screenEnterHeight - circleRadius - 3 * dy);
        headerPath.lineTo(screenEnterWidth - 2 * dx, screenEnterHeight - circleRadius - 3 * dy);
        headerPath.lineTo(screenEnterWidth - 2 * dx, screenEnterHeight - circleRadius - 2 * dy);
        headerPath.lineTo(screenEnterWidth - dx, screenEnterHeight - circleRadius - 2 * dy);
        headerPath.lineTo(screenEnterWidth - dx, screenEnterHeight - circleRadius - dy);

        circlePointX = screenEnterWidth;
        circlePointY = screenEnterHeight;

        rectF.set(screenEnterWidth - arcRadius, screenEnterHeight - arcRadius,
                screenEnterWidth + arcRadius, screenEnterHeight + arcRadius);
        Log.d("abcd", "onLayout: ");
    }

    private boolean isfa = false;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(headerPath, headerPaint);
        canvas.drawCircle(circlePointX, circlePointY, circleRadius, circlePaint);
        if (!isfa) {
            canvas.drawArc(rectF, -90, 360 * progress, true, arcPaint);
        } else {
            canvas.drawArc(rectF, -90, 360 * progress, true, arcPaintW);
        }

    }

    private ValueAnimator animator;

    public void startArcAnimator() {
        if (animator != null && animator.isRunning()) {
            return;
        }
        animator = ValueAnimator.ofFloat(0, 1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (!isArcStop) {
                    progress = (float) animation.getAnimatedValue();
                    requestLayout();
                }else{
                    animator.removeAllUpdateListeners();
                }
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                isfa = !isfa;
            }
        });
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1000);
        animator.start();
    }

    private boolean isArcStop = false;

    public void stopArcAnimator() {
        if (animator != null && animator.isStarted()) {
            isArcStop = true;
            animator.cancel();
            animator = null;
        }
    }
    public void  setisArcStop(boolean isArcStop){
        this.isArcStop = isArcStop;
    }
}
