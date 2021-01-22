package com.example.goodluck.modeule.study.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.goodluck.base.activity.AppBaseActivity;
import com.example.goodluck.databinding.ActivityStudyAndroidBinding;
import com.example.goodluck.modeule.study.dialog.activity.MyCreateDialogActivity;
import com.example.goodluck.modeule.study.dialog.activity.SystemDialogActivity;
import com.example.goodluck.utils.StatusBarUtil;

/**
 * android部分的学习内容
 */
public class StudyAndroidActivity extends AppBaseActivity<ActivityStudyAndroidBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        bindData();
    }
    @Override
    protected void initViews() {
        setStatusBarHeight(StatusBarUtil.getStatusBarHeight(mContext));
        binding.btnNoShadowDialog.setOnClickListener(this);
        binding.btnSystemDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (isClick) {
            return;
        }
        isClick = true;
        new Handler().postDelayed(runnable, 500);
        if (view == binding.btnNoShadowDialog) {
            startActivity(new Intent(this, MyCreateDialogActivity.class));
        } else if (view == binding.btnSystemDialog) {
            startActivity(new Intent(this, SystemDialogActivity.class));
        }
    }
}
