package com.example.goodluck.customview.pullrefreshview;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.goodluck.customview.pullrefreshview.PullRefreshHeaderView;

public class PullRefreshLayout extends LinearLayout {
    private Paint clockHeadPaint;
    private Path clockHeadPath;

    private Paint circlePaint;

    public PullRefreshLayout(Context context) {
        super(context);
        init();
    }

    public PullRefreshLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public PullRefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PullRefreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    PullRefreshHeaderView headerView;
    View contentView, endView;
    private int headerWidth, headerHeight;
    private int contentWidth, contentHeight;
    //是否时第一次初始化
    private boolean isFirstLoad = false;

    private float progress;
    private float mTouchPullY;

    @Override

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int measureWidth, measureHeight;
        if (widthMode == MeasureSpec.EXACTLY) {
            measureWidth = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            measureWidth = widthSize;
        } else {
            measureWidth = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            measureHeight = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            measureHeight = heightSize;
        } else {
            measureHeight = heightSize;
        }
        setMeasuredDimension(measureWidth, measureHeight);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (getChildCount() < 3) {
            return;
        }
        if (!isFirstLoad) {
            isFirstLoad = true;
            headerView = (PullRefreshHeaderView) getChildAt(0);

            contentView = getChildAt(1);
            endView = getChildAt(2);

            headerWidth = headerView.getMeasuredWidth();
            headerHeight = headerView.getMeasuredHeight();

            contentWidth = getMeasuredWidth();
            contentHeight = getMeasuredHeight();

            headerView.layout(0, -headerHeight, headerWidth, 0);
            contentView.layout(0, 0, contentWidth, contentHeight);
        } else {
            headerView.layout(0, (int) (headerHeight * progress), headerWidth, 0);
            contentView.layout(0, (int) (headerHeight * progress), contentWidth,
                    (int) (contentHeight + headerHeight * progress));
            if (isTouch) {
                headerView.setisArcStop(false);
                headerView.startArcAnimator();
                isTouch = false;
            }
        }

    }

    private void init() {

    }

    private boolean isTouch = false;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mTouchPullY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float len = event.getY();
                if (len >= mTouchPullY) {
                    float moveSize = len - mTouchPullY;
                    progress = moveSize >= mTouchPullY ? 1 : moveSize / mTouchPullY;
                    dy = progress;
                }
                isTouch = true;
                requestLayout();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP:

                arcAnimator();
                return false;
            default:
        }
        return false;
    }

    /**
     * 结束动画，页面弹回
     */
    private ValueAnimator animator;
    private float dy;
    private void arcAnimator() {
        if (animator != null && animator.isRunning()) {
            return;
        }
        animator = ValueAnimator.ofFloat(dy, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (float) animation.getAnimatedValue();
                requestLayout();
            }
        });
        animator.setDuration(1000);
        animator.setRepeatCount(0);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                headerView.stopArcAnimator();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
