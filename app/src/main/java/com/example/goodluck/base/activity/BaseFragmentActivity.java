package com.example.goodluck.base.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.example.goodluck.R;

public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener {
    public Activity mActivity;
    public Context mContext;
    public String TAG = "yinp";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //界面间的切换动画
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition enter = TransitionInflater.from(this).inflateTransition(R.transition.exit_animation);
        getWindow().setEnterTransition(enter);//进入
        getWindow().setExitTransition(enter);//退出
        getWindow().setReenterTransition(enter);//用于决定如果当前Activity已经打开过，并且再次打开该Activity时的动画
        //有截屏攻击风险
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        mActivity = this;
        mContext = this;
    }
    @Override
    public void onClick(View v) {

    }
}
