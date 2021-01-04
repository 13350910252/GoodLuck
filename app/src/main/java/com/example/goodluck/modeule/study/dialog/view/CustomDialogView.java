package com.example.goodluck.modeule.study.dialog.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomDialogView extends FrameLayout {
    /**
     * 背景页，主要是实现阴影效果,随便一个viewgroup都可以
     */
    private View dialogBgView;
    /**
     * 弹窗页，显示弹窗，其内可以拜访需要显示的页面
     * 也是自定义的，需要对点击事件进行拦截。
     */
    private View dialogContentView;
    /**
     * 背景view的宽高
     */
    private int bgWidth;
    private int bgHeight;

    private int contentWidth;
    private int contentHeight;
    /**
     * 是否时第一次初始化
     */
    private boolean isFirstLoad = false;
    /**
     * 是否能够结束动画
     */
    private boolean isCanEnd = false;
    /**
     * 判断弹窗是否打开了
     */
    private boolean isStarting = false;
    /**
     * 动画的时长
     */
    private long duration = 400;
    /**
     * 存储弹窗页面的矩形，用于点击事件计算，
     * 方便控制弹窗收回
     */
    private Rect rectContent;

    public CustomDialogView(@NonNull Context context) {
        this(context, null);
    }

    public CustomDialogView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomDialogView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CustomDialogView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initFirstView();
    }

    private void initFirstView() {
        isFirstLoad = true;
    }

    /**
     * 获取当前viewGroup中的所有子view
     * 对第一个子view重新摆放位置，放在上边，相当于隐藏，需要时再弹出
     * 所以必须固定格式
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (getChildCount() != 2) {
            return;
        }
        if (isFirstLoad) {
            dialogBgView = getChildAt(0);
            dialogContentView = getChildAt(1);

            bgWidth = dialogBgView.getWidth();
            bgHeight = dialogBgView.getHeight();

            contentWidth = dialogContentView.getWidth();
            contentHeight = dialogContentView.getHeight();
            isFirstLoad = false;

            rectContent = new Rect(dialogContentView.getLeft(), dialogContentView.getTop()
                    , dialogContentView.getRight(), dialogContentView.getBottom());
        }
        dialogContentView.layout(0, -contentHeight, contentWidth, 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!rectContent.contains((int) event.getX(), (int) event.getY())) {
                    endAnimation();
                }
                break;
        }
        //必须对点击事件进行拦截
        return true;
    }

    /**
     * 弹窗打开和关闭时的动画
     */
    public void startAnimations() {
        if (isStarting) {
            return;
        }
        //清理动画
        dialogContentView.clearAnimation();
        dialogBgView.clearAnimation();
        isCanEnd = false;
        isStarting = true;

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(dialogBgView, "alpha", 0, 0.6f);
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(dialogContentView, "translationY", 0, contentHeight * 1.0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alphaAnimator, translationAnimator);
        animatorSet.setDuration(duration);
        animatorSet.start();
        if (animatorSet.isStarted()) {
            isCanEnd = true;
        }
    }

    /**
     * 结束动画
     */
    public void endAnimation() {
        //目的时判断当前是否有开启动画，有才允许关闭动画
        if (!isCanEnd) {
            return;
        }
        //清理动画
        dialogContentView.clearAnimation();
        dialogBgView.clearAnimation();
        isCanEnd = false;
        isStarting = false;

        ObjectAnimator alphaOb = ObjectAnimator.ofFloat(dialogBgView, "alpha", 0.6f, 0f);
        ObjectAnimator translationYOb = ObjectAnimator.ofFloat(dialogContentView, "translationY", 1.0f * contentHeight, 0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alphaOb, translationYOb);
        animatorSet.setDuration(duration);
        animatorSet.start();
    }

    /**
     * 监听弹窗的打开和关闭
     */
    private OnDialogOpenListener onDialogOpenListener;

    public interface OnDialogOpenListener {
        void onDialogOpen(boolean isOpen);
    }

    public void setOnDialogOpenListener(OnDialogOpenListener onDialogOpenListener) {
        this.onDialogOpenListener = onDialogOpenListener;
    }

    /**
     * 设置动画的时长
     *
     * @param duration
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isStarting() {
        return isStarting;
    }
}
